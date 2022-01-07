package top.mingde.common.system.model.enums;

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
public enum InitFlagEnum {

    IS("1","是"),
    NOT("0","否");

    @Getter
    @Setter
    private String value;

    @Getter
    @Setter
    private String desc;

    InitFlagEnum(String value, String desc ) {
        this.value = value;
        this.desc = desc;
    }

    public static String getDesc(String value) {
        InitFlagEnum[] enums = values();
        for (InitFlagEnum enumOf : enums) {
            if (StrUtil.equals(enumOf.getValue(), value)) {
                return enumOf.getDesc();
            }
        }
        return null;
    }

    public static List<EnumsVO> getList(){
        List<EnumsVO> enumsVOList = Lists.newLinkedList();
        EnumsVO enumsVO = null;
        for (InitFlagEnum enumOf: values()) {
            enumsVO = new EnumsVO();
            enumsVO.setValue(enumOf.getValue());
            enumsVO.setDesc(enumOf.getDesc());
            enumsVOList.add(enumsVO);
        }
        return enumsVOList;
    }
}
