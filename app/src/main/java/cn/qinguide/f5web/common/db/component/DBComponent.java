package cn.qinguide.f5web.common.db.component;

import com.ljy.devring.di.component.RingComponent;

import cn.qinguide.f5web.common.db.GreenDBManager;
import cn.qinguide.f5web.common.db.module.GreenDBModule;
import cn.qinguide.f5web.common.db.scope.DBScope;
import dagger.Component;

@DBScope
@Component(modules = {GreenDBModule.class}, dependencies = RingComponent.class)
public interface DBComponent {

    void inject(GreenDBManager greenDBManager);

}
