package edu.health.controller;

import edu.health.core.base.controller.BaseController;
import edu.health.domain.User;
import edu.health.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 执笔
 * @date 2019/5/22 15:03
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {


    @Autowired
    private UserMapper userMapper;


    /**
     * 用户详情
     *
     * @param model
     * @return
     */
    @RequestMapping("detail")
    public String detail(Model model) {
        User user = userMapper.selectByPrimaryKey(sessionUser().getId());
        model.addAttribute(user);
        return "user/detail";
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("update")
    public String update(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(null);
        }
        userMapper.updateByPrimaryKeySelective(user);
        return refresh();
    }


    /**
     * 添加用户
     *
     * @return
     */
    @GetMapping("add")
    public String add() {
        return "register";
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("add")
    public String add(User user, String password2) {
        if (!user.getPassword().equals(password2)) {
            request.setAttribute("msg", "两次密码不一样");
            return "register";
        }
        userMapper.insert(user);
        return "redirect:/login";
    }
}
