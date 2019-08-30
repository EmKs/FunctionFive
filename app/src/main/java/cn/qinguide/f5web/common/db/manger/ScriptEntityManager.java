package cn.qinguide.f5web.common.db.manger;

import com.ljy.devring.db.GreenTableManager;

import org.greenrobot.greendao.AbstractDao;

import cn.qinguide.f5web.common.db.dao.DaoSession;
import cn.qinguide.f5web.common.entity.ScriptEntity;

public class ScriptEntityManager extends GreenTableManager<ScriptEntity, Long> {

    private DaoSession daoSession;

    @Override
    public AbstractDao<ScriptEntity, Long> getDao() {
        return daoSession.getScriptEntityDao();
    }

    public ScriptEntityManager(DaoSession daoSession){
        this.daoSession = daoSession;
    }

}
