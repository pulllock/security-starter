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

XSS全称Cross-site scripting（跨站脚本），是代码注入的一种，允许恶意用户将包含了HTML以及客户端脚本语言的恶意代码注入到网页上，正常用户在加载和执行了这些恶意代码后，攻击者可能会得到更高的权限、一些隐私的内容、Session、Cookie等。

## xss危害

- 获取Cookie，得到敏感信息
- 获取更高权限
- 流量劫持，可以在一些访问量很大的页面上利用XSS共计一些其他的网站，实现DoS攻击效果
- 篡改正常的页面，实现钓鱼攻击
- 获取用户的信息，利用用户的信息执行一些其他的操作

## xss分类

- 反射型XSS：也叫非持久型XSS，攻击者发送一个XSS代码的请求到服务端，服务端处理完后又把带有XSS的数据发送到浏览器，浏览器解析了带有XSS数据的代码后就会产生XSS漏洞
- 存储型XSS：也叫持久性XSS，攻击者提交一个XSS代码到服务端，服务端接收并存储，后续当攻击者或者用户访问某些页面的时候，之前存储的XSS代码会被浏览器进行解析，造成XSS漏洞。存储型XSS是最危险的一种跨站脚本攻击漏洞
- DOM型XSS：DOM型XSS不需要经过服务端处理，是一种基于DOM的漏洞，通过URL传参控制触发

## XSS防范

防范XSS最主要的办法是对用户输入的内容进行过滤，并对一些危险的输入进行清理或者进行转义

- 对on事件进行过滤
- 对script关键字进行过滤
- 对`<, >, ", ', =`等特殊符号进行过滤或编码

## 方案

- 自定义过滤器，配置需要过滤的字符名单
- 使用OWASP的AntiSamy库进行处理
- 使用OWASP的ESAPI库进行处理
- 使用OWASP Java HTML Sanitizer库进行处理
- 使用jsoup库进行处理

### 使用OWASP的AntiSamy库进行处理

使用OWASP的AntiSamy库进行处理，可以将危险的内容清理掉，需要依赖策略文件，可以使用自带的策略文件也可以自定义策略文件，AntiSamy自带的策略文件有如下：

- `antisamy-anythinggoes.xml`：允许HTML、CSS、JavaScript通过，非常危险
- `antisamy-ebay.xml`：适用于电商网站，允许用户输入HTML脚本作为页面的一部分，相对安全
- `antisamy-myspace.xml`：适用于社交网站，允许用户输入作为整个页面，相对危险
- `antisamy-slashdot.xml`：适用于新闻网站的评论过滤
- `antisamy-tinymce.xml`：只允许文本格式，相对安全

### 使用OWASP的ESAPI库进行处理

使用OWASP的ESAPI库进行处理，可以将标签进行编码，需要依赖配置文件，可以从https://github.com/ESAPI/esapi-java-legacy下载配置文件：

- `ESAPI.properties`
- `validation.properties`
- `esapi-java-logging.properties`

## 使用OWASP Java HTML Sanitizer库进行处理

使用OWASP Java HTML Sanitizer库进行处理，不需要依赖配置文件，可以自定义白名单，也可以使用内置白名单。

### 使用jsoup库进行处理

使用jsoup库进行处理，可以将危险的内容清除掉，可以使用内置的白名单，也可以自定义白名单。内置的白名单如下：

- none：只保留标签内文本内容
- simpleText：简单的文本标签，包含：`b,em,i,strong,u`
- basic：基本的标签，包含：`a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span,strike,strong,sub,sup,u,ul`
- basicWithImages：基本标签加上img标签，包含：基本标签和`img,src,align,alt,height,width,title`
- relaxed：在basicWithImages基础上又增加了部分标签，包含：`a,b,blockquote,br,caption,cite,code,col,colgroup,dd,div,dl,dt,em,h1,h2,h3,h4,h5,h6,i,img,li,ol,p,pre,q,small,span,strike,strong,sub,sup,table,tbody,td,tfoot,th,thead,tr,u,ul`

# cros

# crlf

# sql