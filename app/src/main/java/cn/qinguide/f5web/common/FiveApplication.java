package cn.qinguide.f5web.common;

import android.app.Application;

import com.ljy.devring.DevRing;

import cn.qinguide.f5web.EventBusIndex;
import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.constant.PathConstants;
import cn.qinguide.f5web.common.db.GreenDBManager;

public class FiveApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDevRing();
    }

    private void initDevRing() {
        DevRing.init(this);
        DevRing.configureHttp().setBaseUrl(PathConstants.BASE_URL)
                .setConnectTimeout(15)
                .setIsUseCache(true)
                .setIsCookiePersistent(true)
                .setIsUseCache(true);
        DevRing.configureImage().setLoadingResId(R.drawable.ic_loading)
                .setErrorResId(R.drawable.ic_loading_error)
                .setIsShowTransition(true);
        DevRing.configureDB(new GreenDBManager());
        DevRing.configureBus().setIndex(new EventBusIndex()).setIsUseIndex(true);
        DevRing.create();
    }

}
