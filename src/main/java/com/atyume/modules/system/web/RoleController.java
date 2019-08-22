package com.atyume.modules.system.web;

import com.atyume.core.annotation.SystemLog;
import com.atyume.core.utils.Result;
import com.atyume.modules.system.po.Role;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.atyume.modules.base.web.BaseCrudController;
import com.atyume.modules.system.service.ResourceService;
import com.atyume.modules.system.service.RoleService;

import javax.validation.constraints.NotNull;


@Controller
@RequestMapping("/role")
public class RoleController extends BaseCrudController<Role> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    @SystemLog("角色管理查看角色")
    @RequiresPermissions("role:view")
    public String rolePage(Model model) {
        model.addAttribute("resourceList", resourceService.queryAll());
        Result.success();
        return "system/role";
    }

    @ResponseBody
    @RequiresPermissions("role:create")
    @SystemLog("角色管理创建角色")
    @PostMapping("/create")
    @Override
    public Result create(@Validated Role role) {
        roleService.create(role);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("role:update")
    @SystemLog("角色管理更新角色")
    @PostMapping("/update")
    @Override
    public Result update(@Validated Role role) {
        roleService.updateNotNull(role);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("role:delete")
    @SystemLog("角色管理删除角色")
    @PostMapping("/delete-batch")
    @Override
    public Result deleteBatchByIds(@NotNull @RequestParam("id") Object[] ids) {
        super.deleteBatchByIds(ids);
        return Result.success();
    }

}
