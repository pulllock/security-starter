package fun.pullock.starter.xss.support;

import org.apache.commons.text.StringEscapeUtils;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class AntiSamyProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(AntiSamyProcessor.class);

    private static Policy policy = null;

    private AntiSamy antiSamy;

    public AntiSamyProcessor(String policyFile) {
        initAntiSamyPolicy(policyFile);
        antiSamy = new AntiSamy();
    }

    public String cleanHtml(String originContent) {
        try {
            LOGGER.info("before antisamy clean: {}", originContent);
            String value = antiSamy.scan(originContent, policy).getCleanHTML();
            // json也会被转义，需要进行反转义
            value = StringEscapeUtils.unescapeHtml4(value);
            LOGGER.info("after antisamy clean: {}", value);
            return value;
        } catch (ScanException | PolicyException e) {
            LOGGER.error("antiSamy clean html error, origin content: {}, cause: ", originContent, e);
            return originContent;
        }
    }

    private void initAntiSamyPolicy(String policyFile) {
        try {
            policy = Policy.getInstance(new ClassPathResource(policyFile).getInputStream());
        } catch (PolicyException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
