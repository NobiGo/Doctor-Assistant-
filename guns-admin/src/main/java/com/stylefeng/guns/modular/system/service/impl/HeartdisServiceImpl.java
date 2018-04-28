package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.persistence.dao.HeartdisMapper;
import com.stylefeng.guns.common.persistence.model.Heartdis;
import com.stylefeng.guns.modular.system.service.IHeartdisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-19
 */
@Service
public class HeartdisServiceImpl extends ServiceImpl<HeartdisMapper, Heartdis> implements IHeartdisService {


    @Resource
    HeartdisMapper heartdisMapper;

    @Override
    public Long countBySex(Integer integer) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("sex", integer);
        value.put("num", 1);
        List<Heartdis> listHeartdis = heartdisMapper.selectByMap(value);
        return new Long(listHeartdis.size());
    }

    @Override
    public Long countByStatus(Integer integer) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("num", integer);
        List<Heartdis> listHeartdis = heartdisMapper.selectByMap(value);
        return new Long(listHeartdis.size());
    }

    @Override
    public Long countByCp(Integer integer) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("cp", integer);
        value.put("num", 1);
        List<Heartdis> listHeartdis = heartdisMapper.selectByMap(value);
        return new Long(listHeartdis.size());
    }

    @Override
    public Long countByExang(Integer integer) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("exang", integer);
        value.put("num", 1);
        List<Heartdis> listHeartdis = heartdisMapper.selectByMap(value);
        return new Long(listHeartdis.size());
    }
}
