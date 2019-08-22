package com.atyume.modules.system.web;

import com.atyume.core.annotation.SystemLog;
import com.atyume.core.utils.Result;
import com.atyume.modules.base.query.PageQuery;
import com.atyume.modules.system.po.Bbs;
import com.atyume.modules.system.service.BbsService;
import com.atyume.modules.system.vo.BbsVO;
import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.atyume.modules.base.web.BaseCrudController;
import com.atyume.modules.system.service.UserService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bbs")
public class BbsController extends BaseCrudController<Bbs> {
    @Autowired
    private BbsService bbsService;
    @Autowired
    private UserService userService;


    @GetMapping
    @RequiresPermissions("bbs:view")
    public String bbsPage(Model model) {
        model.addAttribute("bbsList", bbsService.queryAll());
        return "system/bbs";
    }

    @ResponseBody
    @GetMapping("/list")
    @SystemLog("留言管理查看留言")
    @RequiresPermissions("bbs:view")
    @Override
    public Result<List<BbsVO>> queryList(Bbs bbs, PageQuery pageQuery) {
        Page<Bbs> page = (Page<Bbs>) bbsService.queryList(bbs, pageQuery);
        List<BbsVO> bbsVOS = new ArrayList<>();
        for (Bbs b : page) {
            BbsVO bbsVO = new BbsVO(b);
            String username=userService.selectName(b.getUserid());
            bbsVO.setUsername(username);
            bbsVOS.add(bbsVO);
        }
        return Result.success(bbsVOS).addExtra("total", page.getTotal());
    }

    @ResponseBody
    @RequiresPermissions("bbs:create")
    @SystemLog("留言管理创建留言")
    @PostMapping("/create")
    @Override
    public Result create(@Validated Bbs bbs) {
        System.out.println("title:"+bbs.getTitle()+"main:"+bbs.getMain());
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        System.out.println("username:"+username);
        Long userid=userService.queryId(username);        //error
        System.out.println("userid:"+userid);
        bbs.setUserid(userid);
        bbsService.create(bbs);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("bbs:update")
    @SystemLog("留言管理更新留言")
    @PostMapping("/update")
    @Override
    public Result update(@Validated Bbs bbs) {
        bbsService.updateNotNull(bbs);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("bbs:delete")
    @SystemLog("留言管理删除留言")
    @PostMapping("/delete-batch")
    @Override
    public Result deleteBatchByIds(@NotNull @RequestParam("id") Object[] ids) {
        super.deleteBatchByIds(ids);
        return Result.success();
    }

}