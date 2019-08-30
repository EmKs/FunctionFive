package cn.qinguide.f5web.xposed;

import cn.qinguide.f5web.xposed.helper.XposedHelper;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XposedHelper xposedHelper = new XposedHelper(loadPackageParam);
        xposedHelper.checkActive();
        if (!xposedHelper.isActive()) return;
        XposedBridge.log("Hook当前包名" + loadPackageParam.packageName);
        if (xposedHelper.getAppPlan() == -1) return;
        XposedBridge.log(loadPackageParam.packageName + "未设置方案");
        XposedBridge.log("当前软件设置方案" + xposedHelper.getAppPlan());
    }

}
