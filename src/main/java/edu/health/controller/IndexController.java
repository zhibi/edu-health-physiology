package edu.health.controller;


import edu.health.core.base.controller.BaseController;
import edu.health.core.exception.MessageException;
import edu.health.core.mybatis.condition.MybatisCondition;
import edu.health.domain.Info;
import edu.health.domain.User;
import edu.health.mapper.UserMapper;
import edu.health.service.InfoService;
import edu.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author 执笔
 * @date 2019/5/22 14:55
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private UserMapper  userMapper;


    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping("login")
    public String login() {
        return "login";
    }


    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping({"/", "index"})
    public String index(Model model) {
        MybatisCondition condition = new MybatisCondition()
                .eq("userid", sessionUser().getId())
                .order("addtime", false)
                .page(1, Integer.MAX_VALUE);
        List<Info> infoList = infoService.selectPage(condition).getList();
        model.addAttribute("infoList", infoList);
        return "index";
    }


    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public String login(String username, String password) {
        User user = userService.login(username, password);
        if (null == user) {
            request.setAttribute("msg", "用户名或者密码错误");
            return "login";
        }
        session.setAttribute("sessionAdmin", user);
        return "redirect:index";
    }


    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        session.removeAttribute("sessionAdmin");
        return redirect("index");
    }

    /**
     * 修改密码页面
     *
     * @return
     */
    @RequestMapping("modify")
    public String modify() {
        return "modify";
    }

    /**
     * 修改密码
     *
     * @param pass
     * @param newpass
     * @param confimpass
     * @return
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public String modify(String pass, String newpass, String confimpass) {
        if (!newpass.equals(confimpass)) {
            throw new MessageException("两次密码不一样");
        }
        if (!sessionUser().getPassword().equalsIgnoreCase(pass)) {
            throw new MessageException("原密码错误");
        }
        User user = userMapper.selectByPrimaryKey(sessionUser().getId());
        user.setPassword(newpass);
        userMapper.updateByPrimaryKey(user);
        return redirect("logout");
    }
}
