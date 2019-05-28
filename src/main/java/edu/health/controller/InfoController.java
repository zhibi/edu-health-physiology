package edu.health.controller;

import com.github.pagehelper.PageInfo;
import edu.health.core.base.BaseController;
import edu.health.domain.Info;
import edu.health.service.InfoService;
import edu.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;

import java.util.Date;

@RequestMapping("info")
@Controller
public class InfoController extends BaseController {

    @Autowired
    private InfoService infoService;
    @Autowired
    private UserService userService;

    /**
     * 列表
     *
     * @param info
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(Info info, Page page, Model model) {
        Example example = Example.getInstance()
                .addParam("u.name", info.getUsername(), ExampleType.Operation.LIKE)
                .addOrder("addtime", ExampleType.OrderType.DESC);
        if (!sessionUser().getType().equals("admin") && !sessionUser().getType().equals("doctor")) {
            example.addParam("userid", sessionUser().getId());
        }
        PageInfo<Info> pageInfo = infoService.selectByExample(example, page);
        setModelAttribute(model, pageInfo);
        return "info/list";
    }

    /**
     * 录入健康数据
     *
     * @param model
     * @param userid
     * @return
     */
    @GetMapping("add/{userid}")
    public String add(Model model, @PathVariable Integer userid) {
        Info info = new Info();
        info.setUserid(userid);
        model.addAttribute("info", info);
        return "info/add";
    }

    /**
     * 详情
     *
     * @param model
     * @return
     */
    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable Integer id) {
        model.addAttribute("info", infoService.selectByPrimaryKey(id));
        return "info/add";
    }

    /**
     * 录入健康数据
     *
     * @param info
     * @return
     */
    @PostMapping("add")
    public String add(Info info) {
        info.setAddtime(new Date());
        String tip = "";
        if (info.getHeart() < 90) tip += "血氧太低报警  ";
        if (info.getHeart() > 100) tip += "血氧太高报警    ";
        if (info.getBlood1() < 60) tip += "低血压太低报警   ";
        if (info.getBlood1() > 90) tip += "低血压太高报警   ";
        if (info.getBlood2() < 90) tip += "高血压太低报警   ";
        if (info.getBlood2() > 140) tip += "高血压太高报警  ";
        if (info.getPulse() < 60) tip += "脉搏太低报警 ";
        if (info.getPulse() > 100) tip += "脉搏太高报警    ";
        info.setTip(tip);
        if (info.getId() == null) {
            infoService.insertSelective(info);
        } else {
            infoService.updateByPrimaryKeySelective(info);
        }

        return redirect("list");
    }


    /**
     * 删除
     */
    @GetMapping("del/{id}")
    public String del(@PathVariable Integer id) {
        infoService.deleteByPrimaryKey(id);
        return refresh();
    }

}
