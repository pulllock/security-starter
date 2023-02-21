# 说明

包含了一些常用的安全相关的starter，比如防止XSS、CORS、SQL注入、CRLF注入等等。使用的时候可以将本项目作为POM导入，并选择合适的start进行引入使用。该项目使用[https://github.com/dachengxi/parent-pom](https://github.com/dachengxi/parent-pom)作为父模块，可以根据实际需要选择保留或者去除，使用前请先将parent-pom发布到仓库中。

# starter列表

- `xss-spring-boot-starter`：防止xss攻击
- `cors-spring-boot-starter`：防止cors攻击
- `crlf-spring-boot-starter`：防止crlf攻击
- `sql-spring-boot-starter`：防止sql注入攻击

# starter使用方法和说明

所有的starter使用，首先需要将security-starter进行导入，代码如下：

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

## cors-spring-boot-starter使用方法

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

# cors

CORS全称Cross-origin resource sharing（跨域资源共享），默认情况下浏览器会限制从脚本中发起的跨域HTTP请求，默认的安全限制为同源策略，也就是只能访问同域下的内容，而CORS可以让不同域的应用能够无视同源策略。

CORS漏洞通常是由于Access-Control-Allow-Origin配置不当引起的，没有必要的话尽量不开启CORS，如果需要开启则需要使用白名单，不要使用*，另外要尽量避免使用Access-Control-Allow-Credentials

## 配置CORS

- 使用全局配置方式
- 基于过滤器的方式
- 使用`@CrossOrigin`注解的方式

### 使用全局配置方式

配置方式1：

```
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 尽量不要使用*
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PATCH", "DELETE", "PUT")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
```

配置方式2：

```
@Configuration
public class CorsConfigOther extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 尽量不要使用*
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PATCH", "DELETE", "PUT")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
```

### 基于过滤器方式

```
public class CorsFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        // 尽量不要使用*
        servletResponse.setHeader("Access-Control-Allow-Origin","*");
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        servletResponse.setHeader("Access-Control-Max-Age", "3600");
        servletResponse.setHeader("Access-Control-Allow-Headers", "*");
        chain.doFilter(request, servletResponse);
    }
}

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }
}
```

# crlf

crlf全称Carriage-Return Line-Feed（回车换行），crlf注入攻击可以任意插入恶意的HTTP头，HTTP报文以状态行开始，后面是HTTP首部，首部由多个首部字段组成，每个首部字段单独一行，首部后面是一个空行，然后是报文主体。也就是状态行和首部的每一行都是以CRLF结尾，首部和报文主体由两个CRLF分割。 一旦能够控制HTTP头中的字符来注入一些恶意的换行，就能注入一些恶意的Http Header，比如Set-cookie: xxxx，甚至还可以注入一些其他的代码，来造成XSS注入攻击等。

另外CRLF注入还可以针对日志进行，在参数中注入恶意的CRLF后，如果服务端将日志打印出来，可以造成日志的混乱，如果是针对日志做了一些其他的操作，可能会导致出现不可预期的错误。

## 解决方案

- 针对HTTP头的CRLF，可以过滤掉`\r`、`\n`等换行结束符，避免输入的数据污染其他的HTTP首部字段
- 针对日志的CRLF，可以在打印日志的地方过滤掉`\r`、`\n`等换行符，或者统一处理掉日志中的换行符
- 如果使用log4j，可以在log4j配置文件中配置`%enc{%m}{CRLF}`来对换行符进行转义
- 也可以使用`OWASP Security Logging`库来对log4j和logback进行处理
- 日志中如果打印堆栈信息也有日志注入风险，需要统一处理下

# sql

- 可以使用过滤器过滤掉一些sql关键字和特殊字符，正则：`select|update|and|or|delete|insert|truncate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|table|char|declare|sitename|xp_cmdshell|like|from|grant|use|group_concat|column_name|information_schema.columns|table_schema|union|where|order|by|'\\*|\\;|\\-|\\--|\\+|\\,|\\//|\\/|\\%|\\#`
- 使用mybatis的时候要使用`#{}`，不要使用`${}`，必须要用`${}`的地方要使用白名单或者不要有用户输入