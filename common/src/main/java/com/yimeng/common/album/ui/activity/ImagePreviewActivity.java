package com.yimeng.common.album.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yimeng.common.R;
import com.yimeng.common.album.bean.ImageInfo;
import com.yimeng.common.photoview.PhotoView;
import com.yimeng.common.photoview.PhotoViewAttacher;

import java.io.Serializable;
import java.util.List;


/**
 * 图片预览界面
 *
 * @author Clock
 * @since 2016-01-25
 */
public class ImagePreviewActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private final static String TAG = ImagePreviewActivity.class.getSimpleName();
    public final static String EXTRA_IMAGE_INFO_LIST = "ImageInfoList";
    public final static String EXTRA_IMAGE_INFO = "ImageInfo";
    public final static String EXTRA_CHECK = "show";
    public final static String EXTRA_NEW_IMAGE_LIST = "NewImageList";
    private ViewPager mPreviewViewPager;
    private PagerAdapter mPreviewPagerAdapter;
    private ViewPager.OnPageChangeListener mPreviewChangeListener;
    private TextView mTitleView;
    private CheckBox mImageSelectedBox;
    private View mHeaderView, mFooterView;
    private int imageIndex;
    private ImageView backTv;
    /**
     * 所有图片的列表
     */
    private List<ImageInfo> mPreviewImageInfoList;
    /**
     * 刚进入页面显示的图片
     */
    private ImageInfo mPreviewImageInfo;
    private TextView deletetv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        if (Build.VERSION.SDK_INT >= 11) {
            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if (View.SYSTEM_UI_FLAG_VISIBLE == visibility) {//此处需要添加顶部和底部消失和出现的动画效果
                        Log.i(TAG, "SYSTEM_UI_FLAG_VISIBLE");
                        mHeaderView.startAnimation(AnimationUtils.loadAnimation(ImagePreviewActivity.this, R.anim.top_enter_anim));
                        mFooterView.startAnimation(AnimationUtils.loadAnimation(ImagePreviewActivity.this, R.anim.bottom_enter_anim));

                    } else {
                        Log.i(TAG, "SYSTEM_UI_FLAG_INVISIBLE");
                        mHeaderView.startAnimation(AnimationUtils.loadAnimation(ImagePreviewActivity.this, R.anim.top_exit_anim));
                        mFooterView.startAnimation(AnimationUtils.loadAnimation(ImagePreviewActivity.this, R.anim.bottom_exit_anim));

                    }
                }
            });
        }
        mPreviewImageInfo = (ImageInfo) getIntent().getSerializableExtra(EXTRA_IMAGE_INFO);
        mPreviewImageInfoList = (List<ImageInfo>) getIntent().getSerializableExtra(EXTRA_IMAGE_INFO_LIST);
        initView();

    }

    private void initView() {
        backTv = (ImageView) findViewById(R.id.iv_back);
        deletetv = (TextView) findViewById(R.id.deletetv);
        mTitleView = (TextView) findViewById(R.id.tv_title);
        mImageSelectedBox = (CheckBox) findViewById(R.id.ckb_image_select);
        mHeaderView = findViewById(R.id.header_view);
        mFooterView = findViewById(R.id.footer_view);
        backTv.setOnClickListener(this);
        if (mPreviewImageInfo != null && mPreviewImageInfoList != null) {
            if (mPreviewImageInfoList.contains(mPreviewImageInfo)) {
                imageIndex = mPreviewImageInfoList.indexOf(mPreviewImageInfo);
                setPositionToTitle(imageIndex);
            }
        }
        if ("noshow".equals(getIntent().getStringExtra(EXTRA_CHECK))) {
            deletetv.setVisibility(View.VISIBLE);
            mImageSelectedBox.setVisibility(View.GONE);
            deletetv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPreviewImageInfoList.size() > 1) {
                        mPreviewImageInfoList.remove(mPreviewImageInfoList.get(imageIndex));
                        int initShowPosition = 0;
                        if (imageIndex == 0) {  // 在第一张点删除按钮就会选中删除一个，然后选中集合第一张
                            mPreviewImageInfo = mPreviewImageInfoList.get(0);
                            if (mPreviewImageInfo != null && mPreviewImageInfoList != null && mPreviewImageInfoList.contains(mPreviewImageInfo)) {
                                initShowPosition = mPreviewImageInfoList.indexOf(mPreviewImageInfo);
                            }
                            setPositionToTitle(0);
                            imageIndex = 0;
                        } else if (imageIndex > 0) {
                            mPreviewImageInfo = mPreviewImageInfoList.get(imageIndex - 1);
                            if (mPreviewImageInfo != null && mPreviewImageInfoList != null && mPreviewImageInfoList.contains(mPreviewImageInfo)) {
                                initShowPosition = mPreviewImageInfoList.indexOf(mPreviewImageInfo);
                                setPositionToTitle(initShowPosition);
                            }
                            imageIndex = imageIndex - 1;
                        }
                        mImageSelectedBox.setOnCheckedChangeListener(ImagePreviewActivity.this);
                        mPreviewViewPager = (ViewPager) findViewById(R.id.gallery_viewpager);
                        mPreviewPagerAdapter = new PreviewPagerAdapter();
                        mPreviewViewPager.setAdapter(mPreviewPagerAdapter);
                        mPreviewViewPager.setCurrentItem(initShowPosition);
                        mPreviewChangeListener = new PreviewChangeListener();
                        mPreviewViewPager.addOnPageChangeListener(mPreviewChangeListener);
                    } else if (mPreviewImageInfoList.size() == 1) {
                        mPreviewImageInfoList.remove(mPreviewImageInfo);
                        Intent intent = new Intent();
                        intent.putExtra("deletechangeimgs",  (Serializable)mPreviewImageInfoList);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });
        }
        if (mPreviewImageInfo != null) {
            mImageSelectedBox.setChecked(mPreviewImageInfo.isSelected());
        }
        mImageSelectedBox.setOnCheckedChangeListener(this);
        mPreviewViewPager = (ViewPager) findViewById(R.id.gallery_viewpager);
        mPreviewPagerAdapter = new PreviewPagerAdapter();
        mPreviewViewPager.setAdapter(mPreviewPagerAdapter);
        if (mPreviewImageInfo != null && mPreviewImageInfoList != null && mPreviewImageInfoList.contains(mPreviewImageInfo)) {
            int initShowPosition = mPreviewImageInfoList.indexOf(mPreviewImageInfo);
            mPreviewViewPager.setCurrentItem(initShowPosition);
        }
        mPreviewChangeListener = new PreviewChangeListener();
        mPreviewViewPager.addOnPageChangeListener(mPreviewChangeListener);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_back) {
            if ("noshow".equals(getIntent().getStringExtra(EXTRA_CHECK))) {
                Intent intent = new Intent();
                intent.putExtra("deletechangeimgs", (Serializable) mPreviewImageInfoList);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                onBackPressed();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra(EXTRA_NEW_IMAGE_LIST, (Serializable) mPreviewImageInfoList);
        setResult(Activity.RESULT_OK, data);
        super.onBackPressed();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == mImageSelectedBox) {
            int currentPosition = mPreviewViewPager.getCurrentItem();
            ImageInfo imageInfo = mPreviewImageInfoList.get(currentPosition);
            imageInfo.setIsSelected(isChecked);
        }
    }

    /**
     * 监听PhotoView的点击事件
     */
    private PhotoViewAttacher.OnViewTapListener mOnPreviewTapListener = new PhotoViewAttacher.OnViewTapListener() {
        @Override
        public void onViewTap(View view, float v, float v1) {
//            toggleImmersiveMode();
        }
    };

    /**
     * 相册适配器
     */
    private class PreviewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            if (mPreviewImageInfoList == null) {
                return 0;
            }
            return mPreviewImageInfoList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            PhotoView galleryPhotoView = (PhotoView) view.findViewById(R.id.iv_show_image);
            galleryPhotoView.setScale(1.0f);//让图片在滑动过程中恢复回缩放操作前原图大小
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View galleryItemView = View.inflate(ImagePreviewActivity.this, R.layout.preview_image_item, null);
            ImageInfo imageInfo = mPreviewImageInfoList.get(position);
            PhotoView galleryPhotoView = (PhotoView) galleryItemView.findViewById(R.id.iv_show_image);
            galleryPhotoView.setOnViewTapListener(mOnPreviewTapListener);
            Glide.with(ImagePreviewActivity.this).load( imageInfo.getImageFile()).into(galleryPhotoView);
            container.addView(galleryItemView);
            return galleryItemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    /**
     * 相册详情页面滑动监听
     */
    private class PreviewChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            imageIndex = position;
            mImageSelectedBox.setOnCheckedChangeListener(null);//先反注册监听，避免重复更新选中的状态
            setPositionToTitle(position);
            ImageInfo imageInfo = mPreviewImageInfoList.get(position);
            mImageSelectedBox.setChecked(imageInfo.isSelected());
            mImageSelectedBox.setOnCheckedChangeListener(ImagePreviewActivity.this);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 设置标题现实当前所处的位置
     *
     * @param position
     */
    private void setPositionToTitle(int position) {
        if (mPreviewImageInfoList != null) {
            String title = String.format(getString(R.string.image_index), position + 1, mPreviewImageInfoList.size());
            mTitleView.setText(title);
        }
    }

    /**
     * 切换沉浸栏模式（Immersive - Mode）
     */
    private void toggleImmersiveMode() {
        if (Build.VERSION.SDK_INT >= 11) {
            int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
            // Navigation bar hiding:  Backwards compatible to ICS.
            if (Build.VERSION.SDK_INT >= 14) {
                uiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            // Status bar hiding: Backwards compatible to Jellybean
            if (Build.VERSION.SDK_INT >= 16) {
                uiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
            }
            // Immersive mode: Backward compatible to KitKat.
            if (Build.VERSION.SDK_INT >= 18) {
                uiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        }
    }
}
