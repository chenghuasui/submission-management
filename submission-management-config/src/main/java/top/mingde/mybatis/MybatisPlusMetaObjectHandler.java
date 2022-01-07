package top.mingde.mybatis;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import top.mingde.security.utils.AuthUtil;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

@Slf4j
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        String createBy = StrUtil.toCamelCase(TableField.CREATE_BY.toString());
        boolean createByOf = metaObject.hasSetter(createBy);
        if (createByOf) {
            this.strictInsertFill(metaObject, createBy, String.class, getCurrentUser());
        }
        String createDate = StrUtil.toCamelCase(TableField.CREATE_DATE.toString());
        boolean createDateOf = metaObject.hasSetter(createDate);
        if (createDateOf) {
            this.strictInsertFill(metaObject, createDate, LocalDateTime.class, getNow());
        }
        String version = StrUtil.toCamelCase(TableField.VERSION.toString());
        boolean versionOf = metaObject.hasSetter(version);
        if (versionOf) {
            this.strictInsertFill(metaObject, version, Integer.class, 0);
        }
        String delFlag = StrUtil.toCamelCase(TableField.DEL_FLAG.toString());
        boolean delFlagOf = metaObject.hasSetter(delFlag);
        if (delFlagOf) {
            this.strictInsertFill(metaObject, delFlag, String.class, StringPool.ZERO);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String updateBy = StrUtil.toCamelCase(TableField.UPDATE_BY.toString());
        boolean updateByOf = metaObject.hasSetter(updateBy);
        if (updateByOf) {
            this.strictUpdateFill(metaObject, updateBy, String.class, getCurrentUser());
        }
        String updateDate = StrUtil.toCamelCase(TableField.UPDATE_DATE.toString());
        boolean updateDateOf = metaObject.hasSetter(updateDate);
        if (updateDateOf) {
            this.strictUpdateFill(metaObject, updateDate, LocalDateTime.class, getNow());
        }
    }

    @Override
    public MetaObjectHandler fillStrategy(MetaObject metaObject, String fieldName, Object fieldVal) {
        String updateBy = StrUtil.toCamelCase(TableField.UPDATE_BY.toString());
        String updateDate = StrUtil.toCamelCase(TableField.UPDATE_DATE.toString());
        if (getFieldValByName(fieldName, metaObject) == null
                || StrUtil.equals(fieldName, updateBy)
                || StrUtil.equals(fieldName, updateDate)) {
            setFieldValByName(fieldName, fieldVal, metaObject);
        }
        return this;
    }

    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        String updateBy = StrUtil.toCamelCase(TableField.UPDATE_BY.toString());
        String updateDate = StrUtil.toCamelCase(TableField.UPDATE_DATE.toString());
        if (metaObject.getValue(fieldName) == null
                || StrUtil.equals(fieldName, updateBy)
                || StrUtil.equals(fieldName, updateDate)) {
            Object obj = fieldVal.get();
            if (Objects.nonNull(obj)) {
                metaObject.setValue(fieldName, obj);
            }
        }
        return this;
    }

    private String getCurrentUser() {
        String userName = AuthUtil.getUsername();
        //String userName = "";
        return StrUtil.isNotBlank(userName) ? userName : StrUtil.EMPTY;
    }

    private LocalDateTime getNow() {
        return LocalDateTimeUtil.now();
    }

    enum TableField {
        VERSION,
        CREATE_BY,
        CREATE_DATE,
        UPDATE_BY,
        UPDATE_DATE,
        DEL_FLAG
    }
}
