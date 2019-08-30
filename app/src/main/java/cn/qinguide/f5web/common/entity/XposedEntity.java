package cn.qinguide.f5web.common.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class XposedEntity {

    @Id(autoincrement = true)
    private Long id;
    private boolean enabled;
    @Generated(hash = 1234930747)
    public XposedEntity(Long id, boolean enabled) {
        this.id = id;
        this.enabled = enabled;
    }
    @Generated(hash = 1199439812)
    public XposedEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
