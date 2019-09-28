package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoggingTest.class)
public class LoggingTest {
    @Test
    public void logging() {
        new JDK().run();
        new Commons().run();
        new Log4j1().run();
        new Log4j2().run();
        new Slf4jLog().run();
    }
}
