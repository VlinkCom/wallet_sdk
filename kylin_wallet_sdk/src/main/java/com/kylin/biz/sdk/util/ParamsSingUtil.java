package com.kylin.biz.sdk.util;

import java.lang.reflect.Field;
import java.util.*;

public class ParamsSingUtil {

    //参数排序，返回格式key=val
    public static   String signToString(Object object){
        StringBuffer str=new StringBuffer();
        Map map = object instanceof Map ? ((Map) object) : null;
        List<String> na=new ArrayList<>();
        if(null!=map){
            Set set = map.keySet();
            na.addAll(set);
            Collections.sort(na);
            for(String key:na){
                if(null!=map.get(key)){
                    str.append(key+"="+map.get(key)+"&");
                }
            }
        }else {
            Class<?> aClass = object.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for(Field field:fields){
                na.add(field.getName());
            }
            Collections.sort(na);
            for(String name:na){
                try {
                    Field declaredField = aClass.getDeclaredField(name);
                    declaredField.setAccessible(true);
                    Object o = declaredField.get(object);
                    if(null!=o)
                        str.append(name+"="+o+"&");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (str.length() > 1) {
            str.delete(str.length()-1,str.length());
        }
        return str.toString();
    }
}
