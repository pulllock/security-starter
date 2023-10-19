package fun.pullock.starter.xss.support;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlSanitizerProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(HtmlSanitizerProcessor.class);

    private PolicyFactory policy = null;

    public HtmlSanitizerProcessor(String allowElements) {
        initPolicy(allowElements);
    }

    public String sanitize(String originContent) {
        try {
            LOGGER.info("before html sanitizer sanitize: {}", originContent);
            String value = policy.sanitize(originContent);
            // json也会被转义，需要进行反转义
            value = StringEscapeUtils.unescapeHtml4(value);
            LOGGER.info("after html sanitizer sanitize: {}", value);
            return value;
        } catch (Exception e) {
            LOGGER.error("html sanitizer sanitize html error, origin content: {}, cause: ", originContent, e);
            return originContent;
        }
    }

    private void initPolicy(String allowElements) {
        HtmlPolicyBuilder policyBuilder = new HtmlPolicyBuilder();
        if (StringUtils.isNotEmpty(allowElements)) {
            String[] allowedETags = allowElements.split(",");
            policyBuilder.allowElements(allowedETags);
        }

        policy = policyBuilder.toFactory();
    }
}
