package cn.qinguide.f5web.xposed.helper;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import cn.qinguide.f5web.common.constant.KeyConstants;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedHelper {

    private Context context;
    private static int appPlan = -1;
    private List<String> scriptList;
    private static boolean isActive = false;
    private XC_LoadPackage.LoadPackageParam loadPackageParam;

    private static final String APP_TABLE_NAME = "/app";
    private static final String SCRIPT_TABLE_NAME = "/script";
    private static final String XPOSED_TABLE_NAME = "/xposed";
    private static final String CONTENT_HEADER = "content://";
    private static final String CONTENT_PROVIDER = "cn.qinguide.f5web.xposed.helper.fivecontentprovider";

    public XposedHelper(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        this.loadPackageParam = loadPackageParam;
        hookContext();
    }

    public void checkActive() {
        if (loadPackageParam.packageName.equals(KeyConstants.APP_PACKAGE))
            XposedHelpers.findAndHookMethod(KeyConstants.APP_ACTIVE_UTIL_CLASS, loadPackageParam.classLoader,
                    KeyConstants.APP_IS_MODULE_ACTIVE_METHOD, XC_MethodReplacement.returnConstant(true));
    }

    private void hookContext() {
        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                context = (Context) param.thisObject;
                queryXposed();
                if (isActive) queryAppPlan(loadPackageParam.packageName);
                if (appPlan != -1) queryAllScript();
            }
        });
    }

    private void queryAllScript() {
        if (checkSQL()) {
            Uri uri = Uri.parse(CONTENT_HEADER + CONTENT_PROVIDER + SCRIPT_TABLE_NAME);
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor == null) return;
            if (appPlan == -1) return;
            if (scriptList == null) scriptList = new ArrayList<>();
            scriptList.clear();
            while (cursor.moveToNext())
                scriptList.add(cursor.getString(cursor.getColumnIndex("SCRIPT")));
            cursor.close();
        }

    }

    private void queryAppPlan(String packageName) {
        if (checkSQL()) {
            Uri uri = Uri.parse(CONTENT_HEADER + CONTENT_PROVIDER + APP_TABLE_NAME);
            Cursor cursor = context.getContentResolver().query(uri, null, packageName, null, null);
            if (cursor == null) return;
            while (cursor.moveToNext())
                if (packageName.equals(cursor.getString(cursor.getColumnIndex("PACKAGE_NAME"))))
                    appPlan = cursor.getInt(cursor.getInt(cursor.getColumnIndex("APP_PLAN")));
            cursor.close();
        }
    }

    private void queryXposed() {
        if (checkSQL()) {
            Uri uri = Uri.parse(CONTENT_HEADER + CONTENT_PROVIDER + XPOSED_TABLE_NAME);
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor == null) return;
            while (cursor.moveToNext())
                isActive = cursor.getLong(cursor.getColumnIndex("ENABLED")) > 0;
            cursor.close();
        }
    }

    private boolean checkSQL() {
        if (context == null || context.getContentResolver() == null) {
            XposedBridge.log(loadPackageParam.packageName + "无法读取数据库");
            return false;
        }
        return true;
    }

    public List<String> getScriptList() {
        return scriptList;
    }

    public int getAppPlan() {
        return appPlan;
    }

    public boolean isActive() {
        return isActive;
    }

}
