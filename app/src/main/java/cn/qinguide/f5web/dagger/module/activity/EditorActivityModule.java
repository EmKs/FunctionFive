package cn.qinguide.f5web.dagger.module.activity;

import com.ljy.devring.di.scope.ActivityScope;

import cn.qinguide.f5web.via.business.EditorBusiness;
import cn.qinguide.f5web.via.ui.fragment.EditorFragment;
import dagger.Module;
import dagger.Provides;

@Module
public class EditorActivityModule {

    @ActivityScope
    @Provides
    EditorBusiness editorBusiness() {
        return new EditorBusiness();
    }

    @ActivityScope
    @Provides
    EditorFragment editorFragment() {
        return EditorFragment.newInstance();
    }

}
