package com.example.demo.dao.index;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "IndexDao")
public interface IndexDao {
    int findById(int id);
}
