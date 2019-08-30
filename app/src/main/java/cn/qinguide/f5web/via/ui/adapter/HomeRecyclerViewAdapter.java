package cn.qinguide.f5web.via.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.via.repertory.proxy.HomeProxy;
import cn.qinguide.f5web.via.ui.adapter.viewholder.HomeViewHolder;

public class HomeRecyclerViewAdapter extends BaseQuickAdapter<ScriptEntity, HomeViewHolder> {

    private HomeProxy homeProxy;

    public HomeRecyclerViewAdapter(@Nullable List<ScriptEntity> data, HomeProxy homeProxy) {
        super(R.layout.item_script, data);
        this.homeProxy = homeProxy;
    }

    @Override
    protected void convert(HomeViewHolder helper, ScriptEntity item) {
        helper.setBinding(item, homeProxy);
    }

}
