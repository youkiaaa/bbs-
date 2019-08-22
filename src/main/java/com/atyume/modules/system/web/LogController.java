package com.atyume.modules.system.web;

import com.atyume.core.annotation.SystemLog;
import com.atyume.core.utils.Result;
import com.atyume.modules.base.query.PageQuery;
import com.atyume.modules.system.po.Log;
import com.atyume.modules.system.service.LogService;
import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.atyume.modules.base.web.BaseCrudController;

import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController extends BaseCrudController<Log> {

    @Autowired
    private LogService logService;
    @RequiresPermissions("log:view")
    @GetMapping
    public String logPage(Model model) {
        return "system/log";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("log:view")
    @Override
    public Result queryList(Log log, PageQuery pageQuery) {
        return super.queryList(log, pageQuery);
    }

    @ResponseBody
    @GetMapping("/search")
    @RequiresPermissions("log:search")
    public List<Log> searchList(PageQuery pageQuery, @PathVariable("execDesc") String execDesc) {
        System.out.println("logController searching:"+execDesc);
        System.out.println("*********************************************************************");
        List<Log> list=logService.serarchDesc(execDesc);
        return list;
    }
}
