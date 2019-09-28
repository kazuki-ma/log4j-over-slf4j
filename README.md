# log4j-over-slf4j
slf4j/lobcak にログ出力先を統一する example

# spring-boot の場合

2019年後半のファイナルアンサーはこれっぽい。

```groovy
dependencyManagement {
    imports {
        mavenBom org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
    }
}

dependenciss {
    implementation 'org.springframework.boot:spring-boot-starter-logging' // 実際は starter-web の依存とかに入ってることが多い
    runtimeOnly 'org.slf4j:log4j-over-slf4j' // log4j 1.x だけ個別にルーティングする必要がある。依存に無ければ不要だが入れておくべき
}

configurations.all {
    exclude group: 'log4j' // = Old only log4j impl.
    exclude group: 'org.apache.logging.log4j', module: 'log4j-slf4j-impl'// = SLF4J > Log4J 2.x. (Apache Side)
    exclude group: 'org.slf4j', module: 'slf4j-log4j12' // = SLF4J > Log4J 1.x. (SLF4J Side)
    exclude group: 'org.slf4j', module: 'slf4j-jdk14' // = SLF4J > JDK14.
    exclude group: 'commons-logging', module: 'commons-logging-api' // Replaced by jcl-over-slf4j
    exclude group: 'commons-logging', module: 'commons-logging' // Implementation is not needed.
}
```

ちなみによく見る以下は spring-boot が context 初期化時にやってくれるので不要。

```java
public class XxxApplication {
    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }
}
```

Example
=======
* [pure java](test/build.gradle)
* [spring-boot](test/build.gradle)

Test
=====
`./gradlw clean test` でテスト実行した後、レポートを開くことで実際に取得された logger の型とそれが含まれている jar ファイルパスが出力されます。

レポートは `test-spring/build/reports/tests/test/index.html` 等に出力されています。

```text:実施の例
00:21:22.126 [MDC[logger]=] INFO  com.example.JDK - log by class java.util.logging.Logger
00:21:22.130 [MDC[logger]=] INFO  com.example.JDK - logger class jar: jrt:/java.logging/java/util/logging/Logger.class
00:21:22.131 [MDC[logger]=] INFO  com.example.Commons - log by class org.apache.commons.logging.LogAdapter$Slf4jLocationAwareLog
00:21:22.132 [MDC[logger]=] INFO  com.example.Commons - logger class jar: jar:file:~/.gradle/caches/modules-2/files-2.1/org.springframework/spring-jcl/5.1.9.RELEASE/7c372790c999777d20f364960cf557dd74f890cf/spring-jcl-5.1.9.RELEASE.jar!/org/apache/commons/logging/LogAdapter$Slf4jLocationAwareLog.class
00:21:22.136 [MDC[logger]=] INFO  com.example.Log4j1 - log by class org.apache.log4j.Logger
00:21:22.137 [MDC[logger]=] INFO  com.example.Log4j1 - logger class jar: jar:file:~/.gradle/caches/modules-2/files-2.1/org.slf4j/log4j-over-slf4j/1.7.28/2466316c2c59e23c02490e3feec3a68372e45498/log4j-over-slf4j-1.7.28.jar!/org/apache/log4j/Logger.class
00:21:22.187 [MDC[logger]=org.apache.logging.slf4j.SLF4JLogger] INFO  com.example.Log4j2 - log by class org.apache.logging.slf4j.SLF4JLogger
00:21:22.192 [MDC[logger]=org.apache.logging.slf4j.SLF4JLogger] INFO  com.example.Log4j2 - logger class jar: jar:file:~/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-to-slf4j/2.11.2/6d37bf7b046c0ce2669f26b99365a2cfa45c4c18/log4j-to-slf4j-2.11.2.jar!/org/apache/logging/slf4j/SLF4JLogger.class
00:21:22.193 [MDC[logger]=ch.qos.logback.classic.Logger] INFO  com.example.Slf4jLog - log by class ch.qos.logback.classic.Logger
00:21:22.194 [MDC[logger]=ch.qos.logback.classic.Logger] INFO  com.example.Slf4jLog - logger class jar: jar:file:~/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.2.3/7c4f3c474fb2c041d8028740440937705ebb473a/logback-classic-1.2.3.jar!/ch/qos/logback/classic/Logger.class
```

* JDK logger は通常の jrt に実装があります
* `commons-logging` は `spring-jcl-5.1.9.RELEASE.jar` に含まれるクラスファイルに差し替わっています。
  * Javadoc [org\.apache\.commons\.logging \(Spring Framework 5\.1\.10\.RELEASE API\)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/apache/commons/logging/package-summary.html) 
  * 解説は [Spring Framework 5\.0 コア機能の主な変更点 \- Qiita](https://qiita.com/kazuki43zoo/items/b4f58f3ef2a8a43ab5eb#spring-jcl%E3%83%A2%E3%82%B8%E3%83%A5%E3%83%BC%E3%83%AB%E3%81%8C%E8%BF%BD%E5%8A%A0%E3%81%95%E3%82%8C%E3%82%8B-thumbsup) が詳しいです
* Log4j 1.x は実装がオリジナルから `org.slf4j:log4j-over-slf4j:1.7.28` に差し替わっています
* Log4j 2.x は `org.apache.logging.log4j:log4j-to-slf4j:2.11.2` です
* Slf4j はいつも通り
