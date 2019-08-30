package cn.qinguide.f5web.via.ui.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.entity.AppInfoEntity;
import cn.qinguide.f5web.databinding.ItemInjectBinding;
import cn.qinguide.f5web.via.repertory.proxy.InjectProxy;

public class InjectViewHolder extends BaseViewHolder {

    private ItemInjectBinding binding;

    public InjectViewHolder(View view) {
        super(view);
    }

    public void setBinding(AppInfoEntity appInfoEntity) {
        initBinding();
        binding.setAppInfoEntity(appInfoEntity);
        binding.setInjectProxy(new InjectProxy(itemView.getContext()));
    }

    private void initBinding() {
        if (binding == null)
            binding = DataBindingUtil.bind(itemView);
    }

}
