package cn.qinguide.f5web.common.db.module;

import android.app.Application;

import com.ljy.devring.db.support.GreenOpenHelper;

import org.greenrobot.greendao.AbstractDao;

import cn.qinguide.f5web.common.db.dao.AppInfoEntityDao;
import cn.qinguide.f5web.common.db.dao.DaoMaster;
import cn.qinguide.f5web.common.db.dao.DaoSession;
import cn.qinguide.f5web.common.db.dao.ScriptEntityDao;
import cn.qinguide.f5web.common.db.dao.XposedEntityDao;
import cn.qinguide.f5web.common.db.manger.AppInfoEntityManager;
import cn.qinguide.f5web.common.db.manger.ScriptEntityManager;
import cn.qinguide.f5web.common.db.manger.XposedEntityManager;
import cn.qinguide.f5web.common.db.qualifier.GreenDB;
import cn.qinguide.f5web.common.db.scope.DBScope;
import dagger.Module;
import dagger.Provides;

@Module
public class GreenDBModule {

    @DBScope
    @Provides
    DaoSession daoSession(DaoMaster daoMaster) {
        return daoMaster.newSession();
    }

    @DBScope
    @Provides
    DaoMaster daoMaster(GreenOpenHelper greenOpenHelper) {
        //这里使用DevRing提供的GreenOpenHelper对DaoMaster进行初始化，这样就可以实现数据库升级时的数据迁移
        //默认的DaoMaster.OpenHelper不具备数据迁移功能，它会在数据库升级时将数据删除。
        return new DaoMaster(greenOpenHelper.getWritableDatabase());
//        return new DaoMaster(greenOpenHelper.getEncryptedWritableDb("your_secret"));
    }

    @DBScope
    @Provides
    GreenOpenHelper greenOpenHelper(Application context, @GreenDB String dbName, @GreenDB Integer schemaVersion, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        return new GreenOpenHelper(context, dbName, schemaVersion, daoClasses);
    }

    @GreenDB
    @DBScope
    @Provides
    String dbName() {
        return "f5web.db";
    }

    @GreenDB
    @DBScope
    @Provides
    Integer schemaVersion() {
        //返回数据库版本号，使用DaoMaster.SCHEMA_VERSION即可
        return DaoMaster.SCHEMA_VERSION;
    }

    @DBScope
    @Provides
    Class<? extends AbstractDao<?, ?>>[] daoClasses() {
        //传入各数据表对应的Dao类
        return new Class[]{ScriptEntityDao.class, AppInfoEntityDao.class, XposedEntityDao.class};
    }

    @DBScope
    @Provides
    ScriptEntityManager scriptEntityManager(DaoSession daoSession) {
        return new ScriptEntityManager(daoSession);
    }

    @DBScope
    @Provides
    AppInfoEntityManager appInfoEntityManager(DaoSession daoSession) {
        return new AppInfoEntityManager(daoSession);
    }

    @DBScope
    @Provides
    XposedEntityManager xposedEntityManager(DaoSession daoSession) {
        return new XposedEntityManager(daoSession);
    }

}
