package edu.health.core.base.controller;


import edu.health.core.context.Constant;
import edu.health.domain.User;

/**
 * 管理员公用controller
 *
 * @author 执笔
 */
public abstract class BaseAdminController extends BaseController {

    /**
     * 登录的管理员
     *
     * @return
     */
    protected User sessionAdmin() {
        return (User) session.getAttribute(Constant.SESSION_ADMIN);
    }

}
