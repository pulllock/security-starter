package fun.pullock.starter.xss;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.xss.antisamy")
public class XssAntiSamyProperties {

    /**
     * 是否启用antisamy xss过滤器
     */
    private boolean enable = true;

    /**
     * antisamy的策略文件
     */
    private String policyFile;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getPolicyFile() {
        return policyFile;
    }

    public void setPolicyFile(String policyFile) {
        this.policyFile = policyFile;
    }
}
