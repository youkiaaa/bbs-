package com.atyume.modules.system.web;

import com.atyume.core.utils.Result;
import com.atyume.modules.base.query.PageQuery;
import com.atyume.modules.system.po.Avator;
import com.atyume.modules.system.po.Bbs;
import com.atyume.modules.system.service.AvatorService;
import com.atyume.modules.system.service.BbsService;
import com.atyume.modules.system.vo.BbsVO;
import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.atyume.core.utils.BaseController;
import com.atyume.core.utils.Constants;
import com.atyume.modules.system.dto.ResourceDto;
import com.atyume.modules.system.service.ResourceService;
import com.atyume.modules.system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    @Autowired
    private AvatorService avatorService;

    @Autowired
    private BbsService bbsService;

    @RequestMapping("/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        System.out.println("check login");
        logger.info("begin to login");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
            error = "登陆失败次数过多";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "system/login";
    }

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("come to main");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Set<String> permissions = userService.queryPermissions(username);
        List<ResourceDto> menus = resourceService.findMenus(permissions);
        Long userid = userService.queryId(username);
        String picpath = avatorService.selectPathByActive(userid);
        model.addAttribute("sourcePath", picpath);
        StringBuilder dom = new StringBuilder();
        getMenuTree(menus, Constants.MENU_ROOT_ID, dom);
        model.addAttribute(Constants.MENU_TREE, dom);
        return "base/main";
    }

    @GetMapping("/index")
    public String showList(Bbs bbs, PageQuery pageQuery,Model model) {
        Page<Bbs> page = (Page<Bbs>) bbsService.queryList(bbs, pageQuery);
        List<BbsVO> bbsVOS = new ArrayList<>();
        for (Bbs b : page) {
            BbsVO bbsVO = new BbsVO(b);
            String username = userService.selectName(b.getUserid());
            bbsVO.setUsername(username);
            String picPath = avatorService.selectPathByActive(b.getUserid());
            bbsVO.setPicPath(picPath);
            bbsVOS.add(bbsVO);
        }
        model.addAttribute("bbsVoList",bbsVOS);
        return "system/index";
    }


    private List<ResourceDto> getMenuTree(List<ResourceDto> source, Long pid, StringBuilder dom) {
        List<ResourceDto> target = getChildResourceByPId(source, pid);
        target.forEach(res -> {
            dom.append("<li class='treeview'>");
            dom.append("<a href='" + res.getUrl() + "'>");
            dom.append("<i class='" + res.getIcon() + "'></i>");
            dom.append("<span>" + res.getName() + "</span>");
            if (Constants.SHARP.equals(res.getUrl())) {
                dom.append("<span class='pull-right-container'><i class='fa fa-angle-left pull-right'></i> </span>");
            }
            dom.append("</a>");
            dom.append("<ul class='treeview-menu'>");
            res.setChildren(getMenuTree(source, res.getId(), dom));
            dom.append("</ul>");
            dom.append("</li>");
        });
        return target;
    }

    private List<ResourceDto> getChildResourceByPId(List<ResourceDto> source, Long pId) {
        List<ResourceDto> child = new ArrayList<>();
        source.forEach(res -> {
            if (pId.equals(res.getParentId())) {
                child.add(res);
            }
        });
        return child;
    }
}
