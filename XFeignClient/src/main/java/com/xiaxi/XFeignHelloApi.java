package com.xiaxi;

import com.xiaxi.feign.annotation.XFeignClient;
import com.xiaxi.xfeign.api.HelloApi;
import com.xiaxi.xfeign.api.User;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by BaoCai on 18/1/20.
 * Description:
 */
@XFeignClient(url = "http://localhost:19090")
public class XFeignHelloApi implements HelloApi {
    @Override
    public String hello(String name, int age) {
        return null;
    }

    @Override
    public User user(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        return null;
    }
}
