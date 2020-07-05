/**
 *     Copyright [2020] [https://github.com/JodenHe]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.github.jodenhe.util;

/**
 * base common util
 * @author xiaofeng.he
 */
public class CommonUtil {

    /**
     * if t is empty then return {@code result}, else return itself.
     * @param t
     * @param result
     * @param <T>
     * @return
     */
    public static<T> T nvl(T t, T result) {
        return isEmpty(t)? result: t;
    }

    /**
     * if t is empty then return {@code nullResult}, else return {@code result}.
     * @param t
     * @param result
     * @param <T>
     * @return
     */
    public static<T> T nvl(T t, T nullResult, T result) {
        return isEmpty(t)? nullResult: result;
    }

    /**
     * Check whether the given {@code String} is empty.
     * <p>
     *    copy by spring framework util
     * </p>
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }


    /**
     * 格式化字符串
     * @param str 源字符串
     * @param prefix 前缀
     * @param len 整个字符串格式化后长度
     * @return
     */
    public static String format(String str, String prefix, int len) {
        if (str == null) {
            return null;
        }
        int strLen = str.length();
        if (strLen <= len) {
            while (strLen < len) {
                StringBuffer sb = new StringBuffer();
                // 前补充
                sb.append(prefix).append(str);
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

}
