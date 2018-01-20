package com.xiaxi.feign.config;

import com.xiaxi.feign.beanPostProcessor.XFeignBeanPostProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by BaoCai on 18/1/20.
 * Description:
 */
@Configuration
public class XFeignConfig {
    @Bean
    public XFeignBeanPostProcessor xFeignBeanPostProcessor(){
        return new XFeignBeanPostProcessor();
    }
}
