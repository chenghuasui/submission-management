/*
*   
*/
package top.mingde.common.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mingde.common.system.entity.SystemUser;
import top.mingde.common.system.entity.SystemUserRole;
import top.mingde.common.system.mapper.ISystemUserMapper;
import top.mingde.common.system.model.dto.SystemUserSaveDTO;
import top.mingde.common.system.model.dto.SystemUserUpdateDTO;
import top.mingde.common.system.model.enums.AdminStatusEnum;
import top.mingde.common.system.model.enums.InitFlagEnum;
import top.mingde.common.system.service.ISystemUserRoleService;
import top.mingde.common.system.service.ISystemUserService;
import top.mingde.security.utils.PasswordUtil;
import top.mingde.tool.core.mybatis.BaseServiceImpl;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * 
 * @since 2021-07-04 22:50:48
 */
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SystemUserServiceImpl
    extends BaseServiceImpl<ISystemUserMapper, SystemUser> implements ISystemUserService {


    private final ISystemUserRoleService systemUserRoleService;

    /**
     * 创建用户和用户与角色关系
     *
     * @param systemUserSaveDTO
     */
    @Override
    public void addUser(SystemUserSaveDTO systemUserSaveDTO) {
        SystemUser systemUser = BeanUtil.copyProperties(systemUserSaveDTO,SystemUser.class);
        systemUser.setLoginPwd(PasswordUtil.entrypt(systemUserSaveDTO.getUserPwd()));
        systemUser.setInitFlag(InitFlagEnum.IS.getValue());
        systemUser.setAdminStatus(AdminStatusEnum.NOT.getValue());
        super.save(systemUser);
        List<SystemUserRole> systemUserRoleList = Lists.newArrayList();
        List<Integer> roleIdList = systemUserSaveDTO.getRoleId();
        for (Integer roleId : roleIdList) {
            SystemUserRole userRole = new SystemUserRole();
            userRole.setUserId(systemUser.getId());
            userRole.setRoleId(roleId);
            systemUserRoleList.add(userRole);
        }
        QueryWrapper<SystemUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SystemUserRole::getUserId, systemUser.getId());
        systemUserRoleService.remove(wrapper);
        systemUserRoleService.saveBatch(systemUserRoleList);
    }

    /**
     * 更新用户和用户与角色关系
     *
     * @param systemUserUpdateDTO
     */
    @Override
    public void updateUser(SystemUserUpdateDTO systemUserUpdateDTO) {
        SystemUser systemUser = super.getById(systemUserUpdateDTO.getId());
        BeanUtil.copyProperties(systemUserUpdateDTO, systemUser,
                CopyOptions.create().ignoreNullValue());
        systemUser.setLoginPwd(PasswordUtil.entrypt(systemUserUpdateDTO.getUserPwd()));
        super.updateById(systemUser);
        List<SystemUserRole> systemUserRoleList = Lists.newArrayList();
        List<Integer> roleIdList = systemUserUpdateDTO.getRoleId();
        for (Integer roleId : roleIdList) {
            SystemUserRole userRole = new SystemUserRole();
            userRole.setUserId(systemUser.getId());
            userRole.setRoleId(roleId);
            systemUserRoleList.add(userRole);
        }
        QueryWrapper<SystemUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SystemUserRole::getUserId, systemUser.getId());
        systemUserRoleService.remove(wrapper);
        systemUserRoleService.saveBatch(systemUserRoleList);
    }
}
