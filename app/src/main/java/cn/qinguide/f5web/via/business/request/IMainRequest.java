package cn.qinguide.f5web.via.business.request;

import com.kunminx.architecture.business.bus.IRequest;

import cn.qinguide.f5web.common.entity.ScriptEntity;

public interface IMainRequest extends IRequest {

    void setFragment(int FragmentId);

    void setToolbarTitle(String title);

    void setActive(boolean isEnabled);

    void startEditorActivity();

    void initActive();

}
