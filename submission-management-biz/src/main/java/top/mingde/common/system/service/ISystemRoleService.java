package top.mingde.common.system.service;

import top.mingde.common.system.entity.SystemRole;
import top.mingde.common.system.model.vo.SystemRoleWithPermis;
import top.mingde.tool.core.mybatis.BaseService;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * 
 * @since 2019-07-30
 */
public interface ISystemRoleService extends BaseService<SystemRole> {

    /**
     * 根据当前用户获得授权的角色ID
     * @param userId
     * @param userType
     * @param tenantId
     * @return
     */
    List<String> getAuthRoles(String userId, String userType, String tenantId);

    /**
     * 保存角色和权限关系
     *
     * @param systemRoleWithPermis
     * @return
     */
    boolean saveRolePermis(SystemRoleWithPermis systemRoleWithPermis);

    /**
     * 禁用/启用角色
     *
     * @param id
     * @param delFlag
     * @return
     */
    boolean remove(Integer id, String delFlag);

}
