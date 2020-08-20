package com.module.zhupeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhupeng
 */
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class ZModulePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZModulePlatformApplication.class, args);
    }

}