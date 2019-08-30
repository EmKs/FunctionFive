package cn.qinguide.f5web.xposed.helper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cn.qinguide.f5web.common.db.dao.AppInfoEntityDao;

/**
 * 注意，这里不能有Xposed的东西
 */
public class FiveContentProvider extends ContentProvider {


    static final int RESOLVER_SCRIPT = 201;
    static final int RESOLVER_APP = 203;
    static final int RESOLVER_XPOSED = 206;
    private static final String APP_TABLE_NAME = "/app";
    private static final String SCRIPT_TABLE_NAME = "/script";
    private static final String XPOSED_TABLE_NAME = "/xposed";
    private static final String CONTENT_PROVIDER = "cn.qinguide.f5web.xposed.helper.fivecontentprovider";

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(CONTENT_PROVIDER, SCRIPT_TABLE_NAME, RESOLVER_SCRIPT);
        matcher.addURI(CONTENT_PROVIDER, APP_TABLE_NAME, RESOLVER_APP);
        matcher.addURI(CONTENT_PROVIDER, XPOSED_TABLE_NAME, RESOLVER_XPOSED);
    }

    public FiveContentProvider() {

    }

    @Override
    public boolean onCreate() {
        GreenDaoManager.getInstance().init(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (matcherUri(uri)) {
            case RESOLVER_SCRIPT:
                return GreenDaoManager.getInstance().getDaoSession().getScriptEntityDao().queryBuilder().buildCursor().query();
            case RESOLVER_APP:
                return GreenDaoManager.getInstance().getDaoSession().getAppInfoEntityDao().queryBuilder().where(AppInfoEntityDao.Properties.Id.eq(selection)).buildCursor().query();
            case RESOLVER_XPOSED:
                return GreenDaoManager.getInstance().getDaoSession().getXposedEntityDao().queryBuilder().buildCursor().query();
            default:
                throw new RuntimeException("查询Uri格式错误");
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    private int matcherUri(Uri uri) {
        return matcher.match(uri);
    }

}
