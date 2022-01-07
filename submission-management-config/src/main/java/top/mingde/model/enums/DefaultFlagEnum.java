package top.mingde.model.enums;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.mingde.model.vo.EnumsVO;

import java.util.List;

/**
 * 默认状态
 * @ClassName: DefaultFlagEnum
 * @Description:
 * @author chenjianfeng
 */
@ToString
public enum DefaultFlagEnum {

    IS("1","是"),
    NOT("0","否");

    @Getter
    @Setter
    private String value;

    @Getter
    @Setter
    private String desc;

    DefaultFlagEnum(String value, String desc ) {
        this.value = value;
        this.desc = desc;
    }

    public static String getDesc(String value) {
        DefaultFlagEnum[] defaultFlagEnums = values();
        for (DefaultFlagEnum defaultFlagEnum : defaultFlagEnums) {
            if (StrUtil.equals(defaultFlagEnum.getValue(), value)) {
                return defaultFlagEnum.getDesc();
            }
        }
        return null;
    }

    public static List<EnumsVO> getList(){
        List<EnumsVO> defultFlagEnumList = Lists.newLinkedList();
        EnumsVO enumsVO = null;
        for (DefaultFlagEnum defaultFlagEnum: values()) {
            enumsVO = new EnumsVO();
            enumsVO.setValue(defaultFlagEnum.getValue());
            enumsVO.setDesc(defaultFlagEnum.getDesc());
            defultFlagEnumList.add(enumsVO);
        }
        return defultFlagEnumList;
    }
}
