package edu.health.controller;

import com.github.pagehelper.PageInfo;
import edu.health.core.base.controller.BaseController;
import edu.health.core.base.dto.BaseDomain;
import edu.health.core.mybatis.condition.MybatisCondition;
import edu.health.domain.Info;
import edu.health.mapper.InfoMapper;
import edu.health.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author 执笔
 * @date 2019/5/28 18:30
 */
@RequestMapping("info")
@Controller
public class InfoController extends BaseController {

    @Autowired
    private InfoService infoService;
    @Autowired
    private InfoMapper  infoMapper;

    /**
     * 列表
     *
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(BaseDomain page, Model model) {
        MybatisCondition condition = new MybatisCondition()
                .eq("userid", sessionUser().getId())
                .order("addtime", false)
                .page(page);
        PageInfo<Info> pageInfo = infoService.selectPage(condition);
        setModelAttribute(model, pageInfo);
        return "info/list";
    }

    /**
     * 录入健康数据
     *
     * @param model
     * @return
     */
    @GetMapping("add")
    public String add(Model model) {
        Info info = new Info();
        info.setUserid(sessionUser().getId());
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
        model.addAttribute("info", infoMapper.selectByPrimaryKey(id));
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
        if (info.getId() == null) {
            infoMapper.insertSelective(info);
        } else {
            infoMapper.updateByPrimaryKeySelective(info);
        }
        return redirect("list");
    }


    /**
     * 删除
     */
    @GetMapping("del/{id}")
    public String del(@PathVariable Integer id) {
        infoMapper.deleteByPrimaryKey(id);
        return refresh();
    }
}
