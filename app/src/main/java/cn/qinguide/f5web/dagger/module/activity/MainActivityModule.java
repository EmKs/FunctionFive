package cn.qinguide.f5web.dagger.module.activity;

import com.ljy.devring.di.scope.ActivityScope;

import cn.qinguide.f5web.via.business.DownloadBusiness;
import cn.qinguide.f5web.via.business.HomeBusiness;
import cn.qinguide.f5web.via.business.InjectBusiness;
import cn.qinguide.f5web.via.business.MainBusiness;
import cn.qinguide.f5web.via.business.UploadBusiness;

import cn.qinguide.f5web.via.ui.fragment.AboutFragment;
import cn.qinguide.f5web.via.ui.fragment.DownloadFragment;
import cn.qinguide.f5web.via.ui.fragment.HomeFragment;
import cn.qinguide.f5web.via.ui.fragment.InjectFragment;
import cn.qinguide.f5web.via.ui.fragment.SupportFragment;
import cn.qinguide.f5web.via.ui.fragment.UploadFragment;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @ActivityScope
    @Provides
    MainBusiness mainBusiness() {
        return new MainBusiness();
    }

    @ActivityScope
    @Provides
    HomeBusiness homeBusiness() {
        return new HomeBusiness();
    }

    @ActivityScope
    @Provides
    UploadBusiness uploadBusiness() {
        return new UploadBusiness();
    }

    @ActivityScope
    @Provides
    InjectBusiness injectBusiness() {
        return new InjectBusiness();
    }

    @ActivityScope
    @Provides
    DownloadBusiness downloadBusiness() {
        return new DownloadBusiness();
    }

    @ActivityScope
    @Provides
    HomeFragment homeFragment() {
        return HomeFragment.newInstance();
    }

    @ActivityScope
    @Provides
    DownloadFragment downloadFragment() {
        return DownloadFragment.newInstance();
    }

    @ActivityScope
    @Provides
    UploadFragment uploadFragment() {
        return UploadFragment.newInstance();
    }

    @ActivityScope
    @Provides
    InjectFragment injectFragment() {
        return InjectFragment.newInstance();
    }

    @ActivityScope
    @Provides
    AboutFragment aboutFragment() {
        return AboutFragment.newInstance();
    }

    @ActivityScope
    @Provides
    SupportFragment supportFragment() {
        return SupportFragment.newInstance();
    }

}
