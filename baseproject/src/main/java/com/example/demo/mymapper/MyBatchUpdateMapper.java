package com.example.demo.mymapper;

import java.util.List;
import org.apache.ibatis.annotations.UpdateProvider;

public interface MyBatchUpdateMapper<T> {

    @UpdateProvider(type = MyBatchUpdateProvider.class,method = "dynamicSQL")
    void batchUpdateMapper(List<T> list);
}
