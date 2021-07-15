package com.example.demo.config;

public class DataSourceHolder {
    private static ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    public static String get(){
        return dataSourceHolder.get();
    }

    public static void set(String s){
        dataSourceHolder.set(s);
    }

    public static void remove(){
        dataSourceHolder.remove();
    }
}
