package cn.qinguide.f5web.via.business.request;

import android.content.Context;

import com.kunminx.architecture.business.bus.IRequest;

public interface IInjectRequest extends IRequest {

    void getAppInfos(Context context);

    void searchAppInfos(String appName);

    void saveAppPlan(String packageName, int planId);

    void removeAppPlan(String packageName);

}
