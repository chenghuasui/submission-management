package top.mingde.model.enums;

import lombok.Getter;
import lombok.Setter;

public enum MethodEventsEnum {

    ADD("add"),       //新增
    UPDATE("update"), //更新
    STATUS("status"); //状态

    @Getter
    @Setter
    private String value;

    MethodEventsEnum(String value) {
        this.value = value;
    }

}
