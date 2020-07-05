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

/**
 * 请求信息.
 * @author xiaofeng.he
 */
public class SapRequestInfo {

    /** 服务器地址. */
    private String serverName;

    /** 系统编码 */
    private String systemNumber;

    /** SAP集团 */
    private String client;

    /** SAP用户名 */
    private String userName;

    /** SAP密码 */
    private String password;

    /** 登录语言 */
    private String lang;

    /** 最大连接数 */
    private String connectCount;

    /** 最大连接线程数 */
    private String connectThread;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getConnectCount() {
        return connectCount;
    }

    public void setConnectCount(String connectCount) {
        this.connectCount = connectCount;
    }

    public String getConnectThread() {
        return connectThread;
    }

    public void setConnectThread(String connectThread) {
        this.connectThread = connectThread;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "serverName='" + serverName + '\'' +
                ", systemNumber='" + systemNumber + '\'' +
                ", client='" + client + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", lang='" + lang + '\'' +
                ", connectCount='" + connectCount + '\'' +
                ", connectThread='" + connectThread + '\'' +
                '}';
    }
}
