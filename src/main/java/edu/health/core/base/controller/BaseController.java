package edu.health.core.base.controller;


import edu.health.core.context.Constant;
import edu.health.core.util.ParamUtils;
import edu.health.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 执笔
 * @date 2019/4/9 21:48
 */
public abstract class BaseController implements Constant {

    @Autowired
    protected HttpSession        session;
    @Autowired
    private   HttpServletRequest request;

    protected User sessionUser() {
        return (User) session.getAttribute(SESSION_USER);
    }


    /**
     * 将数据放在model里面
     *
     * @param model
     * @param attributes
     */
    protected void setModelAttribute(Model model, Object... attributes) {
        if (attributes != null && attributes.length > 0) {
            for (Object object : attributes) {
                if (null != object) {
                    model.addAttribute(object);
                }
            }
        }
        model.addAttribute("requestUrl", request.getRequestURI() + "?" + ParamUtils.params2String(request));
    }

    /**
     * 刷新页面
     *
     * @return
     * @Date 2016年2月24日 上午11:16:22
     */
    protected String refresh() {
        return "redirect:" + request.getHeader("Referer");
    }

    /**
     * 重定向
     *
     * @param viewName
     * @return
     */
    protected String redirect(String viewName) {
        return "redirect:" + viewName;
    }

    /**
     * 得到博客类型
     *
     * @return
     */
    protected String getBlogType() {
        Object blog_type = session.getAttribute("blog_type");
        if (null == blog_type) {
            return "事件";
        }
        return blog_type.toString();
    }


}
