package com.atyume.modules.system.web;

import com.atyume.core.utils.Result;
import com.atyume.modules.base.query.PageQuery;
import com.atyume.modules.base.web.BaseCrudController;
import com.atyume.modules.system.mapper.MoneyLogMapper;
import com.atyume.modules.system.po.MoneyLog;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mondetail")
public class MoneyLogController extends BaseCrudController<MoneyLog> {
    @Autowired
    private MoneyLogMapper moneyLogMapper;
    @GetMapping
    public String detailPage(Model model) {
        return "system/mondetail";
    }

    @ResponseBody
    @GetMapping("/list")
    @Override
    public Result queryList(MoneyLog moneyLog, PageQuery pageQuery) {
        return super.queryList(moneyLog, pageQuery);
    }

}
