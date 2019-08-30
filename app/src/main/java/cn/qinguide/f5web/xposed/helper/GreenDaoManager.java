package cn.qinguide.f5web.xposed.helper;

import android.content.Context;

import com.ljy.devring.db.support.GreenOpenHelper;

import cn.qinguide.f5web.common.db.dao.AppInfoEntityDao;
import cn.qinguide.f5web.common.db.dao.DaoMaster;
import cn.qinguide.f5web.common.db.dao.DaoSession;
import cn.qinguide.f5web.common.db.dao.ScriptEntityDao;
import cn.qinguide.f5web.common.db.dao.XposedEntityDao;

public class GreenDaoManager {

    private static final String TAG = "GreenDaoManager";

    private static final String DATABASE_NAME = "f5web.db";
    /**
     * 全局保持一个DaoSession
     */
    private DaoSession daoSession;

    private boolean isInited;

    private static final class GreenDaoManagerHolder {
        private static final GreenDaoManager sInstance = new GreenDaoManager();
    }

    public static GreenDaoManager getInstance() {
        return GreenDaoManagerHolder.sInstance;
    }

    private GreenDaoManager() {

    }

    /**
     * 初始化DaoSession
     *
     * @param context
     */
    public void init(Context context) {
        if (!isInited) {
            DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(
                    context.getApplicationContext(), DATABASE_NAME, null);
            GreenOpenHelper greenOpenHelper = new GreenOpenHelper(context, DATABASE_NAME, DaoMaster.SCHEMA_VERSION,
                    new Class[]{ScriptEntityDao.class, AppInfoEntityDao.class,  XposedEntityDao.class});
            DaoMaster daoMaster = new DaoMaster(greenOpenHelper.getWritableDatabase());
            daoSession = daoMaster.newSession();
            isInited = true;
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
