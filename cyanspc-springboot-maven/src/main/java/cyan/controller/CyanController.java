package cyan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CyanController {

    @RequestMapping(value = "hello")
    public String testHello(){
        return "hello";
    }

}
