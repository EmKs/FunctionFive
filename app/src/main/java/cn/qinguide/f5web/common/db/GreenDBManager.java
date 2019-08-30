package cn.qinguide.f5web.common.db;

import android.support.v4.util.SimpleArrayMap;

import com.ljy.devring.DevRing;
import com.ljy.devring.db.support.IDBManager;
import com.ljy.devring.db.support.ITableManger;

import javax.inject.Inject;

import cn.qinguide.f5web.common.db.component.DaggerDBComponent;
import cn.qinguide.f5web.common.db.dao.DaoSession;
import cn.qinguide.f5web.common.db.manger.AppInfoEntityManager;
import cn.qinguide.f5web.common.db.manger.ScriptEntityManager;
import cn.qinguide.f5web.common.db.manger.XposedEntityManager;
import cn.qinguide.f5web.common.entity.AppInfoEntity;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.common.entity.XposedEntity;

public class    GreenDBManager implements IDBManager {

    @Inject
    DaoSession daoSession;
    @Inject
    ScriptEntityManager scriptEntityManager;
    @Inject
    AppInfoEntityManager appInfoEntityManager;
    @Inject
    XposedEntityManager xposedEntityManager;

    @Override
    public void init() {
        DaggerDBComponent.builder().ringComponent(DevRing.ringComponent()).build().inject(this);
    }

    @Override
    public void putTableManager(SimpleArrayMap<Object, ITableManger> mapTables) {
        mapTables.put(ScriptEntity.class, scriptEntityManager);
        mapTables.put(AppInfoEntity.class, appInfoEntityManager);
        mapTables.put(XposedEntity.class, xposedEntityManager);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
