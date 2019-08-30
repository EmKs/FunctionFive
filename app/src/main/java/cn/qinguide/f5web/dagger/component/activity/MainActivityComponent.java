package cn.qinguide.f5web.dagger.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import cn.qinguide.f5web.dagger.module.activity.MainActivityModule;
import cn.qinguide.f5web.via.ui.activity.MainActivity;
import dagger.Component;

@ActivityScope
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

}
