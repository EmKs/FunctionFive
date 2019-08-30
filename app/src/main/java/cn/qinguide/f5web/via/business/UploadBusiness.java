package cn.qinguide.f5web.via.business;

import com.kunminx.architecture.business.BaseBusiness;

import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.business.request.IUploadRequest;

public class UploadBusiness extends BaseBusiness<MainBus> implements IUploadRequest {
    @Override
    public void onDestroy() {

    }
}
