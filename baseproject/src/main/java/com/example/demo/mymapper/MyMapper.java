package com.example.demo.mymapper;

import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;

public interface MyMapper<T> extends BaseSelectMapper<T>, BaseUpdateMapper<T>, BaseInsertMapper<T>, BaseDeleteMapper<T>,MyBatchUpdateMapper<T> {
}
