/*
*   
*/
package top.mingde.common.system.service;

import top.mingde.common.system.entity.SystemUser;
import top.mingde.common.system.model.dto.SystemUserSaveDTO;
import top.mingde.common.system.model.dto.SystemUserUpdateDTO;
import top.mingde.tool.core.mybatis.BaseService;


/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * 
 * @since 2021-07-04 22:50:48
 */
public interface ISystemUserService extends BaseService<SystemUser> {


    /**
     * 创建用户和用户与角色关系
     *
     * @param systemUserSaveDTO
     */
    void addUser(SystemUserSaveDTO systemUserSaveDTO);

    /**
     * 更新用户和用户与角色关系
     *
     * @param systemUserWithRole
     */
    void updateUser(SystemUserUpdateDTO systemUserWithRole);

}
