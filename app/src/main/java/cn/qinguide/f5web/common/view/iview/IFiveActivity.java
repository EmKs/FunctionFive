package cn.qinguide.f5web.common.view.iview;

import android.content.Context;

import com.ljy.devring.base.activity.IBaseActivity;

import cn.qinguide.f5web.common.view.BaseActivity;

public interface IFiveActivity extends IBaseActivity {

    Context getActivityContext();

    BaseActivity getActivity();

}
