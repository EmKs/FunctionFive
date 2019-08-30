package cn.qinguide.f5web.common.db.manger;

import com.ljy.devring.db.GreenTableManager;

import org.greenrobot.greendao.AbstractDao;

import cn.qinguide.f5web.common.db.dao.DaoSession;
import cn.qinguide.f5web.common.entity.XposedEntity;

public class XposedEntityManager extends GreenTableManager<XposedEntity, Long> {

    private DaoSession daoSession;

    @Override
    public AbstractDao<XposedEntity, Long> getDao() {
        return daoSession.getXposedEntityDao();
    }

    public XposedEntityManager(DaoSession daoSession){
        this.daoSession = daoSession;
    }

}
