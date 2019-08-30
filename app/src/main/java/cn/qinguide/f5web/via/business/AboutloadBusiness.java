package cn.qinguide.f5web.via.business;

import com.kunminx.architecture.business.BaseBusiness;

import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.business.request.IAboutRequest;

public class AboutloadBusiness extends BaseBusiness<MainBus> implements IAboutRequest {
    @Override
    public void onDestroy() {

    }
}
