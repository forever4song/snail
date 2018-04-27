package cn.foreversong.springboot.controller;

import cn.foreversong.springboot.model.User;
import cn.foreversong.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-27
 * Time: 上午7:33
 * Description: 用户控制器
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
    public List<User> list(){
        log.info("list begin");
        return userService.list();
    }

    @GetMapping(value = "sel/{name}")
    public User sel(@PathVariable(value = "name") String name){
        log.info("sel begin");
        return userService.sel(name);
    }

    @GetMapping(value = "/ins")
    public User ins(User user){
        user.setTime(new Date());
        return userService.ins(user);
    }

}
