package cn.qinguide.f5web.via.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.entity.AppInfoEntity;
import cn.qinguide.f5web.via.ui.adapter.viewholder.InjectViewHolder;

public class InjectRecyclerViewAdapter extends BaseQuickAdapter<AppInfoEntity, InjectViewHolder> {

    public InjectRecyclerViewAdapter(@Nullable List<AppInfoEntity> data) {
        super(R.layout.item_inject, data);
    }

    @Override
    protected void convert(InjectViewHolder helper, AppInfoEntity item) {
        helper.setBinding(item);
    }

}