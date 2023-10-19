package fun.pullock.starter.xss.support;

import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EsapiProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(EsapiProcessor.class);


    public String encodeForHtml(String originContent) {

        try {
            LOGGER.info("before esapi encode: {}", originContent);
            String value = ESAPI.encoder().encodeForHTML(originContent);
            // TODO json也会被转义，需要进行反转义
            LOGGER.info("after esapi encode: {}", value);
            return value;
        } catch (Exception e) {
            LOGGER.error("esapi encode html error, origin content: {}, cause: ", originContent, e);
            return originContent;
        }
    }
}
