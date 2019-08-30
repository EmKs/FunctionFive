package cn.qinguide.f5web.via.ui.activity;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;

import com.kunminx.architecture.business.bus.Result;

import javax.inject.Inject;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.view.BaseActivity;
import cn.qinguide.f5web.dagger.component.activity.DaggerEditorActivityComponent;
import cn.qinguide.f5web.dagger.module.activity.EditorActivityModule;
import cn.qinguide.f5web.databinding.ActivityEditorBinding;
import cn.qinguide.f5web.via.business.EditorBusiness;
import cn.qinguide.f5web.via.business.bus.EditorBus;
import cn.qinguide.f5web.via.ui.fragment.EditorFragment;

public class EditorActivity extends BaseActivity<ActivityEditorBinding> {

    @Inject
    EditorBusiness editorBusiness;
    @Inject
    EditorFragment editorFragment;

    @Override
    protected String[] initPermissions() {
        return null;
    }

    @Override
    protected Toolbar setToolbar() {
        return dataBinding.layoutToolbar.toolbarCommon;
    }

    @Override
    protected boolean showToolbar() {
        return true;
    }

    @Override
    protected boolean showToolbarBack() {
        return true;
    }

    @Override
    protected void permissionDenied() {

    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_editor;
    }

    @Override
    protected void onResultHandle(Result testResult) {

    }

    @Override
    protected void register() {
        DaggerEditorActivityComponent.builder().editorActivityModule(new EditorActivityModule()).build().inject(this);
        EditorBus.registerRequestHandler(editorBusiness);
    }

    @Override
    protected void unRegister() {
        if (editorBusiness != null) {
            EditorBus.unregisterRequestHandler(editorBusiness);
            editorBusiness = null;
        }
    }

    @Override
    protected void initView() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.editor_script);
        loadRootFragment(R.id.frame_layout_editor, editorFragment);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public BaseActivity getActivity() {
        return this;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    public boolean isUseFragment() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                EditorActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}