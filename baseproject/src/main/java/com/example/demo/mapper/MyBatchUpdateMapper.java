package com.example.demo.mapper;

import com.example.demo.mymapper.MyBatchUpdateProvider;
import java.util.List;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

@RegisterMapper
public interface MyBatchUpdateMapper<T> {

    @UpdateProvider(type = MyBatchUpdateProvider.class,method = "dynamicSQL")
    void batchUpdateMapper(List<T> list);
}
