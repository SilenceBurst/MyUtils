package com.sign.myutils;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

/**
 * 查看大图
 */
public class ImagePagerActivity extends AppCompatActivity {
    //图片链接
    private final static String BASE_IMAGE = "";
    private Context mContext;
    private String[] imageArr;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mContext = this;
        imageArr = getIntent().getStringArrayExtra("imageArr");
        position = getIntent().getIntExtra("position", -1);
        ViewPager viewPager = new ViewPager(this);
        setContentView(viewPager);
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter();
        viewPager.setAdapter(imagePagerAdapter);
        viewPager.setOffscreenPageLimit(imageArr.length);
        if (position > 0 && position < imageArr.length) {
            viewPager.setCurrentItem(position);
        }
    }

    class ImagePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageArr == null ? 0 : imageArr.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayout.setBackgroundColor(Color.BLACK);
            linearLayout.setGravity(Gravity.CENTER_VERTICAL);
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ScreenSizeUtils.getInstance(mContext).dip2px(500)));
            Glide.with(mContext).load(BASE_IMAGE + imageArr[position])
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //点击退出
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImagePagerActivity.this.finish();
                }
            });
            linearLayout.addView(imageView);
            container.addView(linearLayout);
            return linearLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
