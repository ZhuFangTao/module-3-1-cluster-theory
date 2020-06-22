package com.lagou.nettyrpc.server.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * \* User: ZhuFangTao
 * \* Date: 2020/6/17 11:16 上午
 * \
 */
@Component
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static Object getBeanByType(String className) throws ClassNotFoundException {
        return applicationContext.getBean(Class.forName(className));
    }

    public void setApplicationContext(ApplicationContext arg0) {
        applicationContext = arg0;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

}