/*
*   
*/
package top.mingde.common.app.service;


import top.mingde.common.app.entity.App;
import top.mingde.tool.core.mybatis.BaseService;


/**
 * <p>
 * 应用信息表 服务类
 * </p>
 *
 */
public interface IAppService extends BaseService<App> {

    /**
     * 获得应用名称重复数量
     * @param appName 应用名称
     * @param event 事件
     * @param id 主键
     * @return
    */
    boolean checkAppNameRepeat(String appName, String event, Long id);

    /**
     * 获得应用编码重复数量
     * @param appCode 应用编码
     * @param event 事件
     * @param id 主键
     * @return
    */
    boolean checkAppCodeRepeat(String appCode, String event, Long id);

    /**
     * 获得客户端ID重复数量
     * @param clientId 客户端ID
     * @param event 事件
     * @param id 主键
     * @return
    */
    boolean checkClientIdRepeat(String clientId, String event, Long id);

    /**
     * 创建应用
     * @param app
     */
    void createApp(App app);

}
