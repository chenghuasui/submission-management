package top.mingde.common.system.service;


import top.mingde.common.system.entity.SystemPermi;
import top.mingde.tool.core.mybatis.BaseService;

import java.util.List;

/**
 * <p>
 * 系统权限表 服务类
 * </p>
 *
 * 
 * @since 2019-04-04
 */
public interface ISystemPermiService extends BaseService<SystemPermi> {
	
	/**
	 * 获取权限集合
	 * @param userId
	 *
	 * @return
	 */
	List<SystemPermi> getPermissions(Long userId, boolean superFlag);

	/**
	 * 是否存在重复数据（标识）
	 * @param permiList
	 * @return
	 */
	boolean hashRepeatPermisData(List<SystemPermi> permiList);


}
