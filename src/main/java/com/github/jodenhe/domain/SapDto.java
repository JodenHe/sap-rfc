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

import java.util.ArrayList;
import java.util.List;

/**
 * SAP基础接口dto对象
 * @author xiaofeng.he
 */
public class SapDto {

    /** 接口名称 */
    private String funName;

    /** 普通入参 */
    private List<SapNormalParam> normalInputList = new ArrayList<>();

    /** 表入参 */
    private List<SapTableOrStructureParam> tableInputList = new ArrayList<>();

    /** 结构体入参 */
    private List<SapTableOrStructureParam> structureInputList = new ArrayList<>();

    /** 普通出参 */
    private List<String> normalOutputList = new ArrayList<>();

    /** 表出参 */
    private List<String> tableOutputList = new ArrayList<>();

    /** 结构体出参 */
    private List<String> structureOutputList = new ArrayList<>();

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public List<SapNormalParam> getNormalInputList() {
        return normalInputList;
    }

    public void setNormalInputList(List<SapNormalParam> normalInputList) {
        this.normalInputList = normalInputList;
    }

    public List<SapTableOrStructureParam> getTableInputList() {
        return tableInputList;
    }

    public void setTableInputList(List<SapTableOrStructureParam> tableInputList) {
        this.tableInputList = tableInputList;
    }

    public List<SapTableOrStructureParam> getStructureInputList() {
        return structureInputList;
    }

    public void setStructureInputList(List<SapTableOrStructureParam> structureInputList) {
        this.structureInputList = structureInputList;
    }

    public List<String> getNormalOutputList() {
        return normalOutputList;
    }

    public void setNormalOutputList(List<String> normalOutputList) {
        this.normalOutputList = normalOutputList;
    }

    public List<String> getTableOutputList() {
        return tableOutputList;
    }

    public void setTableOutputList(List<String> tableOutputList) {
        this.tableOutputList = tableOutputList;
    }

    public List<String> getStructureOutputList() {
        return structureOutputList;
    }

    public void setStructureOutputList(List<String> structureOutputList) {
        this.structureOutputList = structureOutputList;
    }

    @Override
    public String toString() {
        return "SapDto{" +
                "funName='" + funName + '\'' +
                ", normalInputList=" + normalInputList +
                ", tableInputList=" + tableInputList +
                ", structureInputList=" + structureInputList +
                ", normalOutputList=" + normalOutputList +
                ", tableOutputList=" + tableOutputList +
                ", structureOutputList=" + structureOutputList +
                '}';
    }
}
