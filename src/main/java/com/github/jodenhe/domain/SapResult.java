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
package com.github.jodenhe.domain;

import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

import java.util.Map;

/**
 * SAP返回结果
 * @author xiaofeng.he
 */
public class SapResult {

    /** 普通参数返回 */
    private Map<String,Object> export;

    /** 表参数返回 */
    private Map<String, JCoTable> table;

    /** 结构体参数返回 */
    private Map<String, JCoStructure> structure;

    public Map<String, Object> getExport() {
        return export;
    }

    public void setExport(Map<String, Object> export) {
        this.export = export;
    }

    public Map<String, JCoTable> getTable() {
        return table;
    }

    public void setTable(Map<String, JCoTable> table) {
        this.table = table;
    }

    public Map<String, JCoStructure> getStructure() {
        return structure;
    }

    public void setStructure(Map<String, JCoStructure> structure) {
        this.structure = structure;
    }

    @Override
    public String toString() {
        return "SapResult{" +
                "export=" + export +
                ", table=" + table +
                ", structure=" + structure +
                '}';
    }
}
