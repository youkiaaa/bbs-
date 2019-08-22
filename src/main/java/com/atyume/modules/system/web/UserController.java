package com.atyume.modules.system.web;

import com.atyume.core.annotation.SystemLog;
import com.atyume.core.utils.Result;
import com.atyume.core.utils.ResultCodeEnum;
import com.atyume.modules.base.query.PageQuery;
import com.atyume.modules.base.web.BaseCrudController;
import com.atyume.modules.system.po.*;
import com.atyume.modules.system.service.*;
import com.atyume.modules.system.vo.UserVO;
import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseCrudController<User> {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private AvatorService avatorService;

    @GetMapping
    @RequiresPermissions("user:view")
    public String userPage(Model model) {
        model.addAttribute("organizationList", organizationService.queryAll());
        model.addAttribute("roleList", roleService.queryAll());
        model.addAttribute("groupList", groupService.queryAll());
        return "system/user";
    }

    @ResponseBody
    @GetMapping("/list")
    @SystemLog("用户管理查看用户")
    @RequiresPermissions("user:view")
    @Override
    public Result<List<UserVO>> queryList(User user, PageQuery pageQuery) {
        System.out.println("list show");
        Page<User> page = (Page<User>) userService.queryList(user, pageQuery);
        if(page!=null){
            System.out.println("have page user");
        }
        List<UserVO> userVOS = new ArrayList<>();
        page.forEach(u -> {
            UserVO userVO = new UserVO(u);
            System.out.println(userVO.getId());
            userVO.setOrganizationName(getOrganizationName(Long.valueOf(userVO.getOrganizationId())));
            userVO.setRoleNames(getRoleNames(userVO.getRoleIdList()));
            userVO.setGroupNames(getGroupNames(userVO.getGroupIdList()));
            userVOS.add(userVO);
        });
        return Result.success(userVOS).addExtra("total", page.getTotal());
    }

    private String getGroupNames(Collection<Long> groupIds) {
        if (CollectionUtils.isEmpty(groupIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (Long groupId : groupIds) {
            Group role = groupService.queryById(groupId);
            if (role != null) {
                s.append(role.getName());
                s.append(",");
            }
        }

        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }


    private String getRoleNames(Collection<Long> groupIds) {
        if (CollectionUtils.isEmpty(groupIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (Long roleId : groupIds) {
            Role role = roleService.queryById(roleId);
            if (role != null) {
                s.append(role.getDescription());
                s.append(",");
            }
        }

        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }

    private String getOrganizationName(Long organizationId) {
        Organization organization = organizationService.queryOne(new Organization().setId(organizationId));
        if (organization == null) {
            return "";
        }
        return organization.getName();
    }

    @ResponseBody
    @PostMapping("/create")
    @RequiresPermissions("user:create")
    @SystemLog("用户管理创建用户")
    @Override
    public Result create(@Validated(User.UserCreateChecks.class) User user) {
        userService.createUser(user);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("user:update")
    @SystemLog("用户管理更新用户")
    @Override
    public Result update(@Validated(User.UserUpdateChecks.class) User user) {
        userService.updateNotNull(user);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete-batch")
    @RequiresPermissions("user:delete")
    @SystemLog("用户管理删除用户")
    @Override
    public Result deleteBatchByIds(@NotNull @RequestParam("id") Object[] ids) {
        // 当前用户
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.queryOne(new User().setUsername(username));
        boolean isSelf = Arrays.stream(ids).anyMatch(id -> id.equals(user.getId()));
        if (isSelf) {
            return Result.failure(ResultCodeEnum.FAILED_DEL_OWN);
        }
        super.deleteBatchByIds(ids);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("user:update")
    @PostMapping("/{id}/change/password")
    @SystemLog("用户管理更改用户密码")
    public Result changePassword(@PathVariable("id") Long id, String newPassword) {
        userService.changePassword(id, newPassword);
        return Result.success();
    }

}
