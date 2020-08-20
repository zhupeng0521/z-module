package com.module.zhupeng.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * PropertiesUtils
 * <p>date : 2019-12-20</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class PropertiesUtils {

    private static final String DEFAULT_NAME = "application";
    private static final String MODULE_NAME = "application-module";
    public static final String INTEGRATION = "integration";

    private static final Object propertiesMapLock = new Object();
    private static volatile Map<String, Properties> propertiesMap = new HashMap<>();

    private static final Object propertiesGroupsLock = new Object();
    private static volatile Map<String, Map<String, String>> propertiesGroups = new HashMap<>();

    private static Properties moduleProperties() throws IOException {
        Resource resource = new ClassPathResource("module.properties");
        return PropertiesLoaderUtils.loadProperties(new EncodedResource(resource, Charset.forName("UTF-8")));
    }

    private static Properties loadProperties(String name) throws IOException {
        String path = get(MODULE_NAME, "module.conf.root");
        Resource resource;
        if (StringUtils.isEmpty(path)) {
            //path为空 直接读取class路径下的properties
            resource = new ClassPathResource(String.format("%s.properties", name));
        } else if (path.startsWith("$")) {
            //path以$开头则是读取环境变量，但路径需在环境变量对应路径下的特殊路径 modules\模组\properties
            String key = get(MODULE_NAME, "module.server.active");
            String propertiesPath = String.format("%s\\modules\\%s\\conf\\%s.properties",
                    System.getProperty(path.substring(1)), key, name);
            resource = new FileSystemResource(propertiesPath);
        } else if (path.startsWith("*")) {
            //path以*开头则是读取环境变量，但路径需在环境变量对应路径下properties
            String propertiesPath = String.format("%s\\%s.properties",
                    System.getProperty(path.substring(1)), name);
            resource = new FileSystemResource(propertiesPath);
        } else {
            //若以上都不符合，则视为固定路径下的properties
            resource = new FileSystemResource(String.format("%s\\%s.properties", path, name));
        }
        return PropertiesLoaderUtils.loadProperties(new EncodedResource(resource, Charset.forName("UTF-8")));
    }

    public static Properties loadModule() throws IOException {
        if (!propertiesMap.containsKey(MODULE_NAME)) {
            synchronized (propertiesMapLock) {
                if (!propertiesMap.containsKey(MODULE_NAME)) {
                    propertiesMap.put(MODULE_NAME, moduleProperties());
                }
            }
        }
        return propertiesMap.get(MODULE_NAME);
    }

    public static Properties load(String propertiesKey) throws IOException {
        if (!propertiesMap.containsKey(propertiesKey)) {
            synchronized (propertiesMapLock) {
                if (!propertiesMap.containsKey(propertiesKey)) {
                    propertiesMap.put(propertiesKey, loadProperties(propertiesKey));
                }
            }
        }
        return propertiesMap.get(propertiesKey);
    }

    public static Properties load() throws IOException {
        return load(DEFAULT_NAME);
    }

    public static Properties reload(String propertiesKey) throws IOException {
        if (propertiesMap.containsKey(propertiesKey)) {
            synchronized (propertiesMapLock) {
                if (propertiesMap.containsKey(propertiesKey)) {
                    propertiesMap.remove(propertiesKey);
                }
            }
        }
        load(propertiesKey);
        return propertiesMap.get(propertiesKey);
    }

    public static Properties reload() throws IOException {
        return reload(DEFAULT_NAME);
    }

    public static Integer getInteger(String key) {
        return getInteger(DEFAULT_NAME, key);
    }

    public static Integer getInteger(String propertiesKey, String key) {
        String value = get(propertiesKey, key);
        if (null == value) {
            return null;
        }
        return Integer.valueOf(value);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(DEFAULT_NAME, key);
    }

    public static boolean getBoolean(String propertiesKey, String key) {
        String value = get(propertiesKey, key);
        if (null == value) {
            return false;
        }
        return Boolean.valueOf(get(propertiesKey, key));
    }

    public static String get(String key) {
        return get(DEFAULT_NAME, key);
    }

    public static String get(String propertiesKey, String key) {
        if (!propertiesMap.containsKey(propertiesKey)) {
            return null;
        }
        return propertiesMap.get(propertiesKey).getProperty(key);
    }

    public static String getModule(String key, String defaultValue) {
        return getDefault(MODULE_NAME, key, defaultValue);
    }

    public static String getModule(String key) {
        return getModule(key, "");
    }

    public static String getDefault(String key, String defaultValue) {
        return getDefault(DEFAULT_NAME, key, defaultValue);
    }

    public static String getDefault(String propertiesKey, String key, String defaultValue) {
        if (!propertiesMap.containsKey(propertiesKey)) {
            return defaultValue;
        }
        return propertiesMap.get(propertiesKey).getProperty(key, defaultValue);
    }

    public static Map<String, String> loadGroup(String groupKey) {
        if (!propertiesGroups.containsKey(groupKey)) {

            synchronized (propertiesGroupsLock) {

                if (!propertiesGroups.containsKey(groupKey)) {

                    Map<String, String> map = new HashMap<>();
                    propertiesMap.values().forEach(o -> {
                        Map<String, String> perProperties = loadGroup(groupKey, o);
                        if (perProperties.size() > 0) {
                            map.putAll(perProperties);
                        }
                    });

                    propertiesGroups.put(groupKey, map);
                }
            }
        }
        return propertiesGroups.get(groupKey);
    }

    public static Map<String, String> reloadGroup(String groupKey) {
        if (propertiesGroups.containsKey(groupKey)) {
            synchronized (propertiesGroupsLock) {
                if (propertiesGroups.containsKey(groupKey)) {
                    propertiesGroups.remove(groupKey);
                }
            }
        }
        return loadGroup(groupKey);
    }

    public static Map<String, String> loadGroup(String groupKey, Properties properties) {
        final String prefix = groupKey + ".";
        final int prefixLength = prefix.length();
        return properties.stringPropertyNames().stream()
                .filter(s -> s.length() > prefixLength && s.startsWith(prefix))
                .distinct()
                .collect(Collectors.toMap(s -> s.substring(prefixLength), s -> {
                    return properties.getProperty(s, "");
                }));
    }

    public static Map<String, String> loadGroup(String groupKey, String propertiesKey) throws IOException {
        return loadGroup(groupKey, load(propertiesKey));
    }

    private static String getGroup(String groupKey, String key) {

        if (!propertiesGroups.containsKey(groupKey)) {
            return null;
        }

        if (!propertiesGroups.get(groupKey).containsKey(key)) {
            return null;
        }

        return propertiesGroups.get(groupKey).get(key);
    }

    private static String getGroup(String groupKey, String key, String defaultValue) {

        if (!propertiesGroups.containsKey(groupKey)) {
            return defaultValue;
        }

        if (!propertiesGroups.get(groupKey).containsKey(key)) {
            return defaultValue;
        }

        if (!StringUtils.isEmpty(propertiesGroups.get(groupKey).get(key))) {
            return defaultValue;
        }

        return propertiesGroups.get(groupKey).get(key);
    }

}
