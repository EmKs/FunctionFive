package cn.qinguide.f5web.via.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kunminx.architecture.business.bus.Result;
import com.ljy.devring.DevRing;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import cn.qinguide.f5web.common.constant.KeyConstants;
import cn.qinguide.f5web.common.view.BaseFragment;
import cn.qinguide.f5web.dagger.component.activity.DaggerMainActivityComponent;
import cn.qinguide.f5web.databinding.ActivityMainBinding;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.view.BaseActivity;
import cn.qinguide.f5web.dagger.module.activity.MainActivityModule;
import cn.qinguide.f5web.via.business.DownloadBusiness;
import cn.qinguide.f5web.via.business.HomeBusiness;
import cn.qinguide.f5web.via.business.InjectBusiness;
import cn.qinguide.f5web.via.business.MainBusiness;
import cn.qinguide.f5web.via.business.UploadBusiness;
import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.ui.fragment.AboutFragment;
import cn.qinguide.f5web.via.ui.fragment.DownloadFragment;
import cn.qinguide.f5web.via.ui.fragment.HomeFragment;
import cn.qinguide.f5web.via.ui.fragment.InjectFragment;
import cn.qinguide.f5web.via.ui.fragment.SupportFragment;
import cn.qinguide.f5web.via.ui.fragment.UploadFragment;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainBusiness mainBusiness;
    @Inject
    HomeBusiness homeBusiness;
    @Inject
    UploadBusiness uploadBusiness;
    @Inject
    DownloadBusiness downloadBusiness;
    @Inject
    InjectBusiness injectBusiness;
    @Inject
    HomeFragment homeFragment;
    @Inject
    UploadFragment uploadFragment;
    @Inject
    DownloadFragment downloadFragment;
    @Inject
    InjectFragment injectFragment;
    @Inject
    SupportFragment supportFragment;
    @Inject
    AboutFragment aboutFragment;

    private int index = HOME;
    public static final int HOME = 0;
    public static final int UPLOAD = 1;
    public static final int DOWNLOAD = 2;
    public static final int INJECT = 3;
    public static final int SUPPORT = 4;
    public static final int ABOUT = 5;
    private BaseFragment[] baseFragments = new BaseFragment[7];


    @Override
    protected String[] initPermissions() {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
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
        MainActivity.this.finish();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResultHandle(Result testResult) {
        String resultCode = (String) testResult.getResultCode();
        switch (resultCode) {
            case KeyConstants.SET_FRAGMENT:
                int currentIndex = (int) testResult.getResultObject();
                showOrHideFragment(currentIndex, baseFragments[currentIndex]);
                break;
            case KeyConstants.SET_TOOLTAB_TITLE:
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle((CharSequence) testResult.getResultObject());
                break;
            case KeyConstants.START_EDITOR_ACTIVITY:
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
                break;
        }
    }

    @Override
    protected void register() {
        DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule()).build().inject(this);
        MainBus.registerResponseObserver(this);
        MainBus.registerRequestHandler(mainBusiness);
        MainBus.registerRequestHandler(homeBusiness);
        MainBus.registerRequestHandler(uploadBusiness);
        MainBus.registerRequestHandler(downloadBusiness);
        MainBus.registerRequestHandler(injectBusiness);
    }

    @Override
    protected void unRegister() {

        if (mainBusiness != null) {
            MainBus.unregisterRequestHandler(mainBusiness);
            mainBusiness = null;
        }

        if (homeBusiness != null) {
            MainBus.unregisterRequestHandler(homeBusiness);
            homeBusiness = null;
        }

        if (downloadBusiness != null) {
            MainBus.unregisterRequestHandler(downloadBusiness);
            downloadBusiness = null;
        }

        if (uploadBusiness != null) {
            MainBus.unregisterRequestHandler(uploadBusiness);
            uploadBusiness = null;
        }

        if (injectBusiness != null) {
            MainBus.unregisterRequestHandler(injectBusiness);
            injectBusiness = null;
        }

    }

    @Override
    protected void initView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dataBinding.drawerLayoutMain, dataBinding.layoutToolbar.toolbarCommon, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dataBinding.drawerLayoutMain.addDrawerListener(toggle);
        toggle.syncState();
        initFragment();
    }

    public void initFragment() {
        BaseFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            baseFragments[MainActivity.HOME] = homeFragment;
            baseFragments[MainActivity.UPLOAD] = uploadFragment;
            baseFragments[MainActivity.DOWNLOAD] = downloadFragment;
            baseFragments[MainActivity.INJECT] = injectFragment;
            baseFragments[MainActivity.SUPPORT] = supportFragment;
            baseFragments[MainActivity.ABOUT] = aboutFragment;
            loadMultipleRootFragment(R.id.frame_layout_main, HOME,
                    baseFragments[HOME],
                    baseFragments[UPLOAD],
                    baseFragments[DOWNLOAD],
                    baseFragments[INJECT],
                    baseFragments[SUPPORT],
                    baseFragments[ABOUT]);
        } else {
            baseFragments[MainActivity.HOME] = firstFragment;
            baseFragments[MainActivity.UPLOAD] = findFragment(UploadFragment.class);
            baseFragments[MainActivity.DOWNLOAD] = findFragment(DownloadFragment.class);
            baseFragments[MainActivity.INJECT] = findFragment(InjectFragment.class);
            baseFragments[MainActivity.SUPPORT] = findFragment(SupportFragment.class);
            baseFragments[MainActivity.ABOUT] = findFragment(AboutFragment.class);
        }
    }


    @Override
    protected void initData() {
        MainBus.main().initActive();
    }

    @Override
    protected void initEvent() {
        dataBinding.navigationMain.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressedSupport() {
        if (!closeDrawerLayout())
            super.onBackPressedSupport();
    }

    private boolean closeDrawerLayout() {
        if (dataBinding.drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            dataBinding.drawerLayoutMain.closeDrawer(GravityCompat.START);
            return true;
        } else return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!closeDrawerLayout())
                    dataBinding.drawerLayoutMain.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
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
    public boolean isUseFragment() {
        return true;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(menuItem.getTitle());
        switch (menuItem.getItemId()) {
            case R.id.nav_item_model:
                showOrHideFragment(HOME, homeFragment);
                break;
            case R.id.nav_item_upload:
                showOrHideFragment(UPLOAD, uploadFragment);
                break;
            case R.id.nav_item_downloads:
                showOrHideFragment(DOWNLOAD, downloadFragment);
                break;
            case R.id.nav_item_inject:
                showOrHideFragment(INJECT, injectFragment);
                break;
            case R.id.nav_item_support:
                showOrHideFragment(SUPPORT, supportFragment);
                break;
            case R.id.nav_item_about:
                showOrHideFragment(ABOUT, aboutFragment);
                break;
        }
        closeDrawerLayout();
        return false;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    private void showOrHideFragment(int currentIndex, BaseFragment baseFragment) {
        if (currentIndex == index) return;
        showHideFragment(baseFragment, baseFragments[index]);
        index = currentIndex;
    }

}
