package cn.qinguide.f5web.via.business;

import com.kunminx.architecture.business.BaseBusiness;

import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.business.request.ISupportRequest;

public class SupportBusiness extends BaseBusiness<MainBus> implements ISupportRequest {
    @Override
    public void onDestroy() {

    }
}
