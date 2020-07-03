package com.elasticsearch.elasticsearch.utis;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Author: zhangtiancheng
 * @Date: 2020/2/27
 */
public class CommonUtil {

    /**
     * uuid
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 随机数
     * @return
     */
    public static long randomNumber() {
        return System.currentTimeMillis() + new Random().nextInt(1000000);
    }

    /**
     * yyyyMMddhhmmssSSS
     * @return
     */
    public static String timeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return sdf.format(new Date());
    }

    public static List<Map.Entry<String,Object>> sortHashMapByKey(HashMap<String, Object> map) {
        Set<Map.Entry<String,Object>> entey = map.entrySet();
        List<Map.Entry<String,Object>> list = new ArrayList<>(entey);
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return list;
    }

    /**
     * 毫秒数，13位
     * @param time
     * @param timeout
     * @return
     */
    public static boolean ifTimeout(long time, long timeout) {
        long diff = System.currentTimeMillis() - time;
        if (diff > timeout * 1000) {
            return true;
        }
        return false;
    }

}
