package cn.qinguide.f5web.dagger.component.fragment;

import com.ljy.devring.di.scope.FragmentScope;

import cn.qinguide.f5web.dagger.module.fragment.InjectFragmentModule;
import cn.qinguide.f5web.via.ui.fragment.InjectFragment;
import dagger.Component;

@FragmentScope
@Component(modules = InjectFragmentModule.class)
public interface InjectFragmentComponent {

    void inject(InjectFragment injectFragment);

}
