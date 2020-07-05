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

import com.github.jodenhe.annotation.SapField;
import com.github.jodenhe.domain.*;
import com.sap.conn.jco.*;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SAP接口导入数据基础工具类
 * @author xiaofeng.he
 */
public class SapBaseInterfaceUtil {

    private static final Logger log = LoggerFactory.getLogger(SapBaseInterfaceUtil.class);

    /**
     * SAP RFC接口运行
     * @param sapRequestInfo 配置信息
     * @param sapDto sap基础对象
     * @return List<SapDto.SapParams>
     */
    public static SapResult invoke(SapRequestInfo sapRequestInfo, SapDto sapDto) {
        SapResult result = new SapResult();
        try {
            JCoDestination destination = SapConnectUtil.connect(sapRequestInfo);
            // 1 调用sapDto.getFunName()的RFC接口
            JCoFunction function = destination.getRepository().getFunction(sapDto.getFunName());

            // 2 输入参数
            // 2.1 普通入参类型
            JCoParameterList input = function.getImportParameterList();
            sapDto.getNormalInputList().forEach(item -> {
                input.setValue(item.getName(), item.getValue());
            });
            // 2.2 table类型入参
            sapDto.getTableInputList().forEach(item -> {
                JCoTable inTable = function.getTableParameterList().getTable(item.getName());
                inTable.appendRow();
                item.getValue().forEach((k, v) -> {
                    inTable.setValue(k, v);
                });
            });
            // 2.3 结构体入参
            sapDto.getStructureInputList().forEach(item -> {
                JCoStructure inStructure = function.getImportParameterList().getStructure(item.getName());
                item.getValue().forEach((k, v) -> {
                    inStructure.setValue(k, v);
                });
            });

            // 3 执行
            function.execute(destination);

            // 4 接口返回消息
            // 4.1 普通类型
            Map<String, Object> exportMap = new HashMap<>();
            sapDto.getNormalOutputList().forEach(item -> {
                String value = function.getExportParameterList().getString(item);
                exportMap.put(item, value);
            });
            result.setExport(exportMap);
            // 4.2 表类型
            Map<String, JCoTable> tableMap = new HashMap<>();
            sapDto.getTableOutputList().forEach(item -> {
                JCoTable value = function.getTableParameterList().getTable(item);
                tableMap.put(item, value);
            });
            result.setTable(tableMap);
            // 4.3 结构体类型
            Map<String, JCoStructure> structureMap = new HashMap<>();
            sapDto.getStructureOutputList().forEach(item -> {
                JCoStructure value = function.getExportParameterList().getStructure(item);
                structureMap.put(item, value);
            });
            result.setStructure(structureMap);

        } catch (Exception e) {
            log.error("[SAP Exception] e = {}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 转换成普通入参
     * @param object
     * @return
     * @throws IllegalAccessException
     */
    public static List<SapNormalParam> convert2NormalParam(Object object) throws IllegalAccessException {
        List<SapNormalParam> result = new ArrayList<>();
        if (object == null) {
            return result;
        }
        Class clazz = object.getClass();
        for (Field field: FieldUtils.getAllFieldsList(clazz)) {
            field.setAccessible(true);
            SapField sapField = field.getAnnotation(SapField.class);
            if (sapField != null) {
                Object v = fieldValue(field, sapField, object);
                if (!sapField.includeNull() && v == null) {
                    continue;
                }
                String name = CommonUtil.nvl(sapField.name(), field.getName());
                result.add(new SapNormalParam(name, v));
            }
        }

        return result;
    }

    /**
     * 转换成结构体或者表类型
     * @param name
     * @param object
     * @return
     * @throws IllegalAccessException
     */
    public static SapTableOrStructureParam convert2TableOrStructure(String name, Object object) throws IllegalAccessException {
        if (object == null) {
            return null;
        }
        Map<String, Object> value = new HashMap<>();
        Class clazz = object.getClass();
        for (Field field: FieldUtils.getAllFieldsList(clazz)) {
            field.setAccessible(true);
            SapField sapField = field.getAnnotation(SapField.class);
            if (sapField != null) {
                Object v = fieldValue(field, sapField, object);
                if (!sapField.includeNull() && v == null) {
                    continue;
                }
                String k = CommonUtil.nvl(sapField.name(), field.getName());
                value.put(k, v);
            }
        }

        return new SapTableOrStructureParam(name, value);
    }

    /**
     * 获取属性值
     * @param field
     * @param sapField
     * @param object
     * @return
     */
    private static Object fieldValue(Field field, SapField sapField, Object object) throws IllegalAccessException {
        Object v = field.get(object);
        if (v instanceof String && sapField.len() > 0) {
           return CommonUtil.format((String) v, sapField.prefix(), sapField.len());
        }
        return v;
    }

}
