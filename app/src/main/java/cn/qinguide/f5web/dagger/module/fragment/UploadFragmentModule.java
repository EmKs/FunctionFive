package cn.qinguide.f5web.dagger.module.fragment;

import cn.qinguide.f5web.common.view.iview.IFiveFragment;
import dagger.Module;

@Module
public class UploadFragmentModule {

    private IFiveFragment iView;

    public UploadFragmentModule(IFiveFragment iView) {
        this.iView = iView;
    }


}
