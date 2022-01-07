package top.mingde.model.enums;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.mingde.model.vo.EnumsVO;

import java.util.List;

/**
 * 操作状态
* @ClassName: DelFlag
* @Description:
* @author chenjianfeng
 */
@ToString
public enum DelFlagEnum {
	
	NORMAL("0", "正常"),
	DELETE("1", "删除"),
	DISABLE("2", "禁用");
	
	@Getter
	@Setter
	private String value;
	@Getter
	@Setter
	private String desc;
	
	DelFlagEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public static String getDesc(String value) {
		DelFlagEnum[] delFlagEnums = values();
		for (DelFlagEnum delFlagEnum : delFlagEnums) {
			if (StrUtil.equals(delFlagEnum.getValue(), value)) {
				return delFlagEnum.getDesc();
			}
		}
		return null;
	}

	public static List<EnumsVO> getList(){
		List<EnumsVO> delFlagEnumList = Lists.newLinkedList();
		EnumsVO enumsVO = null;
		for (DelFlagEnum delFlagEnum: values()) {
			enumsVO = new EnumsVO();
			enumsVO.setValue(delFlagEnum.getValue());
			enumsVO.setDesc(delFlagEnum.getDesc());
			delFlagEnumList.add(enumsVO);
		}
		return delFlagEnumList;
	}
	
}
