package fun.pullock.starter.sample.cors;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cors")
public class CorsController {

    @CrossOrigin(origins = "*")
    @GetMapping("/test/get")
    public String test(@RequestParam String key) {
        return key;
    }
}
