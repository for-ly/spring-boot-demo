package com.example.demo.dao.bdp;

import com.example.demo.model.UserFunc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "UserFuncDao")
public interface UserFuncDao {
    UserFunc findById(Integer id);

    @Select("select * from user_funcs where deleted_at is null")
    @Results(
            id = "getUserFuncs", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "updated_at", property = "updatedAt"),
            @Result(column = "deleted_at", property = "deletedAt"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "func_id", property = "funcId"),
            @Result(column = "limit", property = "limit")
            }
    )
    List<UserFunc> getUserFuncs();
}
