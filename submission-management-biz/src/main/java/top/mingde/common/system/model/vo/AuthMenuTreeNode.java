package top.mingde.common.system.model.vo;

import cn.hutool.core.lang.tree.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sch
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthMenuTreeNode extends TreeNode<String> {
	/**
	 * icon
	 */
	private String icon;

	/**
	 * code
	 */
	private String code;

	/**
	 * path
	 */
	private String path;

	/**
	 * 组件
	 */
	private String component;

	/**
	 * 权限
	 */
	private String authority;

	/**
	 * 缓存
	 */
	private String keepAlive;

	/**
	 * type
	 */
	private String type;

	/**
	 * label
	 */
	private String label;

	/**
	 * level
	 */
	private Integer level;

	/**
	 * 是否显示
	 */
	private String visible;


	public AuthMenuTreeNode(String id, String parentId,
                            String name, Integer weight, String code,
                            String icon, String path, String component,
                            String authority, String keepAlive, String type,
                            Integer level, String visible) {
		super(id,parentId,name,weight);
		this.code = code;
		this.icon = icon;
		this.path = path;
		this.component = component;
		this.authority = authority;
		this.keepAlive = keepAlive;
		this.type = type;
		this.level = level;
		this.visible = visible;
	}
	
	
	
	
	
}
