package cn.qinguide.f5web.common.db.manger;

import com.ljy.devring.db.GreenTableManager;

import org.greenrobot.greendao.AbstractDao;

import cn.qinguide.f5web.common.db.dao.DaoSession;
import cn.qinguide.f5web.common.entity.AppInfoEntity;

public class AppInfoEntityManager extends GreenTableManager<AppInfoEntity, Long> {

    private DaoSession daoSession;

    @Override
    public AbstractDao<AppInfoEntity, Long> getDao() {
        return daoSession.getAppInfoEntityDao();
    }

    public AppInfoEntityManager(DaoSession daoSession){
        this.daoSession = daoSession;
    }

}
