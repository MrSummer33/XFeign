package com.xiaxi.feign.beanFactoryPostProcessor;

import com.xiaxi.feign.annotation.XFeignClient;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Created by BaoCai on 18/1/20.
 * Description:
 */
public class XFeignBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String[] beanNames = configurableListableBeanFactory.getBeanDefinitionNames();
        Arrays.stream(beanNames).forEach(name -> {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(name);
            Class clazz = beanDefinition.getClass();
            Annotation[] annotations = clazz.getDeclaredAnnotations();
            for(Annotation annotation : annotations){
                if(XFeignClient.class.equals(annotation.annotationType())){

                }
            }
        });
    }
}
