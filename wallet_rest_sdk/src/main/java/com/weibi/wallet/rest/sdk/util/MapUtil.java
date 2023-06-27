package com.weibi.wallet.rest.sdk.util;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapUtil {
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                Object o = beanMap.get(key);
                if (Objects.isNull(o)) continue;
                map.put(key + "", o);
            }
        }
        return map;
    }
}
