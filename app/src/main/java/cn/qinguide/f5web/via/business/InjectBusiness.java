package cn.qinguide.f5web.via.business;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

import com.kunminx.architecture.business.BaseBusiness;
import com.kunminx.architecture.business.bus.Result;
import com.ljy.devring.DevRing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.qinguide.f5web.common.constant.KeyConstants;
import cn.qinguide.f5web.common.db.dao.AppInfoEntityDao;
import cn.qinguide.f5web.common.db.manger.AppInfoEntityManager;
import cn.qinguide.f5web.common.entity.AppInfoEntity;
import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.business.request.IInjectRequest;
import io.reactivex.ObservableEmitter;

public class InjectBusiness extends BaseBusiness<MainBus> implements IInjectRequest {

    private List<AppInfoEntity> appInfos = new ArrayList<>();

    private List<AppInfoEntity> selectInfos = new ArrayList<>();

    private List<AppInfoEntity> searchInfos = new ArrayList<>();

    @Override
    public void onDestroy() {

    }

    @Override
    public void getAppInfos(final Context context) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(0);
                if (packageInfos != null && packageInfos.size() > 0) {
                    appInfos.clear();
                    selectInfos.clear();
                    selectInfos = DevRing.tableManager(AppInfoEntity.class).loadAll();
                    for (PackageInfo packageInfo : packageInfos) {
                        if (selectInfos.size() > 0)
                            for (AppInfoEntity appInfoEntity : selectInfos) {
                                if (appInfoEntity.getPackageName().contains(packageInfo.applicationInfo.packageName)) {
                                    appInfoEntity.setAppName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
                                    continue;
                                }
                                AppInfoEntity appInfo = new AppInfoEntity();
                                appInfo.setPackageName(packageInfo.applicationInfo.packageName);
                                appInfo.setAppName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
                                appInfos.add(appInfo);
                            }
                        else {
                            AppInfoEntity appInfo = new AppInfoEntity();
                            appInfo.setPackageName(packageInfo.applicationInfo.packageName);
                            appInfo.setAppName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
                            appInfos.add(appInfo);
                        }
                    }
                    selectInfos.addAll(appInfos);
                    return new Result(KeyConstants.APP_LIST, selectInfos);
                } else return new Result(KeyConstants.APP_LIST_ERROR, null);
            }
        });
    }

    @Override
    public void searchAppInfos(final String appName) {
        if (appInfos == null) return;
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                searchInfos.clear();
                for (AppInfoEntity appInfoEntity : appInfos) {
                    if (appInfoEntity.getAppName().toLowerCase().contains(appName.toLowerCase()))
                        searchInfos.add(appInfoEntity);
                }
                return new Result(KeyConstants.APP_LIST_SEARCH, searchInfos);
            }
        });
    }

    @Override
    public void saveAppPlan(final String packageName, final int planId) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                long count = DevRing.<AppInfoEntityManager>tableManager(AppInfoEntity.class).getDao().queryBuilder().where(AppInfoEntityDao.Properties.PackageName.eq(packageName)).count();
                if (count > 0) {
                    AppInfoEntity appInfoEntity = DevRing.<AppInfoEntityManager>tableManager(AppInfoEntity.class)
                            .getDao().queryBuilder().where(AppInfoEntityDao.Properties.PackageName.eq(packageName)).build().list().get(0);
                    appInfoEntity.setAppPlan(planId);
                    DevRing.tableManager(AppInfoEntity.class).updateOne(appInfoEntity);
                    appInfoEntity = null;
                } else {
                    AppInfoEntity appInfoEntity = new AppInfoEntity();
                    appInfoEntity.setPackageName(packageName);
                    appInfoEntity.setAppPlan(planId);
                    DevRing.tableManager(AppInfoEntity.class).insertOne(appInfoEntity);
                    appInfoEntity = null;
                }
                return null;
            }
        });
    }

    @Override
    public void removeAppPlan(final String packageName) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                List<AppInfoEntity> appInfoEntities = DevRing.<AppInfoEntityManager>tableManager(AppInfoEntity.class).getDao().queryBuilder().where(AppInfoEntityDao.Properties.PackageName.eq(packageName)).build().list();
                if (appInfoEntities != null && appInfoEntities.size() > 0) {
                    DevRing.tableManager(AppInfoEntity.class).deleteOne(appInfoEntities.get(0));
                    appInfoEntities.clear();
                    appInfoEntities = null;
                }
                return null;
            }
        });
    }
}
