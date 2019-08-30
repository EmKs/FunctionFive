package cn.qinguide.f5web.via.ui.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.databinding.ItemScriptBinding;
import cn.qinguide.f5web.via.repertory.proxy.HomeProxy;

public class HomeViewHolder extends BaseViewHolder {

    private ItemScriptBinding binding;

    public HomeViewHolder(View view) {
        super(view);
    }

    public void setBinding(ScriptEntity scriptEntity, HomeProxy homeProxy) {
        initBinding();
        binding.setHomeProxy(homeProxy);
        binding.setScriptEntity(scriptEntity);
    }

    private void initBinding() {
        if (binding == null)
            binding = DataBindingUtil.bind(itemView);
    }

}
