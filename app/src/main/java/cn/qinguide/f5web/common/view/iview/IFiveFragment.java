package cn.qinguide.f5web.common.view.iview;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.ljy.devring.base.fragment.IBaseFragment;


public interface IFiveFragment extends IBaseFragment {

    Context getFragmentContext();

    FragmentActivity getFragmentActivity();

}
