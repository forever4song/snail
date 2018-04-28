package cn.foreversong.snail.springboot.dao;

import cn.foreversong.snail.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-27
 * Time: 上午7:34
 * Description: 用户 Jpa
 */
public interface UserJpa extends JpaRepository<User,Integer>{
    User findByName(String name);
}
