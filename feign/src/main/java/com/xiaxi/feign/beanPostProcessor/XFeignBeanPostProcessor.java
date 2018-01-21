package com.xiaxi.feign.beanPostProcessor;

import com.xiaxi.feign.annotation.XFeignClient;
import com.xiaxi.feign.proxy.XFeignProxyFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;

/**
 * Created by BaoCai on 18/1/20.
 * Description: return feignClient proxy
 */
public class XFeignBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Annotation[] annotations = bean.getClass().getDeclaredAnnotations();
        if (null != annotations && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (XFeignClient.class.equals(annotation.annotationType())) {
                    XFeignClient feignClient = (XFeignClient) annotation;
                    String serverUrl = feignClient.url();
                    return XFeignProxyFactory.createdProxy(bean, serverUrl);
                }
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
