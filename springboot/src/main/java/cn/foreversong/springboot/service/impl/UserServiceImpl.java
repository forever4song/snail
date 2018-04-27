package cn.foreversong.springboot.service.impl;

import cn.foreversong.springboot.dao.UserJpa;
import cn.foreversong.springboot.model.User;
import cn.foreversong.springboot.mybatis.UserMapper;
import cn.foreversong.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-27
 * Time: 上午9:08
 * Description: 用户服务实现类
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserJpa userJpa;

    @Override
    public List<User> list() {
        return userMapper.selectByExample(null);
    }

    @Override
    public User sel(String name) {
        return userJpa.findByName(name);
    }

    @Override
    public User ins(User user) {
        return userJpa.save(user);
    }
}
