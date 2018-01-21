package com.xiaxi.feign.proxy;


import java.lang.reflect.Proxy;

/**
 * Created by BaoCai on 18/1/20.
 * Description:
 */
public class XFeignProxyFactory  {
    public static Object createdProxy(Object target , String url){
        XFeignProxy proxy = new XFeignProxy(url);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),proxy);
    }
}
