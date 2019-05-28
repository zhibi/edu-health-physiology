package edu.health.controller;

import edu.health.core.base.BaseController;
import edu.health.core.context.Constant;
import edu.health.domain.Doctor;
import edu.health.domain.Info;
import edu.health.domain.Personal;
import edu.health.domain.User;
import edu.health.mapper.PersonalMapper;
import edu.health.service.DoctorService;
import edu.health.service.InfoService;
import edu.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zhibi.commons.exception.MessageException;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;

import java.util.List;

/**
 * @author 执笔
 * @date 2019/5/22 14:55
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService    userService;
    @Autowired
    private InfoService    infoService;
    @Autowired
    private DoctorService  doctorService;
    @Autowired
    private PersonalMapper personalMapper;


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
        model.addAttribute("tipMsg", session.getAttribute("tipMsg"));
        session.removeAttribute("tipMsg");
        if (sessionUser().getType().equals("user")) {
            Example    example  = Example.getInstance().addParam("userid", sessionUser().getId()).addOrder("addtime", ExampleType.OrderType.DESC);
            List<Info> infoList = infoService.selectByExample(example);
            model.addAttribute("infoList", infoList);
        }
        if (sessionUser().getType().equals("doctor")) {
            Doctor doctor = doctorService.selectByPrimaryKey(sessionUser().getId());
            if (doctor == null) {
                doctor = new Doctor();
                doctor.setId(sessionUser().getId());
            }
            model.addAttribute("doctor", doctor);
        }
        return "index";
    }

    @RequestMapping({"index-2"})
    public String index2(Model model) {
        model.addAttribute("tipMsg", session.getAttribute("tipMsg"));
        session.removeAttribute("tipMsg");

        Personal personal = personalMapper.selectByPrimaryKey(sessionUser().getId());
        if (personal == null) personal = new Personal();
        model.addAttribute("personal", personal);

        return "index-2";
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
        if (user.getStatus() == 0) {
            request.setAttribute("msg", "用户不可用");
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
        session.removeAttribute(Constant.SESSION.ADMIN);
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
        User user = userService.selectByPrimaryKey(sessionUser().getId());
        user.setPassword(newpass);
        userService.updateByPrimaryKey(user);
        return redirect("logout");
    }

    /**
     * 个人信息
     *
     * @param model
     * @return
     */
    @RequestMapping("info")
    public String info(Model model) {
        Doctor doctor = doctorService.selectByPrimaryKey(sessionUser().getId());
        if (null == doctor) {
            doctor = new Doctor();
            doctor.setId(sessionUser().getId());
        }
        model.addAttribute("doctor", doctor);
        return "info";
    }

    /**
     * 更新医生个人信息
     *
     * @param doctor
     * @return
     */
    @RequestMapping("update")
    public String update(Doctor doctor) {
        if (doctorService.selectByPrimaryKey(doctor.getId()) == null)
            doctorService.insertSelective(doctor);
        else
            doctorService.updateByPrimaryKeySelective(doctor);
        return refresh();
    }

    /**
     * 在线交流
     */
    @GetMapping("zxjl")
    public String zxjl(Model model) {
        User       user = new User();
        List<User> list = null;
        if (sessionUser().getType().equals("doctor")) {
            user.setType("user");
            list = userService.select(user);
        } else {
            user.setType("doctor");
            list = userService.select(user);
        }
        model.addAttribute("list", list);
        return "zxjl";
    }
}
