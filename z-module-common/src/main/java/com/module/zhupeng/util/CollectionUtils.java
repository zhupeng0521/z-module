/**
 * FileName: Cle
 * Author:   DONGSK
 * Datetime: 2020/3/15 19:18
 * Description:
 * History:
 * 作者姓名 --修改时间 --版本号--描述
 */
package com.module.zhupeng.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Cle
 *
 * @author DONGSK
 * @date 2020/3/15
 * @since 1.0.0
 */
public class CollectionUtils {

    public static ArrayList<String> sortList(List<String> list) {
        /* 返回的list */
        ArrayList<String> retList = new ArrayList<String>();
        /* 当前list的元素个数 */
        int size = list.size();
        /* list的最大长度(while循环使用) */
        int listMaxSize = size;
        /* 当返回的list的 长度和参数list的长度不一致的时候，循环 */
        while (retList.size() < listMaxSize) {
            /* 长度最大的字符串的长度 */
            int maxLen = 0;
            /* 长度最大的index */
            int maxIndex = 0;
            /* 循环找出长度最大的字符串 */
            for (int i = 0; i < size; i++) {
                /* 当前字符串 */
                String str = list.get(i);
                /* 当前字符串的长度（null的时候是0） */
                int len = 0;
                if (str != null) {
                    len = str.length();
                }
                /* 如果当前字符串的长度比设定的maxLen大，则把当前字符串设定为最大*/
                if (len > maxLen) {
                    maxLen = len;
                    maxIndex = i;
                }
            }
            /* 结束内层循环，把最大的字符串追加到retList中 */
            retList.add(list.get(maxIndex));
            /* 把list中最大的那个元素remove掉 */
            list.remove(maxIndex);
            /* 元素-1 */
            size--;
        }
        return retList;
    }
}
