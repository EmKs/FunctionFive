package cn.qinguide.f5web.dagger.component.fragment;

import com.ljy.devring.di.scope.FragmentScope;

import cn.qinguide.f5web.dagger.module.fragment.AboutFragmentModule;
import cn.qinguide.f5web.via.ui.fragment.AboutFragment;
import dagger.Component;

@FragmentScope
@Component(modules = AboutFragmentModule.class)
public interface AboutFragmentComponent {

    void inject(AboutFragment aboutFragment);

}
