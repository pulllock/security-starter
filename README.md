# 说明

包含了一些常用的安全相关的starter，比如防止XSS、CROS、SQL注入、CRLF注入等等。使用的时候可以将本项目作为POM导入，并选择合适的start进行引入使用。该项目使用[https://github.com/dachengxi/parent-pom](https://github.com/dachengxi/parent-pom)作为父模块，可以根据实际需要选择保留或者去除，使用前请先将parent-pom发布到仓库中。

# starter列表

- `xss-spring-boot-starter`：防止xss攻击
- `cros-spring-boot-starter`：防止cros攻击
- `crlf-spring-boot-starter`：防止crlf攻击
- `sql-spring-boot-starter`：防止sql注入攻击

# starter使用方法和说明

所有的starter使用，首先需要将general-starter-parent进行导入，代码如下：

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>me.cxis</groupId>
            <artifactId>security-starter</artifactId>
            <version>${project.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## xss-spring-boot-starter使用方法

## cros-spring-boot-starter使用方法

## crlf-spring-boot-starter使用方法

## sql-spring-boot-starter使用方法

# xss

# cros

# crlf

# sql