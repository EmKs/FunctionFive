package cn.qinguide.f5web.dagger.component.fragment;

import com.ljy.devring.di.scope.FragmentScope;

import cn.qinguide.f5web.dagger.module.fragment.SupportFragmentModule;
import cn.qinguide.f5web.via.ui.fragment.SupportFragment;
import dagger.Component;

@FragmentScope
@Component(modules = SupportFragmentModule.class)
public interface SupportFragmentComponent {

    void inject(SupportFragment supportFragment);

}
