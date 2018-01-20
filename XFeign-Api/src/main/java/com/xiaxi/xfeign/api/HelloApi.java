package com.xiaxi.xfeign.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by BaoCai on 18/1/20.
 * Description:
 */
public interface HelloApi {
    @RequestMapping(value = "/hello/xfeign")
    String hello(@RequestParam(value = "name") String name,
                 @RequestParam(value = "age") int age);

    @RequestMapping(value = "/hello/user")
    User user(@RequestParam(value = "name") String name,
              @RequestParam(value = "age") int age);
}
