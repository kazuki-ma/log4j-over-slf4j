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
