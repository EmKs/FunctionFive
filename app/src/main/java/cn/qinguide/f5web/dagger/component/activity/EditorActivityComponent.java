package cn.qinguide.f5web.dagger.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import cn.qinguide.f5web.dagger.module.activity.EditorActivityModule;
import cn.qinguide.f5web.via.ui.activity.EditorActivity;
import dagger.Component;

@ActivityScope
@Component(modules = EditorActivityModule.class)
public interface EditorActivityComponent {

    void inject(EditorActivity editorActivity);

}
