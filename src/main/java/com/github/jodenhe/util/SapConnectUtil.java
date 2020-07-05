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

import com.github.jodenhe.domain.SapRequestInfo;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * SAP 连接工具类
 * @author xiaofeng.he
 */
public class SapConnectUtil {

    private static final String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";

    private static Logger log = LoggerFactory.getLogger(SapConnectUtil.class);

    /**
     * 创建 SAP 接口属性文件
     * @param name ABAP 管道名称
     * @param suffix 属性文件后缀
     * @param properties 属性文件内容
     */
    private static void createDataFile(String name, String suffix, Properties properties){
        File cfg = new File(name + "." + suffix);
        if (cfg.exists()) {
            cfg.deleteOnExit();
        }
        try {
            FileOutputStream fos = new FileOutputStream(cfg, false);
            properties.store(fos, "for tests only!");
            fos.close();
        } catch (Exception e){
            log.error("Create Data file fault, error msg: " + e.toString());
            throw new RuntimeException("Unable to create the destination file " + cfg.getName(), e);
        }
    }

    /**
     * 设置连接属性
     * @param sapRequestInfo
     */
    private static void setConnectData(SapRequestInfo sapRequestInfo){
        Properties connectProperties = new Properties();
        // 服务器
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, sapRequestInfo.getServerName());
        // 系统编号
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,  sapRequestInfo.getSystemNumber());
        // SAP集团
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, sapRequestInfo.getClient());
        // SAP用户名
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, sapRequestInfo.getUserName());
        // 密码
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, sapRequestInfo.getPassword());
        // 登录语言
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, sapRequestInfo.getLang());
        // 最大连接数
        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, sapRequestInfo.getConnectCount());
        // 最大连接线程
        connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, sapRequestInfo.getConnectThread());

        createDataFile(ABAP_AS_POOLED, "jcoDestination", connectProperties);
    }

    /**
     * 获取SAP连接
     * @return  SAP连接对象
     */
    public static JCoDestination connect(SapRequestInfo sapRequestInfo) throws JCoException {
        setConnectData(sapRequestInfo);
        JCoDestination destination =null;
        try {
            destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
            String repositoryName = destination.getRepository().getName();
            log.debug("repository name: {}", repositoryName);
        } catch (JCoException e) {
            log.error("Connect SAP fault, error msg: " + e.toString());
            throw e;
        }
        return destination;
    }
}

