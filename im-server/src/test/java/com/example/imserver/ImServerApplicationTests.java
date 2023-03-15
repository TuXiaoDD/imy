package com.example.imserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 这是因为springbootTest启动时不会启动服务器，所以websocket就会报错，
 * 这个时候需要在注解中添加webEnvironment，给wevsocket提供测试环境：
 */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
class ImServerApplicationTests {

    @Test
    void contextLoads() {
    }

}
