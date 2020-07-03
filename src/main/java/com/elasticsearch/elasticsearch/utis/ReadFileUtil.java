package com.elasticsearch.elasticsearch.utis;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @ClassName: ReadFileUtil
 * @Description: 获取文件数据工具类
 * @Author: ouyang
 * @Date: 2019/10/10 19:56
 */
public class ReadFileUtil {

    /**
     * 读取 resources  文件夹下面json 文件内容
     *
     * @param path
     * @return
     */
    public static String readResourcesJson(String path) {
        String collect = null;
        try {
            ClassPathResource cpr = new ClassPathResource(path);
            InputStream inputStream = cpr.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            collect = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collect;
    }
}
