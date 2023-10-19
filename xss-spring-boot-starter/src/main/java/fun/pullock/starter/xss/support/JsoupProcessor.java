package fun.pullock.starter.xss.support;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsoupProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsoupProcessor.class);

    /**
     * 不对代码进行格式化
     */
    private final static Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    private static Safelist safelist = null;

    public JsoupProcessor(String whitelist) {
        initSafelist(whitelist);
    }

    public String clean(String originContent) {
        try {
            LOGGER.info("before jsoup clean: {}", originContent);
            String value = Jsoup.clean(originContent, "", safelist, outputSettings);
            LOGGER.info("after jsoup clean: {}", value);
            return value;
        } catch (Exception e) {
            LOGGER.error("jsoup clean html error, origin content: {}, cause: ", originContent, e);
            return originContent;
        }
    }

    private void initSafelist(String whitelist) {
        if (StringUtils.isEmpty(whitelist)) {
            whitelist = "none";
        }

        switch (whitelist) {
            case "none":
                safelist = Safelist.none();
                break;
            case "simple-text":
                safelist = Safelist.simpleText();
                break;
            case "basic":
                safelist = Safelist.basic();
                break;
            case "basic-with-images":
                safelist = Safelist.basicWithImages();
                break;
            case "relaxed":
                safelist = Safelist.relaxed();
                break;
            case "custom":
                // TODO 自定义规则
                safelist = null;
                break;
            default:
                safelist = Safelist.none();

        }
    }
}
