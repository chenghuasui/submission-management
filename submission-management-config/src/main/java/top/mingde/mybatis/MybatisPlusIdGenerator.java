package top.mingde.mybatis;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

public class MybatisPlusIdGenerator implements IdentifierGenerator {

    @Override
    public Long nextId(Object entity) {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId() + RandomUtil.randomLong(100000, 999999);
        return id;
    }

}
