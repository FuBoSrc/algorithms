package com.suns.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author 见贤不思齐
 * @time 2018/11/8.
 * @desc
 */
public class SystemUtil {
    /**
     * 接收控制台输入的数据，并返回
     *
     * @param tip 输入的模块名称
     * @return 输入的数据
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入")
                .append(tip)
                .append(":");
        System.out.println(help);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNoneBlank(ipt)) {
                return ipt;
            }
        }
        throw new RuntimeException("请输入正确的" + tip + "!");
    }
}
