package cn.qinguide.f5web.via.repertory.proxy;

import android.view.View;

import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.via.business.bus.MainBus;

public class DownloadProxy {

    public void onClicked(View view, ScriptEntity scriptEntity) {
        MainBus.download().saveScript(scriptEntity);
    }


}
