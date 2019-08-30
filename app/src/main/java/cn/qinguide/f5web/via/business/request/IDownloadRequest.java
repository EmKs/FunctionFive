package cn.qinguide.f5web.via.business.request;

import com.kunminx.architecture.business.bus.IRequest;
import com.ljy.devring.base.fragment.IBaseFragment;

import cn.qinguide.f5web.common.entity.ScriptEntity;

public interface IDownloadRequest extends IRequest {

    void initScript(int page, int size, String type, IBaseFragment iBaseFragment);

    void saveScript(ScriptEntity scriptEntity);
}
