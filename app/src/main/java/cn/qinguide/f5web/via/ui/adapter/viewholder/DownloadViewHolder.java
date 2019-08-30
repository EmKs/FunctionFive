package cn.qinguide.f5web.via.ui.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.databinding.ItemDownloadBinding;

public class DownloadViewHolder extends BaseViewHolder {

    private ItemDownloadBinding binding;

    public DownloadViewHolder(View view) {
        super(view);
    }

    public void setBinding(ScriptEntity scriptEntity) {
        initDataBinding();
        binding.setScriptEntity(scriptEntity);
    }

    private void initDataBinding() {
        if (binding == null)
            binding = DataBindingUtil.bind(itemView);
    }

}
