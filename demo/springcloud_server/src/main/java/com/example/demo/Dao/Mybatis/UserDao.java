package com.example.demo.Dao.Mybatis;


import com.example.demo.Dao.Entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Created by 21510 on 2021/7/13.
 */
@Component
public interface UserDao {

    UserEntity selectByName(String name);



}
