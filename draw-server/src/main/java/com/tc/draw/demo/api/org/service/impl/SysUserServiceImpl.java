package com.tc.draw.demo.api.org.service.impl;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.tc.draw.demo.api.org.mapper.*;
import com.tc.draw.demo.api.org.model.bo.user.SysUserImportBO;
import com.tc.draw.demo.api.org.model.domain.SysPost;
import com.tc.draw.demo.api.org.model.domain.SysUser;
import com.tc.draw.demo.api.org.model.domain.SysUserPost;
import com.tc.draw.demo.api.org.model.domain.SysUserRole;
import com.tc.draw.demo.api.org.model.dto.SysUserDTO;
import com.tc.draw.demo.api.org.model.dto.SysUserListDTO;
import com.tc.draw.demo.api.org.model.vo.SysRoleVO;
import com.tc.draw.demo.api.org.model.vo.SysUserListVO;
import com.tc.draw.demo.api.org.service.ISysUserService;
import com.tc.draw.demo.api.sys.service.ISysConfigService;
import com.tc.draw.demo.common.constant.DefaultRoleIdConstants;
import com.tc.draw.demo.common.constant.SysConfigKeyCOnstants;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.DateTimeUtils;
import com.tc.draw.demo.common.utils.SecurityUtils;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.exception.CustomException;
import com.tc.draw.demo.framework.security.SysUserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户 业务层处理
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    private static final Log LOG = LogFactory.getLog(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private ISysConfigService configService;

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<SysUserListVO> selectUserList(SysUserListDTO user) {
        List<SysUserListVO> list = userMapper.selectUserList(user);
        if (list == null) {
            return null;
        }
        list.parallelStream().forEach(u -> {
            List<String> names = postMapper.selectPosNametListByUserId(u.getId());
            if (names == null) {
                u.setDutyNames("未设置");
            } else {
                String name = names.stream().map(s -> {
                    return s;
                }).collect(Collectors.joining(",", "", ""));
                u.setDutyNames(name);
            }


            List<SysRoleVO> rolesList = roleMapper.selectRolePermissionByUserId(u.getId());
            if (rolesList == null) {
                u.setRoleNames("未设置");
            } else {
                String role = rolesList.stream().map(s -> {
                    return s.getRoleName();
                }).collect(Collectors.joining(",", "", ""));
                u.setRoleNames(role);
            }
        });
        return list;
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUserInfo selectUserByUserName(String userName) {
        SysUserInfo userInfo = userMapper.selectUserByUserName(userName);
//        String path = fileService.getFullPath(userInfo.getAvatar());
//        userInfo.setAvatar(path);
        return userInfo;
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    /**
     * 查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName) {
        List<SysRoleVO> list = roleMapper.selectRolesByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysRoleVO role : list) {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(String userName) {
        List<SysPost> list = postMapper.selectPostsByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysPost post : list) {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName) {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @return
     */
    @Override
    public String checkPhoneUnique(Long userId, String number) {
        userId = StringUtils.isNull(userId) ? -1L : userId;
        int count = userMapper.checkPhoneUnique(number, userId);
        if (count> 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }



    /**
     * 校验用户是否允许操作
     *
     */
    @Override
    public void checkUserAllowed(long userId) {
        if (StringUtils.isNotNull(userId) && SecurityUtils.isAdmin(userId)) {
            throw new CustomException("不允许操作超级管理员用户");
        }
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     * @throws PinyinException
     */
    @Override
    @Transactional
    public int insertUser(SysUserDTO user) {
        SysUser person = user.getUser();
        person.setPassword(SecurityUtils.encryptPassword(user.getUser().getPassword()));
        person.setCreateTime(System.currentTimeMillis());
        person.setCreatorId(SecurityUtils.getUserId());
        person.setCreateTime(System.currentTimeMillis());
        person.setOperatorId(person.getCreatorId());
        person.setUpdateTime(person.getCreateTime());
        person.setIsDelete(0);
        person.setLoginDate(0L);
        person.setLoginIp("");
        if (StringUtils.isEmpty(person.getUserType())) {
            person.setUserType("01");
        }
        try {
            person.setNamePinyin(PinyinHelper.getShortPinyin(user.getUser().getName()));
        } catch (PinyinException e) {
            person.setNamePinyin("");

        }
        person.setRemark("");
        person.setStatus("0");
        // 新增用户信息
        int rows = userMapper.insert(person);
        // 新增用户岗位关联
        insertUserPost(user.getPostIds(), person.getId());
        // 新增用户与角色管理
        insertUserRole(user.getRoleIds(), person.getId());
        return rows;
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(SysUserDTO user) {
        Long userId = user.getUser().getId();
        user.getUser().setUpdateTime(System.currentTimeMillis());
        user.getUser().setOperatorId(SecurityUtils.getUserId());
        user.getUser().setPassword(null);
        try {
            user.getUser().setNamePinyin(PinyinHelper.getShortPinyin(user.getUser().getName()));
        } catch (PinyinException e) {
            user.getUser().setNamePinyin("");

        }
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user.getRoleIds(), userId);
        // 删除用户与岗位关联
        userPostMapper.deleteUserPostByUserId(userId);
        // 新增用户与岗位管理
        insertUserPost(user.getPostIds(), userId);
        return userMapper.updateById(user.getUser());
    }

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(SysUser user) {
        return updateUser(user);
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(SysUser user) {
        return updateUser(user);
    }

    private int updateUser(SysUser user) {
        user.setUpdateTime(System.currentTimeMillis());
        user.setOperatorId(SecurityUtils.getUserId());
        return userMapper.updateById(user);
    }


    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetPwd(SysUser user) {
        return updateUser(user);
    }

    /**
     * 重置用户密码
     *
     * @param user 用户
     * @return 结果
     */
    @Override
    public int resetUserPwd(SysUser user) {
        return updateUser(user);
    }

    /**
     * 新增用户角色信息
     *
     * @param userId 用户对象
     */
    public void insertUserRole(List<Long> roles, Long userId) {
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<>();
            for (Long roleId : roles) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                userRoleMapper.insert(list);
            }
        }
    }

    /**
     * 新增用户岗位信息
     *
     */
    public void insertUserPost(List<Long> posts, long userId) {
        if (StringUtils.isNotNull(posts)) {
            // 新增用户与岗位管理
            List<SysUserPost> list = new ArrayList<>();
            for (Long postId : posts) {
                SysUserPost up = new SysUserPost();
                up.setUserId(userId);
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0) {
                userPostMapper.insert(list);
            }
        }
    }


    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(List<Long> userIds) {
        for (Long userId : userIds) {
            checkUserAllowed(userId);
        }
        return userMapper.batDelete(userIds, System.currentTimeMillis());
    }

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importUser(List<SysUserImportBO> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey(SysConfigKeyCOnstants.USER_INIT_PASSWORD);
        for (SysUserImportBO userBO : userList) {
            SysUser user = new SysUser();
            user.setPassword(password);
            user.setLoginName(userBO.getLoginName());
            user.setOrgEmail(userBO.getOrgEmail());
            user.setMobileNo(userBO.getMobileNo());
            user.setName(userBO.getName());
            user.setWorkNumber(userBO.getWorkNumber());
            user.setEntryTime(DateTimeUtils.getTimestampByString(userBO.getEntryTime(), DateTimeUtils.YYYY_MM_DD));
            try {
                SysUserDTO userDTO = new SysUserDTO();
                // 验证是否存在这个用户
                Long id = userMapper.countUserByUserName(user.getName());
                if (StringUtils.isNull(id)) {
                    userDTO.setUser(user);
                    insertUser(userDTO);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getName() + " 导入成功");
                } else if (isUpdateSupport) {
                    user.setId(id);
                    updateUser(userDTO);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                LOG.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public List<SysUserListVO> selectEngineerList(String userName, Long deptId) {
        //获取售后工程师
        return userMapper.selectEngineerListByKeyword(userName, deptId,  DefaultRoleIdConstants.ENGINEER);
    }


}
