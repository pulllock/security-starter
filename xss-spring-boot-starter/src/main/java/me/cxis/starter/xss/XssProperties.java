package me.cxis.starter.xss;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.xss")
public class XssProperties {

    /**
     * 是否启用antisamy xss过滤器
     */
    private boolean antisamyEnable = true;

    /**
     * antisamy的策略文件
     */
    private String antisamyPolicyFile;

    public boolean isAntisamyEnable() {
        return antisamyEnable;
    }

    public void setAntisamyEnable(boolean antisamyEnable) {
        this.antisamyEnable = antisamyEnable;
    }

    public String getAntisamyPolicyFile() {
        return antisamyPolicyFile;
    }

    public void setAntisamyPolicyFile(String antisamyPolicyFile) {
        this.antisamyPolicyFile = antisamyPolicyFile;
    }
}
