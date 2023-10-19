package fun.pullock.starter.sample.crlf;

import fun.pullock.starter.sample.crlf.model.CrlfModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crlf")
public class CrlfController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CrlfController.class);

    @GetMapping("/test/get")
    public String test(@RequestParam String key) {
        return key;
    }

    @PostMapping("/test/post")
    public String test(@RequestBody CrlfModel model) {
        LOGGER.info("key: {}", model.getKey());
        return model.toString();
    }

    @PostMapping("/test/post/exception")
    public String exception(@RequestBody CrlfModel model) {
        throw new RuntimeException(model.getKey());
    }
}
