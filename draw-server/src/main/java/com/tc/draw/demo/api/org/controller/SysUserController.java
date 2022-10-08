package com.tc.draw.demo.api.org.controller;

import java.util.List;

import com.tc.draw.demo.api.org.model.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tc.draw.demo.api.org.model.bo.user.SysUserImportBO;
import com.tc.draw.demo.api.org.model.dto.SysUserDTO;
import com.tc.draw.demo.api.org.model.dto.SysUserListDTO;
import com.tc.draw.demo.api.org.model.vo.SysUserInfoVO;
import com.tc.draw.demo.api.org.model.vo.SysUserListVO;
import com.tc.draw.demo.api.org.service.ISysPostService;
import com.tc.draw.demo.api.org.service.ISysRoleService;
import com.tc.draw.demo.api.org.service.ISysUserService;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.SecurityUtils;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.common.utils.poi.ExcelUtil;

import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.UserErrorStatus;
import com.tc.draw.demo.framework.web.model.page.PageVO;

import io.swagger.annotations.ApiOperation;

/**
 * 用户信息
 * 
 *
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public Res<PageVO<SysUserListVO>, Void> list(SysUserListDTO user)
    {
        startPage();
        List<SysUserListVO> list = userService.selectUserList(user);
        return Res.ok(getDataTable(list));
    }

    // @OperateAction(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @GetMapping("/export")
    public Res<String, Void> export(SysUserListDTO user)
    {
        List<SysUserListVO> list = userService.selectUserList(user);
        ExcelUtil<SysUserListVO> util = new ExcelUtil<>(SysUserListVO.class);
        return util.exportExcel(list, "用户数据");
    }

    // @OperateAction(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public Res<String, Void> importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUserImportBO> util = new ExcelUtil<>(SysUserImportBO.class);
        List<SysUserImportBO> userList = util.importExcel(file.getInputStream());
        String message = userService.importUser(userList, updateSupport);
        return Res.ok(message);
    }

    @GetMapping("/importTemplate")
    public Res<String, Void> importTemplate()
    {
        ExcelUtil<SysUserImportBO> util = new ExcelUtil<>(SysUserImportBO.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping(value = "/info")
    public Res<SysUserInfoVO, Void> getInfo( Long userId)
    {
    	SysUserInfoVO vo = new SysUserInfoVO();
    	vo.setRoles(roleService.selectRoleAll());
    	vo.setPosts(postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
        	vo.setUser(userService.selectUserById(userId));
        	vo.setPostIds(postService.selectPostListByUserId(userId));
        	vo.setRoleIds(roleService.selectRoleListByUserId(userId));
        }
        return Res.ok(vo);
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:save')")
    // @OperateAction(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public Res<Integer, Void> add(@Validated @RequestBody SysUserDTO userDTO)
    {
    	SysUser user = userDTO.getUser();
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getName())))
        {
        	return new Res<>(UserErrorStatus.ACCOUNT_EXIST.getCode(),"新增用户'" + user.getName() + "'失败，登录账号已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user.getId(),user.getMobileNo())))
        {
        	return new Res<>(UserErrorStatus.MOBILE_NOT_SAME.getCode(),"新增用户'" + user.getName() + "'失败，手机号码已存在");
        }
//        else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user.getId(),user.getOrgEmail())))
//        {
//        	return new Res<>(UserErrorStatus.EMAIL_ERROR.getCode(),"新增用户'" + user.getName() + "'失败，邮箱账号已存在");
//        }
        return Res.ok(userService.insertUser(userDTO));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:update')")
    // @OperateAction(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public Res<Integer, Void> edit(@Validated @RequestBody SysUserDTO userDTO)
    {
    	SysUser user = userDTO.getUser();
        userService.checkUserAllowed(user.getId());
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user.getId(),user.getMobileNo())))
        {
            return new Res<>(UserErrorStatus.MOBILE_NOT_SAME.getCode(),"修改用户'" + user.getName() + "'失败，手机号码已存在");
        }
//        else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user.getId(),user.getOrgEmail())))
//        {
//            return new Res<>(UserErrorStatus.EMAIL_ERROR.getCode(),"修改用户'" + user.getName() + "'失败，邮箱账号已存在");
//        }
        return Res.ok(userService.updateUser(userDTO));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:delete')")
    // @OperateAction(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public Res<Integer, Void> remove(@RequestBody List<Long> userIds)
    {
        return Res.ok(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('system:user:update')")
    // @OperateAction(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    public Res<Integer, Void> resetPwd(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user.getId());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return Res.ok(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:user:update')")
    // @OperateAction(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    public Res<Integer, Void> changeStatus(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user.getId());
        return Res.ok(userService.updateUserStatus(user));
    }
    
    
    /**
     * 列表
     */

    @ApiOperation(value = "组件列表")
    @GetMapping("/selectList")
    public Res<List<SysUserListVO>, Void> selectList(SysUserListDTO params) throws Exception {
        return Res.ok(userService.selectUserList(params));
    }

    @ApiOperation(value = "工程师列表")
    @GetMapping("/engineerList")
    public Res<List<SysUserListVO>, Void> selectEngineerList(String userName, Long deptId) {
        return Res.ok(userService.selectEngineerList(userName, deptId));
    }
    
}