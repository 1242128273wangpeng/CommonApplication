package com.yimeng.common.album.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yimeng.common.R;
import com.yimeng.common.utils.DisplayUtil;

import java.io.File;
import java.util.List;

/**
 * 显示选中图片的界面
 *
 * @author Clock
 * @since 2016-01-26
 */
public class ImageSelectActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String EXTRA_SELECTED_IMAGE_LIST = "selectImage";
    private GridView mSelectedImageGridView;
    private List<File> mSelectedImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select);
        findViewById(R.id.iv_back).setOnClickListener(this);
        mSelectedImageList = (List<File>) getIntent().getSerializableExtra(EXTRA_SELECTED_IMAGE_LIST);
        mSelectedImageGridView = (GridView) findViewById(R.id.gv_image_selected);
        mSelectedImageGridView.setAdapter(new SelectedImageGridAdapter());
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_back) {
            onBackPressed();
        }
    }

    private class SelectedImageGridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mSelectedImageList == null) {
                return 0;
            }
            return mSelectedImageList.size();
        }

        @Override
        public Object getItem(int position) {
            return mSelectedImageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SelectedImageHolder holder = null;
            if (convertView == null) {
                holder = new SelectedImageHolder();
                convertView = View.inflate(parent.getContext(), R.layout.selected_image_item, null);
                int gridItemSpacing = (int) DisplayUtil.dp2px(parent.getContext(), 2);
                int gridEdgeLength = (DisplayUtil.getScreenWidth(parent.getContext()) - gridItemSpacing * 2) / 3;
                AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(gridEdgeLength, gridEdgeLength);
                convertView.setLayoutParams(layoutParams);
                holder.selectedImageView = (ImageView) convertView.findViewById(R.id.iv_selected_item);
                convertView.setTag(holder);
            } else {
                holder = (SelectedImageHolder) convertView.getTag();
            }
            Glide.with(ImageSelectActivity.this).load( mSelectedImageList.get(position)).into(holder.selectedImageView);
            return convertView;
        }
    }

    private static class SelectedImageHolder {
        ImageView selectedImageView;
    }
}
