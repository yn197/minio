package com.yn.minioquickstart;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yn.minioquickstart.mapper")
@Slf4j
public class MinioQuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinioQuickstartApplication.class, args);
		log.info("MinioQuickstartApplication启动成功了");
    }

}
