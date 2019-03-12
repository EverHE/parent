package com.he.syslog;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppVisitor implements Serializable {
    private static final long serialVersionUID = 8979911305912905771L;
    protected Logger log = LoggerFactory.getLogger(AppVisitor.class);

    private String orgId;// 操作者所属的组织ID
    private String orgType;// 操作者所属的组织类型
    private Integer plat;// 操作者所属的组织类型

    private String operatorId;// 操作者的id
    private String operatorUid;// 操作者的uid,uuid或者其它业务需要的标识
    private String operatorType;// 操作者的类型
    private String operatorName;// 操作者的名称
    private String operatorLoginName;// 操作者的登录名
    private String phone;

    private String ip;// 操作者的登陆ip

    private Boolean mobile;// 是否移动设备
    private String mobileBrand;// 手机品牌信息
    private String os;// 操作系统
    private String osVersion;// 操作系统版本
    private String browser;// 浏览器
    private String browserVersion;// 浏览器版本
    private String ua;// navigator.userAgent

    @JSONField(serialize = false)
    private List<String> keys;

    public AppVisitor() {
        super();
    }

    public AppVisitor(String operatorId, String operatorName, String operatorLoginName) {
        super();
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.operatorLoginName = operatorLoginName;
    }

    public AppVisitor(String operatorId, String operatorName, String operatorLoginName, String ip) {
        this(operatorId, operatorName, operatorLoginName);
        this.ip = ip;
    }

    public AppVisitor(String operatorId, String operatorName, String operatorLoginName, String operatorType,
                      String ip) {
        this(operatorId, operatorName, operatorLoginName, ip);
        this.operatorType = operatorType;
    }

    public AppVisitor(String orgId, String orgType, String operatorId, String operatorName, String operatorLoginName,
                      String operatorType) {
        this(operatorId, operatorName, operatorLoginName, operatorType, null);
        this.orgId = orgId;
        this.orgType = orgType;
    }

    public AppVisitor(String orgId, String orgType, String operatorId, String operatorName, String operatorLoginName,
                      String operatorType, String ip, Integer plat) {
        this(operatorId, operatorName, operatorLoginName, operatorType, ip);
        this.orgId = orgId;
        this.orgType = orgType;
    }

    public void setClient(Client client) {
        this.ip = client.getIp();
        this.mobile = client.getMobile();
        this.mobileBrand = client.getMobileBrand();
        this.os = client.getOs();
        this.osVersion = client.getOsVersion();
        this.browser = client.getBrowser();
        this.browserVersion = client.getBrowserVersion();
        this.ua = client.getUa();
    }

    public Integer getPlat() {
        return plat;
    }

    public void setPlat(Integer plat) {
        this.plat = plat;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorLoginName() {
        return operatorLoginName;
    }

    public void setOperatorLoginName(String operatorLoginName) {
        this.operatorLoginName = operatorLoginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOperatorUid() {
        return operatorUid;
    }

    public void setOperatorUid(String operatorUid) {
        this.operatorUid = operatorUid;
    }

    public Boolean getMobile() {
        return mobile;
    }

    public void setMobile(Boolean mobile) {
        this.mobile = mobile;
    }

    public String getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(String mobileBrand) {
        this.mobileBrand = mobileBrand;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    @JSONField(serialize = false)
    public List<String> getKeys() {
        return keys;
    }

    @JSONField(serialize = false)
    public String getTopKey() {
        return (keys == null || keys.size() <= 0) ? null : keys.iterator().next();
    }

    public void addKey(String newKey) {
        if (keys == null) {
            keys = new ArrayList<String>();
        }
        keys.add(newKey);
    }

    public void clearAndAddKey(String newKey) {
        if (keys == null) {
            keys = new ArrayList<String>();
        } else if (keys.size() > 0) {
            keys.clear();
        }
        keys.add(newKey);
    }

    public void addKeys(List<String> ks) {
        if (keys == null) {
            keys = new ArrayList<String>();
        }
        keys.addAll(ks);
    }

    public void clearAndAddKeys(List<String> ks) {
        if (keys == null) {
            keys = new ArrayList<String>();
        } else if (keys.size() > 0) {
            keys.clear();
        }
        keys.addAll(ks);
    }

    public void removeKey(String key) {
        if (keys != null && keys.size() > 0) {
            keys.remove(key);
        }
    }

    public void removeAllKey() {
        if (keys != null && keys.size() > 0) {
            keys.clear();
        }
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public AppVisitor copyAppVisitor() {
        AppVisitor result = newAppVisitorInstance();
        try {
            BeanUtils.copyProperties(this, result);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    public AppVisitor copyAppVisitorAndCleanKeys() {
        AppVisitor result = copyAppVisitor();
        result.setKeys(null);
        return result;
    }

    public AppVisitor copyAppVisitorAndReplaceKey(String key) {
        AppVisitor result = copyAppVisitorAndCleanKeys();
        result.clearAndAddKey(key);
        return result;
    }

    public AppVisitor copyAppVisitorAndReplaceKeys(List<String> keys) {
        AppVisitor result = copyAppVisitorAndCleanKeys();
        result.clearAndAddKeys(keys);
        return result;
    }

    public AppVisitor newAppVisitorInstance() {
        return new AppVisitor();
    }

}
