package cn.qinguide.f5web.common.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class AppInfoEntity {

    @Id(autoincrement = true)
    private Long id;

    private int appPlan;

    private String packageName;

    private String appName;

    @Generated(hash = 910347944)
    public AppInfoEntity(Long id, int appPlan, String packageName, String appName) {
        this.id = id;
        this.appPlan = appPlan;
        this.packageName = packageName;
        this.appName = appName;
    }

    @Generated(hash = 1335805280)
    public AppInfoEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAppPlan() {
        return this.appPlan;
    }

    public void setAppPlan(int appPlan) {
        this.appPlan = appPlan;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
