package com.module.zhupeng.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

/**
 * SpringContextUtil
 *
 * @author shuzheng
 * @date 2016年10月15日
 */
@Deprecated
public class SpringBeanUtil implements ApplicationContextAware {

    private static DefaultListableBeanFactory beanFactory = null;

    private SpringBeanUtil() {
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        beanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
    }

    public static <T> void register(String beanName, Class<T> beanClass) {

        if (Objects.isNull(beanClass)) {
            LogUtils.getLogger().debug("beanClass为空，无法注入:{}", beanName);
            return;
        }
        //构建BeanDefinitionBuilder
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);


        //从builder中获取到BeanDefinition对象 并注入
        beanFactory.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }

}
