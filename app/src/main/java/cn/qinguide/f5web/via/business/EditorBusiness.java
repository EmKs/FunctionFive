package cn.qinguide.f5web.via.business;

import com.kunminx.architecture.business.BaseBusiness;

import cn.qinguide.f5web.via.business.bus.EditorBus;
import cn.qinguide.f5web.via.business.request.IEditorRequest;

public class EditorBusiness extends BaseBusiness<EditorBus> implements IEditorRequest {
    @Override
    public void onDestroy() {

    }
}
