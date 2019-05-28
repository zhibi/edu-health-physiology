package edu.health.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.health.core.base.BaseController;
import edu.health.domain.Personal;
import edu.health.domain.User;
import edu.health.mapper.PersonalMapper;
import edu.health.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;
import zhibi.utils.request.ParamUtils;

import java.text.ParseException;

/**
 * @author 执笔
 * @date 2019/5/22 15:03
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {


    @Autowired
    private UserMapper     userMapper;
    @Autowired
    private PersonalMapper personalMapper;

    /**
     * 用户列表
     *
     * @param user
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(User user, @RequestParam(defaultValue = "1") Integer pageNum, Model model) {
        Example example = Example.getInstance();
        example.addParam("type", "admin", ExampleType.Operation.NOTEQ)
                .addParam("name", user.getName(), ExampleType.Operation.LIKE)
                .addParam("username", user.getUsername(), ExampleType.Operation.LIKE);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByExample(example), 5);
        model.addAttribute(pageInfo);
        model.addAttribute("url", request.getRequestURI() + "?" + ParamUtils.params2String(request));
        return "user/list";
    }


    /**
     * 用户详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        User user = userMapper.selectByPrimaryKey(id);
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
        if (user.getPassword() == null || user.getPassword().isEmpty()) user.setPassword(null);
        userMapper.updateByPrimaryKeySelective(user);
        return refresh();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("del/{id}")
    public String del(@PathVariable Integer id) {
        userMapper.deleteByPrimaryKey(id);
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
    public String add(User user,String password2) throws ParseException {
        if(!user.getPassword().equals(password2)){
            request.setAttribute("msg", "两次密码不一样");
            return "register";
        }
        user.setStatus(1);
        userMapper.insert(user);
        return "redirect:/login";
    }

    /**
     * 补存档案
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("personal/{id}")
    public String personal(@PathVariable Integer id, Model model) {
        Personal personal = personalMapper.selectByPrimaryKey(id);
        if (personal == null) personal = new Personal();
        personal.setId(id);
        model.addAttribute(personal);
        return "user/personal";
    }

    /**
     * 补存档案
     *
     * @param personal
     * @return
     */
    @RequestMapping("updatePersonal")
    public String updatePersonal(Personal personal) {
        Personal personal1 = personalMapper.selectByPrimaryKey(personal.getId());
        if (personal1 == null) {
            personalMapper.insertSelective(personal);
        } else {
            personalMapper.updateByPrimaryKeySelective(personal);
        }
        return refresh();
    }
}
