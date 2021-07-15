package com.sbstest.Dao.Mybatis;

import com.sbstest.Dao.Entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Created by 21510 on 2021/7/13.
 */
@Component
public interface UserDao {

    UserEntity selectByName(String name);



}
