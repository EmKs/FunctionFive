package cn.qinguide.f5web.via.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.via.ui.adapter.viewholder.DownloadViewHolder;

public class DownloadRecyclerViewAdapter extends BaseQuickAdapter<ScriptEntity, DownloadViewHolder> {

    public DownloadRecyclerViewAdapter(@Nullable List<ScriptEntity> data) {
        super(R.layout.item_download, data);
    }

    @Override
    protected void convert(DownloadViewHolder helper, ScriptEntity item) {
        helper.setBinding(item);
        helper.addOnClickListener(R.id.imageView_download);
    }
}
