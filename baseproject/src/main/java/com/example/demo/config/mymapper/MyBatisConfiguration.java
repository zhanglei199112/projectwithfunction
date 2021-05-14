package com.example.demo.config.mymapper;

import com.example.demo.mapper.MyBatchUpdateMapper;
import java.util.Properties;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MyBatisConfiguration {
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("com.example.demo.mapper");
//        Properties properties = new Properties();
//        properties.setProperty("mappers", MyBatchUpdateMapper.class.getName());
//        properties.setProperty("notEmpty", "false");
//        properties.setProperty("identity", "MYSQL");
//        properties.setProperty("order","BEFORE");
//        mapperScannerConfigurer.setProperties(properties);
//        return mapperScannerConfigurer;
//    }

}
