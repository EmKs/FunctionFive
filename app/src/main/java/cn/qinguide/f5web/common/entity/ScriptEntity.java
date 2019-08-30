package cn.qinguide.f5web.common.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ScriptEntity {

    /**
     * id : ID
     * name : 名称
     * downloadUrl : 下载地址
     * description : 简介
     * author : 作者
     * iconUrl : 图标
     * supportPage : 支持页面
     */

    @Id(autoincrement = true)
    private Long id;
    private String scriptId;
    private String scriptName;
    private String supportPage;
    private String userId;
    private String version;
    private Integer times;
    private String updateTime;
    private String createTime;
    private String state;
    private String iconUrl;
    private String script;
    private String author;
    private String scriptDescription;
    private boolean isEnabled;
    @Generated(hash = 742422074)
    public ScriptEntity(Long id, String scriptId, String scriptName,
            String supportPage, String userId, String version, Integer times,
            String updateTime, String createTime, String state, String iconUrl,
            String script, String author, String scriptDescription,
            boolean isEnabled) {
        this.id = id;
        this.scriptId = scriptId;
        this.scriptName = scriptName;
        this.supportPage = supportPage;
        this.userId = userId;
        this.version = version;
        this.times = times;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.state = state;
        this.iconUrl = iconUrl;
        this.script = script;
        this.author = author;
        this.scriptDescription = scriptDescription;
        this.isEnabled = isEnabled;
    }
    @Generated(hash = 2681210)
    public ScriptEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getScriptId() {
        return this.scriptId;
    }
    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }
    public String getScriptName() {
        return this.scriptName;
    }
    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }
    public String getSupportPage() {
        return this.supportPage;
    }
    public void setSupportPage(String supportPage) {
        this.supportPage = supportPage;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getVersion() {
        return this.version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public Integer getTimes() {
        return this.times;
    }
    public void setTimes(Integer times) {
        this.times = times;
    }
    public String getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getIconUrl() {
        return this.iconUrl;
    }
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public String getScript() {
        return this.script;
    }
    public void setScript(String script) {
        this.script = script;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getScriptDescription() {
        return this.scriptDescription;
    }
    public void setScriptDescription(String scriptDescription) {
        this.scriptDescription = scriptDescription;
    }
    public boolean getIsEnabled() {
        return this.isEnabled;
    }
    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    

}
