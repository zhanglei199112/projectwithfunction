package com.example.demo.mymapper;

import java.util.Set;
import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class MyBatchUpdateProvider extends MapperTemplate {

    public MyBatchUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String batchUpdateMapper(MappedStatement ms){
        //实体类对象
        Class<?> entityClass = super.getEntityClass(ms);
        //表名
        String tableName = super.tableName(entityClass);
        StringBuilder sqlBuilder = new StringBuilder();
        //开始拼接sql
        sqlBuilder.append("<foreach collection=\"list\" item=\"record\" separator=\";\">");
        //拼接update子句
        String updateClause = SqlHelper.updateTable(entityClass, tableName);
        sqlBuilder.append(updateClause);
        //拼接set子句
        //获取所有列信息
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);
        sqlBuilder.append("<set>");
        String idColumn = null;
        String idValue = null;
        //遍历所有列
        for (EntityColumn entityColumn:columns) {
            //这里暂不考虑联合主键
            if(entityColumn.isId()){
                //缓存主键的列名和值
                idColumn = entityColumn.getColumn();
                idValue = entityColumn.getColumnHolder("record");
            }else{
                //获取非主键列的列名
                String columnName = entityColumn.getColumn();
                String columnHolder = entityColumn.getColumnHolder("record");
                sqlBuilder.append(columnName).append(" = ").append(columnHolder).append(",");
            }
        }
        sqlBuilder.append("</set>");
        //拼接where子句
        sqlBuilder.append("where ").append(idColumn).append(" = ").append(idValue);
        sqlBuilder.append("</foreach>");
        return sqlBuilder.toString();
    }
}
