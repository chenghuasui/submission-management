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
public enum DraftFlagEnum {

	NOT("0", "是"),
	IS("1", "否");

	@Getter
	@Setter
	private String value;
	@Getter
	@Setter
	private String desc;

	DraftFlagEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public static String getDesc(String value) {
		DraftFlagEnum[] draftFlagEnums = values();
		for (DraftFlagEnum draftEnum : draftFlagEnums) {
			if (StrUtil.equals(draftEnum.getValue(), value)) {
				return draftEnum.getDesc();
			}
		}
		return null;
	}

	public static List<EnumsVO> getList(){
		List<EnumsVO> draftFlagEnumList = Lists.newLinkedList();
		EnumsVO enumsVO = null;
		for (DraftFlagEnum draftFlagEnum: values()) {
			enumsVO = new EnumsVO();
			enumsVO.setValue(draftFlagEnum.getValue());
			enumsVO.setDesc(draftFlagEnum.getDesc());
			draftFlagEnumList.add(enumsVO);
		}
		return draftFlagEnumList;
	}
	
}
