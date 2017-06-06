package com.yimeng.common.album.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.yimeng.common.R;
import com.yimeng.common.album.adapter.AlbumGridAdapter;
import com.yimeng.common.album.bean.ImageInfo;
import com.yimeng.common.album.ui.activity.ImagePreviewActivity;
import com.yimeng.common.album.view.ImageChooseView;

import java.io.Serializable;
import java.util.List;

/**
 * 相册详情页面
 *
 * @author Clock
 * @since 2016-01-17
 */
public class AlbumDetailFragment extends Fragment implements AlbumGridAdapter.OnClickPreviewImageListener {
    public static final int PREVIEW_REQUEST_CODE = 1000;
    private static final String ARG_PARAM1 = "param1";
    private int ONLY_CHOOSE = 9;
    private int count;
    /**
     * 图片选择View层交互接口
     */
    private ImageChooseView mImageChooseView;
    /**
     * 相册信息列表
     */
    private List<ImageInfo> mImageInfoList;
    /**
     * 相册视图控件
     */
    private GridView mAlbumGridView;
    private AlbumGridAdapter mAlbumGridViewAdapter;

    /**
     * @param imageInfoList 相册列表
     * @return
     */
    public static AlbumDetailFragment newInstance(List<ImageInfo> imageInfoList) {
        AlbumDetailFragment fragment = new AlbumDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) imageInfoList);
        fragment.setArguments(args);
        return fragment;
    }

    public AlbumDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageInfoList = (List<ImageInfo>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_album_detail, container, false);
        mAlbumGridView = (GridView) rootView.findViewById(R.id.gv_album);
        mAlbumGridViewAdapter = new AlbumGridAdapter(mImageInfoList, mImageChooseView, this, ONLY_CHOOSE,count);
        mAlbumGridView.setAdapter(mAlbumGridViewAdapter);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ImageChooseView) {
            mImageChooseView = (ImageChooseView) context;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mImageChooseView = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PREVIEW_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            List<ImageInfo> newSelectedImageList = (List<ImageInfo>) data.getSerializableExtra(ImagePreviewActivity.EXTRA_NEW_IMAGE_LIST);
            refreshSelectedImage(newSelectedImageList);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public AlbumGridAdapter getmAlbumGridViewAdapter() {
        return mAlbumGridViewAdapter;
    }

    /**
     *
     * 刷新新选中图片的数据
     *
     * @param newSelectedImageList
     */
    private void refreshSelectedImage(List<ImageInfo> newSelectedImageList) {
        int imageSize = newSelectedImageList.size();
        for (int imagePos = 0; imagePos < imageSize; imagePos++) {
            ImageInfo srcImageInfo = newSelectedImageList.get(imagePos);
            ImageInfo destImageInfo = mImageInfoList.get(imagePos);
            destImageInfo.setIsSelected(srcImageInfo.isSelected());//遍历更新选中的状态
            if (mImageChooseView != null) {
                mImageChooseView.refreshSelectedCounter(destImageInfo);  // (判断checked，AlbumActivity集合添加删除mSelectedImageFileList) 刷新选中按钮的状态
            }
        }
        mAlbumGridViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickPreview(ImageInfo imageInfo) {
        Intent previewIntent = new Intent(getContext(), ImagePreviewActivity.class);
        previewIntent.putExtra(ImagePreviewActivity.EXTRA_IMAGE_INFO, (Serializable) imageInfo);
        previewIntent.putExtra(ImagePreviewActivity.EXTRA_IMAGE_INFO_LIST, (Serializable) mImageInfoList);
        startActivityForResult(previewIntent, PREVIEW_REQUEST_CODE);
    }

}
