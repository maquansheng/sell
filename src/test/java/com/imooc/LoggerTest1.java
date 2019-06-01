package com.imooc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
日志的使用
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest1 {
    private final Logger logger = LoggerFactory.getLogger(LoggerTest1.class);
    @Test
    public void test() {
        logger.trace("trance...");
        logger.debug("debug...");
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");
    }

}
