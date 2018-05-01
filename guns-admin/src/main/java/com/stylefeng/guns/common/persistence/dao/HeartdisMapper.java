package com.stylefeng.guns.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.common.persistence.model.Heartdis;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
public interface HeartdisMapper extends BaseMapper<Heartdis> {
    Long countByAge(@Param("start") Integer value1, @Param("end") Integer value2, @Param("ca") Integer mum);

    Long countByThalach(@Param("start") Integer value1, @Param("end") Integer value2, @Param("ca") Integer mum);

    Long countByChol(@Param("start") Integer value1, @Param("end") Integer value2, @Param("ca") Integer mum);

}
