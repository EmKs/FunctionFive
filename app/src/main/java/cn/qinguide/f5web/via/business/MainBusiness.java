package cn.qinguide.f5web.via.business;

import com.kunminx.architecture.business.BaseBusiness;
import com.kunminx.architecture.business.bus.Result;
import com.ljy.devring.DevRing;

import java.io.IOException;

import cn.qinguide.f5web.common.constant.KeyConstants;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.common.entity.XposedEntity;
import cn.qinguide.f5web.common.utils.ActiveUtil;
import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.business.request.IMainRequest;
import io.reactivex.ObservableEmitter;

import static android.view.View.X;

public class MainBusiness extends BaseBusiness<MainBus> implements IMainRequest {

    @Override
    public void onDestroy() {

    }

    @Override
    public void setFragment(final int FragmentId) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return new Result(KeyConstants.SET_FRAGMENT, FragmentId);
            }
        });
    }

    @Override
    public void setToolbarTitle(final String title) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return new Result(KeyConstants.SET_TOOLTAB_TITLE, title);
            }
        });
    }

    @Override
    public void initActive() {
        long count = DevRing.tableManager(XposedEntity.class).count();
        if (count == 1) {
            XposedEntity xposedEntity = (XposedEntity) DevRing.tableManager(XposedEntity.class).loadAll().get(0);
            ActiveUtil.isF5Active = xposedEntity.getEnabled();
        } else {
            if (count > 1) DevRing.tableManager(XposedEntity.class).deleteAll();
            XposedEntity xposedEntity = new XposedEntity();
            xposedEntity.setEnabled(true);
            ActiveUtil.isF5Active = xposedEntity.getEnabled();
            DevRing.tableManager(XposedEntity.class).insertOne(xposedEntity);
        }
    }

    @Override
    public void setActive(boolean isEnabled) {
        XposedEntity xposedEntity = (XposedEntity) DevRing.tableManager(XposedEntity.class).loadAll().get(0);
        xposedEntity.setEnabled(isEnabled);
        ActiveUtil.isF5Active = isEnabled;
        DevRing.tableManager(XposedEntity.class).updateOne(xposedEntity);
    }

    @Override
    public void startEditorActivity() {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return new Result(KeyConstants.START_EDITOR_ACTIVITY, null);
            }
        });
    }

}
