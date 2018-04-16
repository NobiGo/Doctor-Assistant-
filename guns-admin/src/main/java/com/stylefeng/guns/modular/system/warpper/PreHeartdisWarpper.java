package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

public class PreHeartdisWarpper extends BaseControllerWarpper {

    public PreHeartdisWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("cp", ConstantFactory.me().getCpValue((Integer) map.get("cp")));
        map.put("slope", ConstantFactory.me().getSlopeValue((Integer) map.get("slope")));
        map.put("ca", ConstantFactory.me().getStatusValue((Integer) map.get("ca")));
        map.put("thal", ConstantFactory.me().getThalValue(new Double(map.get("thal").toString()).intValue()));
        map.put("num", ConstantFactory.me().getNumValue(new Double(map.get("num").toString()).intValue()));
        map.put("exang", ConstantFactory.me().getExangValue(new Double(map.get("exang").toString()).intValue()));
        map.put("sex", ConstantFactory.me().getSexValue(new Double(map.get("sex").toString()).intValue()));
        map.put("fbs", ConstantFactory.me().getFbsValue(new Double(map.get("fbs").toString()).intValue()));
        map.put("restecg", ConstantFactory.me().getRestecgValue(new Double(map.get("restecg").toString()).intValue()));
    }
}
