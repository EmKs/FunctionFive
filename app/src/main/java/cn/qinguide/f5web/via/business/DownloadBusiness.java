package cn.qinguide.f5web.via.business;

import com.kunminx.architecture.business.BaseBusiness;
import com.kunminx.architecture.business.bus.Result;
import com.ljy.devring.DevRing;
import com.ljy.devring.base.fragment.IBaseFragment;
import com.ljy.devring.http.support.observer.CommonObserver;
import com.ljy.devring.http.support.throwable.HttpThrowable;
import com.ljy.devring.util.RxLifecycleUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.io.IOException;
import java.util.List;

import cn.qinguide.f5web.common.constant.KeyConstants;
import cn.qinguide.f5web.common.db.manger.ScriptEntityManager;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.common.entity.base.Page;
import cn.qinguide.f5web.common.entity.base.Response;
import cn.qinguide.f5web.common.service.FiveApiService;
import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.business.request.IDownloadRequest;
import io.reactivex.ObservableEmitter;

public class DownloadBusiness extends BaseBusiness<MainBus> implements IDownloadRequest {

    @Override
    public void initScript(int page, int size, final String type, IBaseFragment iBaseFragment) {
        DevRing.httpManager().commonRequest(DevRing.httpManager().getService(FiveApiService.class).getScriptList(page, size), new CommonObserver<Response<Page<ScriptEntity>>>() {

            @Override
            public void onResult(final Response<Page<ScriptEntity>> result) {
                handleRequest(new IAsync() {
                    @Override
                    public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                        if (result.getCode() == 0) {
                            if (result.getData().getCurrent() >= result.getData().getPages())
                                return new Result(KeyConstants.SCRIPT_LIST_FINISHED, result.getData().getRecords());
                            else
                                return new Result(type, result.getData().getRecords());
                        }
                        return new Result(KeyConstants.SCRIPT_LIST_ERROR, result.getMsg());
                    }
                });
            }

            @Override
            public void onError(final HttpThrowable httpThrowable) {
                handleRequest(new IAsync() {
                    @Override
                    public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                        return new Result(KeyConstants.SCRIPT_LIST_ERROR, httpThrowable.message);
                    }
                });
            }
        }, RxLifecycleUtil.bindUntilEvent(iBaseFragment, FragmentEvent.DESTROY));
    }

    @Override
    public void saveScript(final ScriptEntity scriptEntity) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                boolean state = DevRing.<ScriptEntityManager>tableManager(ScriptEntity.class).insertOne(scriptEntity);
                if (state) return new Result(KeyConstants.SAVE_SCRIPT, scriptEntity.getScriptName());
                else return new Result(KeyConstants.SAVE_SCRIPT_ERROR, null);
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
