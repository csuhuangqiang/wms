package com.rickysoft.wms.mapper;

import com.github.abel533.entity.Example;
import com.rickysoft.wms.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> users=userMapper.select(new User());
        System.out.println(users.size());
    }

    @Test
    public void testSelectByExample() {
        User condition=new User();
        condition.setMinAge(28);
        condition.setMaxAge(32);

        Example example=new Example(User.class);
        Example.Criteria criteria=example.createCriteria();
        if(condition.getMinAge() != null) {
            criteria.andGreaterThan("age",condition.getMinAge());
        }
        if(condition.getMaxAge() != null) {
            criteria.andLessThan("age",condition.getMaxAge());
        }
        example.setOrderByClause("id desc");

        List<User> users=userMapper.selectByExample(example);
        //System.out.println(userMapper.selectCountByExample(example));
    }

    @Test
    public void testInsert() {
        User user=new User();
        user.setUsername("huangqiang");
        user.setPassword("123456");
        user.setGender(User.GENDER_MALE);
        user.setAge(31);
        user.setMobile("17666113010");
        user.setEmail("csuhuangqiang@163.com");
        userMapper.insert(user);
    }
}
