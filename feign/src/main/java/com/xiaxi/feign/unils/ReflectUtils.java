package com.xiaxi.feign.unils;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * Created by BaoCai on 18/1/20.
 * Description:
 */
public class ReflectUtils {

    private ReflectUtils(){}

    private static final LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer
            = new LocalVariableTableParameterNameDiscoverer();

    public static String[] getParameterNames(Method method){
        return localVariableTableParameterNameDiscoverer.getParameterNames(method);
    }
}
