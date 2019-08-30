package cn.qinguide.f5web.via.business.bus;

import com.kunminx.architecture.business.bus.BaseBus;

import cn.qinguide.f5web.via.business.request.IEditorRequest;

public class EditorBus extends BaseBus {

    public static IEditorRequest editor(){
        return (IEditorRequest) getRequest(IEditorRequest.class);
    }

}
