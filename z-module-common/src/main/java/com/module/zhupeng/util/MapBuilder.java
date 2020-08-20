/**
 * FileName: MapBuilder
 * Author:   DONGSK
 * Datetime: 2020/4/28 17:32
 * Description: 混合map处理类
 * History:
 * 作者姓名 --修改时间 --版本号--描述
 */
package com.module.zhupeng.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MapBuilder
 * 混合map处理类
 *
 * @author DONGSK
 * @date 2020/4/28
 * @since 1.0.0
 */
public class MapBuilder {

    Map<String, Object> mapContainer;

    private MapBuilder() {
        this.mapContainer = new HashMap<>();
    }

    private MapBuilder(Map<String, Object> mapContainer) {
        this.mapContainer = mapContainer;
    }

    public static MapBuilder build(Map<String, Object> mapContainer) {
        return new MapBuilder(mapContainer);
    }

    public static MapBuilder build() {
        return new MapBuilder();
    }


    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap();
    }

    public Map<String, Object> getMapContainer() {
        return this.mapContainer;
    }

    public MapBuilder add(String key, Object value) {
        if (!this.mapContainer.containsKey(key)) {
            mapContainer.put(key, value);
        }
        return this;
    }

    public MapBuilder update(String key, Object value) {
        if (this.mapContainer.containsKey(key)) {
            mapContainer.remove(key);
            mapContainer.put(key, value);
        }
        return this;
    }

    public MapBuilder remove(String key) {
        if (this.mapContainer.containsKey(key)) {
            mapContainer.remove(key);
        }
        return this;
    }

    public Object get(String key) {
        return mapContainer.getOrDefault(key, null);
    }

    public Object get(String key, Object defaultValue) {
        return mapContainer.getOrDefault(key, defaultValue);
    }

    public String getString(String key) {
        return String.valueOf(get(key, ""));
    }

    public String getString(String key, String defaultValue) {
        return String.valueOf(get(key, defaultValue));
    }


    public boolean getBoolean(String key) {
        String s = getString(key);
        return !StringUtils.isEmpty(s) && Boolean.parseBoolean(s);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        String s = String.valueOf(get(key, defaultValue));
        return Integer.parseInt(s);
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        String s = String.valueOf(get(key, defaultValue));
        return Long.parseLong(s);
    }

    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    public double getDouble(String key, double defaultValue) {
        String s = String.valueOf(get(key, defaultValue));
        return Double.parseDouble(s);
    }

    public float getFloat(String key) {
        return getFloat(key, 0);
    }

    public float getFloat(String key, float defaultValue) {
        String s = String.valueOf(get(key, defaultValue));
        return Float.parseFloat(s);
    }

    public <T> T get(String key, Class<T> tClass) {
        String jsonString = JsonUtils.toJson(get(key));
        return JsonUtils.jsonToType(jsonString, tClass);
    }

    public <T> List<T> getList(String key, Class<T> tClass) {
        List<T> defaultList = new ArrayList<>();
        String jsonString = JsonUtils.toJson(get(key, defaultList));
        return JsonUtils.jsonToListType(jsonString, tClass);
    }

}
