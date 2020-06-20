package org.flyfish.springstudy.controler;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello() {
        return "sss";
    }
}
