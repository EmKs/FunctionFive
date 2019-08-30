package cn.qinguide.f5web.common.service;

import cn.qinguide.f5web.common.constant.PathConstants;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.common.entity.base.Page;
import cn.qinguide.f5web.common.entity.base.Response;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FiveApiService {

    @GET(PathConstants.GET_SCRIPT_LIST_URL)
    Observable<Response<Page<ScriptEntity>>> getScriptList(@Query("page") int page, @Query("size") int size);

}
