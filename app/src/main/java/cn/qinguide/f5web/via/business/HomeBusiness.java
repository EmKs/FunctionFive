package cn.qinguide.f5web.via.business;

import com.kunminx.architecture.business.BaseBusiness;
import com.kunminx.architecture.business.bus.Result;
import com.ljy.devring.DevRing;

import java.io.IOException;
import java.util.List;

import cn.qinguide.f5web.common.constant.KeyConstants;
import cn.qinguide.f5web.common.db.dao.ScriptEntityDao;
import cn.qinguide.f5web.common.db.manger.ScriptEntityManager;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.business.request.IHomeRequest;
import io.reactivex.ObservableEmitter;

public class HomeBusiness extends BaseBusiness<MainBus> implements IHomeRequest {
    @Override
    public void onDestroy() {

    }

    @Override
    public void updateTips() {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                return new Result(KeyConstants.UPDATE_TIPS, null);
            }
        });
    }

    @Override
    public void updateScript() {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                List<ScriptEntity> scriptEntityList = DevRing.tableManager(ScriptEntity.class).loadAll();
                return new Result(KeyConstants.UPDATE_SCRIPT_LIST, scriptEntityList);
            }
        });
    }

    @Override
    public void getLocalScript() {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                List<ScriptEntity> scriptEntityList = DevRing.<ScriptEntityManager>tableManager(ScriptEntity.class).loadAll();
                return new Result(KeyConstants.LOCAL_SCRIPT_LOAD, scriptEntityList);
            }
        });
    }

    @Override
    public void updateScriptItem(final boolean isSwitch, final Long scriptId) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                List<ScriptEntity> list = DevRing.<ScriptEntityManager>tableManager(ScriptEntity.class).getDao().queryBuilder().where(ScriptEntityDao.Properties.Id.eq(scriptId)).build().list();
                if (list != null && list.size() > 0) {
                    ScriptEntity scriptEntity = list.get(0);
                    scriptEntity.setIsEnabled(isSwitch);
                    DevRing.<ScriptEntityManager>tableManager(ScriptEntity.class).updateOne(scriptEntity);
                }
                return null;
            }
        });
    }
}
