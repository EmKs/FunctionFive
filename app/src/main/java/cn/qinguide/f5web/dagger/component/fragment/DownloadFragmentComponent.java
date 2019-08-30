package cn.qinguide.f5web.dagger.component.fragment;

import com.ljy.devring.di.scope.FragmentScope;

import cn.qinguide.f5web.dagger.module.fragment.DownloadFragmentModule;
import cn.qinguide.f5web.via.ui.fragment.DownloadFragment;
import dagger.Component;

@FragmentScope
@Component(modules = DownloadFragmentModule.class)
public interface DownloadFragmentComponent {

    void inject(DownloadFragment downloadFragment);

}
