package cn.qinguide.f5web.dagger.component.fragment;

import com.ljy.devring.di.scope.FragmentScope;

import cn.qinguide.f5web.dagger.module.fragment.EditorFragmentModule;
import cn.qinguide.f5web.via.ui.fragment.EditorFragment;
import dagger.Component;

@FragmentScope
@Component(modules = EditorFragmentModule.class)
public interface EditorFragmentComponent {

    void inject(EditorFragment editorFragment);

}
