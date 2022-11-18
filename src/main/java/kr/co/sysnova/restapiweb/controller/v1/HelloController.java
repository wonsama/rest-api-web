package kr.co.sysnova.restapiweb.controller.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Getter;
import lombok.Setter;

@Controller
public class HelloController {

    @GetMapping("/hello/string")
    @ResponseBody
    public String helloWorldString() {
        return "hello string";
    }

    @GetMapping("/hello/json")
    @ResponseBody
    public Hello helloWorldJson() {
        Hello hello = new Hello();
        hello.setMessage("hello json");
        return hello;
    }

    @GetMapping("/hello/page")
    public String helloWorldPage() {
        return "helloWorld";
    }

    @Getter
    @Setter
    public static class Hello {
        private String message;
    }
}
