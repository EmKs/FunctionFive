package cn.qinguide.f5web.via.business.request;

import com.kunminx.architecture.business.bus.IRequest;

public interface IHomeRequest extends IRequest {

    void updateTips();

    void updateScript();

    void getLocalScript();

    void updateScriptItem(boolean isSwitch, Long scriptId);

}
