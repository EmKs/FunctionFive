package cn.qinguide.f5web.dagger.component.fragment;

import com.ljy.devring.di.scope.FragmentScope;

import cn.qinguide.f5web.dagger.module.fragment.HomeFragmentModule;
import cn.qinguide.f5web.via.ui.fragment.HomeFragment;
import dagger.Component;

@FragmentScope
@Component(modules = HomeFragmentModule.class)
public interface HomeFragmentComponent {

    void inject(HomeFragment homeFragment);

}
