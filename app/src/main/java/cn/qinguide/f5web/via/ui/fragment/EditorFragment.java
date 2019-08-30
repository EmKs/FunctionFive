package cn.qinguide.f5web.via.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;

import com.kunminx.architecture.business.bus.Result;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.common.view.BaseFragment;
import cn.qinguide.f5web.dagger.component.fragment.DaggerEditorFragmentComponent;
import cn.qinguide.f5web.dagger.module.fragment.EditorFragmentModule;
import cn.qinguide.f5web.databinding.FragmentEditorBinding;
import cn.qinguide.f5web.editor.CodePane;
import cn.qinguide.f5web.editor.PreformEdit;
import cn.qinguide.f5web.via.business.bus.EditorBus;

public class EditorFragment extends BaseFragment<FragmentEditorBinding> {

    @Inject
    CodePane codePane;
    @Inject
    PreformEdit preformEdit;
    @Inject
    FrameLayout.LayoutParams params;

    private ScriptEntity scriptEntity;

    public EditorFragment() {

    }

    public static EditorFragment newInstance() {
        return new EditorFragment();
    }

    @Override
    protected String[] initPermissions() {
        return null;
    }

    @Override
    protected void permissionDenied() {

    }

    @Override
    public void onResultHandle(Result result) {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_editor;
    }

    @Override
    protected boolean getHasOptionsMenu() {
        return false;
    }

    @Override
    protected void register() {
        DaggerEditorFragmentComponent.builder().editorFragmentModule(new EditorFragmentModule(this)).build().inject(this);
        EditorBus.registerResponseObserver(this);
    }

    @Override
    protected void unRegister() {
        EditorBus.unregisterResponseObserver(this);
    }

    @Override
    protected void initView() {
        dataBinding.frameLayoutEdit.addView(codePane, params);
    }

    @Override
    protected void initData() {
        if (scriptEntity == null) scriptEntity = new ScriptEntity();
        else preformEdit.setDefaultText(scriptEntity.getScript());
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public Context getFragmentContext() {
        return getContext();
    }

    @Override
    public FragmentActivity getFragmentActivity() {
        return getActivity();
    }

    @Override
    public void onSaveState(Bundle bundleToSave) {

    }

    @Override
    public void onRestoreState(Bundle bundleToRestore) {

    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onScriptEntity(ScriptEntity scriptEntity) {
        this.scriptEntity = scriptEntity;
    }

}
