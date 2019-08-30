package cn.qinguide.f5web.common.utils;

import android.content.pm.PackageManager;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljy.devring.DevRing;
import com.ljy.devring.image.support.GlideApp;

import java.lang.ref.WeakReference;

import cn.qinguide.f5web.R;


public class FiveBindingAdapter {

    @BindingAdapter({"setNetImage"})
    public static void setNetImage(ImageView imageView, String url) {
        WeakReference<ImageView> imageViewWeakReference = new WeakReference<>(imageView);
        if (!TextUtils.isEmpty(url))
            DevRing.imageManager().loadNet(url, imageViewWeakReference.get());
        else setErrorImage(imageViewWeakReference.get());
    }

    @BindingAdapter({"setAppIcon"})
    public static void setAppIcon(ImageView imageView, String packageName) {
        WeakReference<ImageView> imageViewWeakReference = new WeakReference<>(imageView);
        try {
            Drawable drawable = imageView.getContext().getPackageManager().getApplicationIcon(packageName);
            GlideApp.with(imageViewWeakReference.get()).load(drawable).into(imageViewWeakReference.get());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            setErrorImage(imageViewWeakReference.get());
        }
    }

    @BindingAdapter({"formatUpdateTime"})
    public static void formatUpdateTime(View view, String time) {
        ((TextView) view).setText(String.format(view.getResources().getString(R.string.update_time), RelativeDateFormat.getTimeFormatText(time)));
    }

    @BindingAdapter({"formatCreateTime"})
    public static void formatCreateTime(View view, String time) {
        ((TextView) view).setText(String.format(view.getResources().getString(R.string.create_time), RelativeDateFormat.getTimeFormatText(time)));
    }

    private static void setErrorImage(ImageView image) {
        DevRing.imageManager().loadRes(R.drawable.ic_loading_error, image);
    }

}
