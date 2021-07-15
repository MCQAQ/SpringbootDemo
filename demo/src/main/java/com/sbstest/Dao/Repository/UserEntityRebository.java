package com.sbstest.Dao.Repository;

import com.sbstest.Dao.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by 21510 on 2021/7/9.
 */
@Repository
public interface UserEntityRebository extends JpaRepository<UserEntity,String>,JpaSpecificationExecutor<UserEntity>{

    UserEntity findByName(String name);

}
