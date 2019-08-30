package cn.qinguide.f5web.via.repertory.proxy;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.CompoundButton;

import com.afollestad.materialdialogs.MaterialDialog;

import com.ljy.devring.DevRing;
import com.ljy.devring.other.toast.RingToast;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.db.dao.AppInfoEntityDao;
import cn.qinguide.f5web.common.db.manger.AppInfoEntityManager;
import cn.qinguide.f5web.common.entity.AppInfoEntity;
import cn.qinguide.f5web.via.business.bus.MainBus;

public class InjectProxy {

    private int planId = 0;
    private Context context;
    private String packageName;
    private CompoundButton buttonView;

    private MaterialDialog materialDialog() {
        return new MaterialDialog.Builder(context)
                .cancelable(false)
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (buttonView != null) {
                            buttonView.setChecked(false);
                            packageName = null;
                            buttonView = null;
                            planId = 0;
                        }
                    }
                })
                .negativeText(R.string.cancel)
                .title(context.getString(R.string.set_inject_plan))
                .itemsCallbackSingleChoice(planId-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        MainBus.inject().saveAppPlan(packageName, which+1);
                        packageName = null;
                        buttonView = null;
                        planId = 0;
                        return false;
                    }
                })
                .items(context.getResources().getStringArray(R.array.plan))
                .build();
    }

    public InjectProxy(Context context) {
        this.context = context;
    }

    public void switchItem(View view, String packageName) {
        if (packageName==null)return;
        buttonView = (CompoundButton) view;
        if (buttonView.isChecked()) {
            this.packageName = packageName;
            materialDialog().show();
        } else MainBus.inject().removeAppPlan(packageName);
    }

    public void selectPlan(AppInfoEntity appInfoEntity) {
        long count = DevRing.<AppInfoEntityManager>tableManager(AppInfoEntity.class).getDao().queryBuilder().where(AppInfoEntityDao.Properties.PackageName.eq(appInfoEntity.getPackageName())).count();
        if (count <= 0) RingToast.show(R.string.switch_enabled);
        else {
            this.planId = appInfoEntity.getAppPlan();
            this.packageName = appInfoEntity.getPackageName();
            materialDialog().show();
        }
    }

}
