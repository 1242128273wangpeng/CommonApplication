package com.yimeng.common.album.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yimeng.common.R;
import com.yimeng.common.album.bean.ImageInfo;
import com.yimeng.common.album.view.ImageChooseView;
import com.yimeng.common.utils.DisplayUtil;

import java.util.List;
import java.util.Stack;

/**
 * 相册视图适配器
 * Created by Clock on 2016/1/16.
 */
public class AlbumGridAdapter extends BaseAdapter {

    private List<ImageInfo> mImageInfoList;
    private Stack<ImageInfo> mImageSaveList;
    private View.OnClickListener mImageItemClickListener;
    private CompoundButton.OnCheckedChangeListener mImageOnSelectedListener;
    private OnClickPreviewImageListener mOnClickPreviewImageListener;
    private ImageChooseView mImageChooseView;
    private int onlyChoose;
    private int nowChoose;
    private boolean isCompleted;

    public AlbumGridAdapter(List<ImageInfo> imageInfoList,
                            ImageChooseView imageChooseView,
                            OnClickPreviewImageListener onClickPreviewImageListener, int onlyChoose, int nowChoose) {
        this.mImageInfoList = imageInfoList;
        this.mImageChooseView = imageChooseView;
        this.mOnClickPreviewImageListener = onClickPreviewImageListener;
        this.onlyChoose = onlyChoose;
        this.nowChoose = nowChoose;
        mImageSaveList = new Stack<>();
    }

    @Override
    public int getCount() {
        if (mImageInfoList == null) {
            return 0;
        }
        return mImageInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mImageInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        AlbumViewHolder holder = null;
        if (convertView == null) {
            holder = new AlbumViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.album_grid_item, null);
            int gridItemSpacing = (int) DisplayUtil.dp2px(parent.getContext(), 2);
            int gridEdgeLength = (DisplayUtil.getScreenWidth(parent.getContext()) - gridItemSpacing * 2) / 3;
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(gridEdgeLength, gridEdgeLength);
            convertView.setLayoutParams(layoutParams);
            holder.albumItem = (ImageView) convertView.findViewById(R.id.iv_album_item);
            holder.imageSelectedCheckBox = (CheckBox) convertView.findViewById(R.id.ckb_image_select);
            convertView.setTag(R.layout.album_grid_item, holder);
        } else {
            holder = (AlbumViewHolder) convertView.getTag(R.layout.album_grid_item);
            resetConvertView(holder);
        }
        ImageInfo imageInfo = mImageInfoList.get(position);
        if (isCompleted) {
            if (mImageSaveList.contains(imageInfo) && imageInfo.isSelected()) {
                holder.imageSelectedCheckBox.setChecked(true);
            } else {
                holder.imageSelectedCheckBox.setChecked(false);
            }
        } else {
            if (imageInfo.isSelected()) {
                mImageSaveList.push(imageInfo);
            }
            holder.imageSelectedCheckBox.setChecked(imageInfo.isSelected());
        }
        if (mImageOnSelectedListener == null) {
            mImageOnSelectedListener = new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ImageInfo imageInfo = (ImageInfo) buttonView.getTag();
                    if (isChecked) {
                        nowChoose++;
                        imageInfo.setIsSelected(isChecked);
                        mImageSaveList.push(imageInfo);
                        if (nowChoose > onlyChoose) {
                            isCompleted = true;
                            int temp = mImageSaveList.size();
                            for (int i = 0; i < temp - 9; i++) {
                                mImageSaveList.removeElement(imageInfo);
                            }
                            nowChoose = 9;
                            imageInfo.setIsSelected(false);
                            notifyDataSetChanged();
                            Toast.makeText(parent.getContext(), "最多勾选9张", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        nowChoose--;
                        imageInfo.setIsSelected(isChecked);
                        mImageSaveList.removeElement(imageInfo);
                    }
                    if (mImageChooseView != null) {
                        mImageChooseView.refreshSelectedCounter(imageInfo);
                    }
                    Log.i("mSelectedImage-co", nowChoose + "");
                }
            };
        }
        holder.imageSelectedCheckBox.setTag(imageInfo);
        holder.imageSelectedCheckBox.setOnCheckedChangeListener(mImageOnSelectedListener);//监听图片是否被选中的状态
        if (mImageItemClickListener == null) {
            mImageItemClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageInfo imageInfo = (ImageInfo) v.getTag(R.id.iv_album_item);
                    if (mOnClickPreviewImageListener != null) {
                        mOnClickPreviewImageListener.onClickPreview(imageInfo);
                    }
                }
            };
        }
        holder.albumItem.setTag(R.id.iv_album_item, imageInfo);
        holder.albumItem.setOnClickListener(mImageItemClickListener);
        Glide.with(convertView.getContext()).load(imageInfo.getImageFile()).into(holder.albumItem);
        return convertView;
    }


    /**
     * 重置缓存视图的初始状态
     *
     * @param viewHolder
     */
    private void resetConvertView(AlbumViewHolder viewHolder) {
        if (viewHolder.imageSelectedCheckBox != null) {
            viewHolder.imageSelectedCheckBox.setOnCheckedChangeListener(null);//先取消选择状态的监听
            viewHolder.imageSelectedCheckBox.setChecked(false);
        }
    }

    private static class AlbumViewHolder {
        /**
         * 显示图片的位置
         */
        ImageView albumItem;
        /**
         * 图片选择按钮
         */
        CheckBox imageSelectedCheckBox;
    }


    /**
     * 点击预览图片操作监听借口
     */
    public static interface OnClickPreviewImageListener {
        /**
         * 当想点击某张图片进行预览的时候触发此函数
         *
         * @param imageInfo
         */
        public void onClickPreview(ImageInfo imageInfo);
    }
}
