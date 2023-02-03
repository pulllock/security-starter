package me.cxis.starter.sample.xss;

import me.cxis.starter.sample.xss.model.XssModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xss")
public class XssController {

    @GetMapping("/test/get")
    public String test(@RequestParam String key) {
        return key;
    }

    @PostMapping("/test/post")
    public String test(@RequestBody XssModel model) {
        return model.toString();
    }
}
