package com.atyume.modules.system.web;

import com.atyume.core.annotation.SystemLog;
import com.atyume.core.utils.Result;
import com.atyume.modules.system.po.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.atyume.modules.base.web.BaseCrudController;
import com.atyume.modules.system.enums.GroupType;
import com.atyume.modules.system.service.GroupService;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/group")
public class GroupController extends BaseCrudController<Group> {

    @Autowired
    private GroupService groupService;

    @SystemLog("用户管理查看用户组")
    @GetMapping
    public String groupPage(Model model) {
        model.addAttribute("groupTypeList", GroupType.values());
        return "system/group";
    }

    @ResponseBody
    @PostMapping("/create")
    @SystemLog("用户管理创建用户组")
    @Override
    public Result create(@Validated Group group) {
        groupService.create(group);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/update")
    @SystemLog("用户管理更新用户组")
    @Override
    public Result update(@Validated Group group) {
        groupService.updateNotNull(group);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete-batch")
    @SystemLog("用户管理删除用户组")
    @Override
    public Result deleteBatchByIds(@NotNull @RequestParam("id")  Object[] ids) {
        super.deleteBatchByIds(ids);
        return Result.success();
    }

}
