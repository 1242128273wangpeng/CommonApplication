package com.yimeng.common.album.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yimeng.common.R;
import com.yimeng.common.album.adapter.AlbumFolderAdapter;
import com.yimeng.common.album.bean.AlbumFolderInfo;
import com.yimeng.common.album.ui.activity.AlbumActivity;
import com.yimeng.common.album.view.AlbumView;

import java.io.Serializable;
import java.util.List;

/**
 * 相册目录页面
 *
 * @author Clock
 * @since 2016-01-17
 */
public class AlbumFolderFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String ARG_PARAM1 = "param1";

    private AlbumView mAlbumView;
    /**
     * 相册目录列表
     */
    private List<AlbumFolderInfo> mAlbumFolderInfoList;
    private ListView mFolderListView;

    public AlbumFolderFragment() {
        // Required empty public constructor
    }

    /**
     * @param albumFolderInfoList 相册目录列表
     * @return
     */
    public static AlbumFolderFragment newInstance(List<AlbumFolderInfo> albumFolderInfoList) {
        AlbumFolderFragment fragment = new AlbumFolderFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) albumFolderInfoList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAlbumFolderInfoList = (List<AlbumFolderInfo>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_album_directory, container, false);
        mFolderListView = (ListView) rootView.findViewById(R.id.list_album);
//        ImageLoaderWrapper loaderWrapper = ImageLoaderFactory.getLoader();
        AlbumFolderAdapter albumFolderAdapter = new AlbumFolderAdapter(mAlbumFolderInfoList);
        mFolderListView.setAdapter(albumFolderAdapter);
        mFolderListView.setOnItemClickListener(this);
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AlbumView) {
            mAlbumView = (AlbumView) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAlbumView = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mFolderListView) {
            if (mAlbumView != null) {
                AlbumFolderInfo albumFolderInfo = mAlbumFolderInfoList.get(position);
                mAlbumView.switchAlbumFolder(albumFolderInfo);
            }
        }
    }

}
