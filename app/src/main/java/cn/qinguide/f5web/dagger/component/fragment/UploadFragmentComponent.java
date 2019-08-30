package cn.qinguide.f5web.dagger.component.fragment;

import com.ljy.devring.di.scope.FragmentScope;

import cn.qinguide.f5web.dagger.module.fragment.UploadFragmentModule;
import cn.qinguide.f5web.via.ui.fragment.UploadFragment;
import dagger.Component;

@FragmentScope
@Component(modules = UploadFragmentModule.class)
public interface UploadFragmentComponent {

    void inject(UploadFragment uploadFragment);

}
