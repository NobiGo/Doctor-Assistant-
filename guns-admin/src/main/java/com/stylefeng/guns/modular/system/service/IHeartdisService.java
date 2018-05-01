package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.Heartdis;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-19
 */
public interface IHeartdisService extends IService<Heartdis> {
    Long countBySex(Integer integer);

    Long countByStatus(Integer integer);

    Long countByCp(Integer integer);

    Long countByExang(Integer integer);

    Long countByAge(Integer integer, Integer integer2);

    Long countByThalach(Integer integer, Integer integer2);

    Long countByChol(Integer integer, Integer integer2);
}