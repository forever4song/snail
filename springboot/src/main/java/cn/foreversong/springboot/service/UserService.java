package cn.foreversong.springboot.service;

import cn.foreversong.springboot.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-27
 * Time: 上午9:08
 * Description: 用户服务
 */

public interface UserService {
    List<User> list();

    User sel(String name);

    User ins(User user);
}
