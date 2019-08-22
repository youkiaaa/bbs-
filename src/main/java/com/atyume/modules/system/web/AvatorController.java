package com.atyume.modules.system.web;

import com.atyume.core.annotation.SystemLog;
import com.atyume.core.utils.Constants;
import com.atyume.core.utils.FileUtil;
import com.atyume.core.utils.Result;
import com.atyume.core.utils.ResultCodeEnum;
import com.atyume.modules.base.web.BaseCrudController;
import com.atyume.modules.system.po.*;
import com.atyume.modules.system.service.*;
import com.atyume.modules.system.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/person")
public class AvatorController extends BaseCrudController<User> {
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

    @Autowired
    private MoneyLogService moneyLogService;

    @GetMapping
    public String SeeSelf(Model model){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        System.out.println("******************************************************************");
        System.out.println("selfname:"+username);
        Long userid=userService.queryId(username);
        System.out.println("selfid:"+userid);
        User userSelf=userService.queryById(userid);
        UserVO userVO=new UserVO(userSelf);
        System.out.println("selfVOid:"+userVO.getId());
        userVO.setOrganizationName(getOrganizationNames(Long.valueOf(userVO.getOrganizationId())));
        userVO.setRoleNames(getRoleNamess(userVO.getRoleIdList()));
        userVO.setGroupNames(getGroupNamess(userVO.getGroupIdList()));
        String picPath=avatorService.selectPathByActive(userid);
        userVO.setPicPath(picPath);

        model.addAttribute("sourcePath",picPath);

        System.out.println("selfVOPath:"+userVO.getPicPath());
        model.addAttribute("userSelf",userVO);
        System.out.println("once-once-once-once-once-once-once-once");
        List<String> picList= avatorService.selectPath(userid);
        System.out.println("picList:"+picList.size());
        /*for(String picpath:picList){
            int i=0;
            picpath=picList.get(i);
            if(picPath!=null){
                System.out.println(picpath);
                i++;
            }
        }*/
        model.addAttribute("picList",picList);
        return "system/person";
    }

    @ResponseBody
    @RequestMapping(value = "/onceImg",method=RequestMethod.POST)
    public Result onceImg(@RequestParam(value = "picPath") String picpath){
        System.out.println(picpath);
        System.out.println("choose avatar for history");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Long userid=userService.queryId(username);
        System.out.println("nininininininini++++++++++++++++++++++++++++++++++++++"+userid);
        boolean flag = avatorService.updateImg(userid,picpath);
        if (!flag) {
            System.out.println("修改图片失败");
        }
        return Result.success();
    }

    @ResponseBody
    @RequestMapping("/queryImg")
    public String queryImg(@RequestParam(value = "file") MultipartFile file){
        System.out.println("heloooooooooooooooooooooooooooooooooooooooooo");
        String picPath=FileUtil.saveImg(file);
        // 将文件上传的路径给user对象
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Long userid=userService.queryId(username);
        boolean flag = avatorService.queryImg(userid, picPath);
        if (!flag) {
            System.out.println("修改图片失败");
        }
        System.out.println("flag:"+flag);
        return "true";
    }

    private String getGroupNamess(Collection<Long> groupIds) {
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

    private String getRoleNamess(Collection<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for (Long roleId : roleIds) {
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

    private String getOrganizationNames(Long organizationId) {
        Organization organization = organizationService.queryOne(new Organization().setId(organizationId));
        if (organization == null) {
            return "";
        }
        return organization.getName();
    }

    /*余额*/
    @ResponseBody
    @SystemLog("用户充值余额")
    @RequestMapping("/posMoney")
    public Result update(@RequestParam("money") String posmoney) {
        Double money= Double.valueOf(posmoney);
        System.out.println("read money:"+money);
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Long userid=userService.queryId(username);
//        金额充值
        userService.updateMoneyById(money,userid);
//        插入明细表
        MoneyLog moneyLog=new MoneyLog();
        moneyLog.setMoney(money);
        moneyLog.setPosuser(userid);
        Long qua= Long.valueOf(0);
        moneyLog.setQuauser(qua);
        moneyLog.setType("充值");
        moneyLogService.create(moneyLog);

        return Result.success();
    }

    /*打赏*/
    @ResponseBody
    @SystemLog("用户打赏金额")
    @RequestMapping("/rewMoney")
    @Transactional
    public Result award(@RequestParam("money") Double money,@RequestParam("foruser") Long touserid) {
        System.out.println("read money:" + money);
        System.out.println("read user:" + touserid);
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Long userid = userService.queryId(username);

/*//        转账人扣钱
        userService.modifyMoneyById(money,userid);
//        被打赏人收钱
        userService.updateMoneyById(money,touserid);*/
        int number = (int) (Math.random() * 100) + 1;
        System.out.println("random随机数:" + number);
        try {
            if (number % 2 != 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.failure("999", "事务回滚");
            }
        } catch (UnexpectedRollbackException e){

        }
            moneyLogService.transfer(userid, touserid, money);
//        记录交易
            MoneyLog pos = new MoneyLog();
            pos.setMoney(money);
            pos.setPosuser(userid);
            pos.setQuauser(touserid);
            pos.setType("支出");
            MoneyLog qua = new MoneyLog();
            qua.setMoney(money);
            qua.setPosuser(touserid);
            qua.setQuauser(userid);
            qua.setType("收入");
            moneyLogService.create(pos);
            moneyLogService.create(qua);
            return Result.success();
    }
}
