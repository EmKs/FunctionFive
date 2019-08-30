package cn.qinguide.f5web.dagger.module.fragment;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ljy.devring.di.scope.FragmentScope;

import cn.qinguide.f5web.common.view.iview.IFiveFragment;
import cn.qinguide.f5web.editor.CodePane;
import cn.qinguide.f5web.editor.PreformEdit;
import dagger.Module;
import dagger.Provides;

@Module
public class EditorFragmentModule {

    private IFiveFragment iView;

    public EditorFragmentModule(IFiveFragment iView) {
        this.iView = iView;
    }

    @FragmentScope
    @Provides
    CodePane codePane() {
        return new CodePane(iView.getFragmentContext());
    }


    @FragmentScope
    @Provides
    PreformEdit preformEdit() {
        return new PreformEdit(codePane().getCodeText());
    }

    @FragmentScope
    @Provides
    FrameLayout.LayoutParams params() {
        return new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }


}
