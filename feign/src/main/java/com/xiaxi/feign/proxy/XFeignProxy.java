package com.xiaxi.feign.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaxi.feign.unils.HttpUtils;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BaoCai on 18/1/20.
 * Description:
 */
public class XFeignProxy implements InvocationHandler {

    private static Class REQUEST_MAPPING_TYPE = RequestMapping.class;

    private static ObjectMapper objectMapper = new ObjectMapper();

    private String serverUrl;


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, String> params = this.paramsMap(method, args);
        final String[] uri = {null};
        final RequestMethod[] httpMethod = {RequestMethod.GET};
        Annotation[] annotations = method.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            Class clazz = annotation.annotationType();
            if (clazz == REQUEST_MAPPING_TYPE) {
                RequestMapping mapping = (RequestMapping) annotation;
                if (mapping.method().length > 0) {
                    httpMethod[0] = mapping.method()[0];
                }
                String[] values = mapping.value();
                if (null == values) {
                    uri[0] = "/";
                } else if (values.length > 1) {
                    throw new RuntimeException("one method only mapping a url");
                } else {
                    uri[0] = values[0];
                }
            }
        }

        if (null == uri[0]) {
            //本地方法
            return method.invoke(proxy, args);
        }

        String url = this.getServerUrl() + uri[0];

        String result;
        if (RequestMethod.POST.equals(httpMethod[0])) {
            result = HttpUtils.post(url, params);
        } else {
            result = HttpUtils.get(url, params);
        }

        Class resultType = method.getReturnType();
        if (String.class.equals(resultType)) {
            return result;
        }
        Object object = objectMapper.readValue(result, resultType);
        return object;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public XFeignProxy(String serverUrl) {
        Assert.notNull(serverUrl, "the server url can not be null");
        this.serverUrl = serverUrl;
    }

    private Map<String, String> paramsMap(Method method, Object[] args) {
        Map<String, String> params = new HashMap();

        Parameter[] parameters = method.getParameters();
        if (null != parameters && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                Annotation[] paramAnnotations = parameter.getDeclaredAnnotations();
                boolean hasRequestParam = false;
                for (Annotation annotation : paramAnnotations) {
                    if (RequestParam.class.equals(annotation.annotationType())) {
                        hasRequestParam = true;
                        RequestParam requestParam = (RequestParam) annotation;
                        String paramName = requestParam.value();
                        params.put(paramName, String.valueOf(args[i]));
                    }
                }
                if (!hasRequestParam) {
                    params.put(parameter.getName(), String.valueOf(args[i]));
                }
            }
        }

        return params;
    }
}
