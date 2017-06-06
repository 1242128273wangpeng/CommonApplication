package com.yimeng.common.album.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yimeng.common.R;
import com.yimeng.common.album.bean.AlbumFolderInfo;
import com.yimeng.common.album.bean.ImageInfo;

import java.io.File;
import java.util.List;

/**
 * 相册目录适配器
 * <p/>
 * Created by Clock on 2016/1/17.
 */
public class AlbumFolderAdapter extends BaseAdapter {

    private List<AlbumFolderInfo> mAlbumFolderInfoList;

    public AlbumFolderAdapter(List<AlbumFolderInfo> albumFolderInfoList) {
        this.mAlbumFolderInfoList = albumFolderInfoList;
    }

    @Override
    public int getCount() {
        if (mAlbumFolderInfoList == null) {
            return 0;
        }
        return mAlbumFolderInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAlbumFolderInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.album_directory_item, null);
            holder = new ViewHolder();
            holder.ivAlbumCover = (ImageView) convertView.findViewById(R.id.iv_album_cover);
            holder.tvDirectoryName = (TextView) convertView.findViewById(R.id.tv_directory_name);
            holder.tvChildCount = (TextView) convertView.findViewById(R.id.tv_child_count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        AlbumFolderInfo albumFolderInfo = mAlbumFolderInfoList.get(position);
        File frontCover = albumFolderInfo.getFrontCover();
        Glide.with(convertView.getContext()).load(frontCover).into(holder.ivAlbumCover);
        String folderName = albumFolderInfo.getFolderName();
        holder.tvDirectoryName.setText(folderName);
        List<ImageInfo> imageInfoList = albumFolderInfo.getImageInfoList();
        holder.tvChildCount.setText(imageInfoList.size() + "");
        return convertView;
    }

    private static class ViewHolder {
        ImageView ivAlbumCover;
        TextView tvDirectoryName;
        TextView tvChildCount;
    }
}
