package com.sbstest.Service;

import com.sbstest.Dao.Entity.UserEntity;
import com.sbstest.Dao.Repository.UserEntityRebository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;


/**
 * Created by 21510 on 2021/7/9.
 */
@Service
public class UserService {

    @Autowired
    private UserEntityRebository userEntityRebository;

    public List<UserEntity> getByName(String s) {
//        return userEntityRebository.findByName(s);
        return userEntityRebository.findAll((root , query, builder) -> {
            query.distinct(true);
            Predicate predicate = builder.disjunction();

            predicate.getExpressions().add(builder.equal(root.get("name"),s));

            return predicate;
        });
    }
}
