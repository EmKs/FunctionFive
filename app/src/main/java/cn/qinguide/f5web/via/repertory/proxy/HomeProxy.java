package cn.qinguide.f5web.via.repertory.proxy;

import android.view.View;
import android.widget.CompoundButton;

import com.ljy.devring.other.toast.RingToast;

import org.greenrobot.eventbus.EventBus;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.common.utils.ActiveUtil;
import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.ui.activity.MainActivity;

public class HomeProxy {

    public void active(CompoundButton buttonView, boolean isChecked) {
        if (!ActiveUtil.isModuleActive()) {
            RingToast.show(R.string.xposed_disabled);
            buttonView.setChecked(ActiveUtil.isModuleActive());
        } else MainBus.main().setActive(isChecked);
        MainBus.home().updateTips();
    }

    public void createScript(View view) {
        MainBus.main().startEditorActivity();
    }

    public void editScript(View view, ScriptEntity scriptEntity) {
        MainBus.main().startEditorActivity();
        EventBus.getDefault().postSticky(scriptEntity);
    }

    public void addInject(View view) {
        MainBus.main().setFragment(MainActivity.INJECT);
        MainBus.main().setToolbarTitle(view.getContext().getString(R.string.add_inject));
    }

    public void switchItem(View view, ScriptEntity scriptEntity) {
        CompoundButton buttonView = (CompoundButton) view;
        boolean isChecked = buttonView.isChecked();
        scriptEntity.setIsEnabled(isChecked);
        MainBus.home().updateScriptItem(isChecked, scriptEntity.getId());
    }

}

