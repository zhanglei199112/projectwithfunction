package com.example.demo.aop;

import com.example.demo.config.DataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Before("execution(public * com.example.demo.service..*.sel*(..))")
    public void changeDataSource(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        //获取id，如果id为单数则用第一个数据源，如果为偶数则用第二个数据源
        Integer id = (Integer) args[0];
        if(id%2==1){
            DataSourceHolder.set("dbOne");
        }else{
            DataSourceHolder.set("dbTwo");
        }
    }

    @After("execution(public * com.example.demo.service..*.*(..))")
    public void setDataSource2test02() {
        DataSourceHolder.remove();
    }
}
