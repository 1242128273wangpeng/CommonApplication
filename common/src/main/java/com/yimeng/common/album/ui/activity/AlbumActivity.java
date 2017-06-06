package com.yimeng.common.album.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mzule.activityrouter.annotation.Router;
import com.github.mzule.activityrouter.router.Routers;
import com.yimeng.common.R;
import com.yimeng.common.album.AlbumConstants;
import com.yimeng.common.album.bean.AlbumFolderInfo;
import com.yimeng.common.album.bean.AlbumViewData;
import com.yimeng.common.album.bean.ImageInfo;
import com.yimeng.common.album.presenter.ImageScannerPresenter;
import com.yimeng.common.album.presenter.ImageScannerPresenterImpl;
import com.yimeng.common.album.ui.fragment.AlbumDetailFragment;
import com.yimeng.common.album.ui.fragment.AlbumFolderFragment;
import com.yimeng.common.album.view.AlbumView;
import com.yimeng.common.album.view.ImageChooseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 系统相册页面
 *
 * @author Clock
 * @since 2016-01-06
 */

@Router(value = "common_albumActivity/:photo_type", intParams = "photo_type")
public class AlbumActivity extends AppCompatActivity implements View.OnClickListener, ImageChooseView, AlbumView {
    private TextView tv_dir_title;
    private final static String TAG = AlbumActivity.class.getSimpleName();
    private final static String FRAGMENT_BACK_STACK = "FragmentBackStack";
    private final static String PACKAGE_URL_SCHEME = "package:";

    /**
     * Android M 的Runtime Permission特性申请权限用的
     */
    private final static int REQUEST_READ_EXTERNAL_STORAGE_CODE = 1;
    /**
     * 相册列表页面
     */
    private AlbumFolderFragment mAlbumFolderFragment;
    /**
     * 相册详情页面
     */
    private HashMap<AlbumFolderInfo, AlbumDetailFragment> mAlbumDetailFragmentMap = new HashMap<>();
    /**
     * 被选中的图片文件列表
     */
    private ArrayList<String> mSelectedImageFileList = new ArrayList<>();

    private ImageScannerPresenter mImageScannerPresenter;
    /**
     * 相册目录信息列表
     */
    private List<AlbumFolderInfo> mAlbumFolderInfoList;
    /**
     * 显示图片目录的名称，选中图片的按钮
     */
    private TextView mTitleView, mSelectedView;
    public static int MAX_SIZE = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        mTitleView = (TextView) findViewById(R.id.tv_dir_title);
        mSelectedView = (TextView) findViewById(R.id.tv_selected_ok);
        tv_dir_title = (TextView) findViewById(R.id.tv_dir_title);
        mSelectedView.setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        mImageScannerPresenter = new ImageScannerPresenterImpl(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, R.string.grant_advice_read_album, Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE_CODE);
        } else {
            mImageScannerPresenter.startScanImage(getApplicationContext(), getSupportLoaderManager());
        }
        tv_dir_title.setText("选择图片");
    }

    /**
     * 显示打开权限提示的对话框
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.help);
        builder.setMessage(R.string.help_content);
        builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlbumActivity.this, R.string.grant_permission_failure, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startSystemSettings();
                finish();
            }
        });
        builder.show();
    }


    /**
     * 启动系统权限设置界面
     */
    private void startSystemSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE_CODE) {
            boolean granted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (granted) {
                Toast.makeText(this, R.string.grant_permission_success, Toast.LENGTH_SHORT).show();
                mImageScannerPresenter.startScanImage(getApplicationContext(), getSupportLoaderManager());
            } else {
                showMissingPermissionDialog();//提示对话框
                //Toast.makeText(this, R.string.grant_permission_failure, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.iv_back) {
            onBackPressed();
            tv_dir_title.setText("选择图片");
        } else if (viewId == R.id.tv_selected_ok) {
            int resultValue = getIntent().getIntExtra(AlbumConstants.PHOTO_TYPE, -1);
            Intent showSelectedIntent = new Intent();
            showSelectedIntent.putStringArrayListExtra(ImageSelectActivity.EXTRA_SELECTED_IMAGE_LIST, mSelectedImageFileList);
            if (resultValue == AlbumConstants.PHOTO_TYPE_MOOD) {
                setResult(AlbumConstants.PHOTO_REQUEST_MANY_GALLERY_MOOD_RESULT, showSelectedIntent);
            } else if (resultValue == AlbumConstants.PHOTO_TYPE_ARTICLE) {
                setResult(AlbumConstants.PHOTO_REQUEST_MANY_GALLERY_ARTICLE_RESULT, showSelectedIntent);
            }
            finish();
        }
    }

    @Override
    public void switchAlbumFolder(AlbumFolderInfo albumFolderInfo) {
        if (albumFolderInfo != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            AlbumDetailFragment albumDetailFragment = mAlbumDetailFragmentMap.get(albumFolderInfo);
            // mSelectedImageFileList.size()
            if (albumDetailFragment == null) {
                List<ImageInfo> imageInfoList = albumFolderInfo.getImageInfoList();
                albumDetailFragment = AlbumDetailFragment.newInstance(imageInfoList);
                mAlbumDetailFragmentMap.put(albumFolderInfo, albumDetailFragment);
            }
            albumDetailFragment.setCount(mSelectedImageFileList.size());
            fragmentTransaction.replace(R.id.fragment_container, albumDetailFragment);
            fragmentTransaction.addToBackStack(FRAGMENT_BACK_STACK);
            fragmentTransaction.commit();
            refreshFolderName(albumFolderInfo.getFolderName());
        }
    }

    /**
     * 刷新目录名称
     *
     * @param albumFolderName
     */
    private void refreshFolderName(String albumFolderName) {
        if (!TextUtils.isEmpty(albumFolderName)) {
            mTitleView.setText(albumFolderName);
        }
    }

    /**
     * 切换到相册列表
     */
    private void switchAlbumFolderList() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mAlbumFolderFragment);
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int backStackCount = fragmentManager.getBackStackEntryCount();
                if (backStackCount == 0) {
                    AlbumFolderInfo albumFolderInfo = mAlbumFolderInfoList.get(0);
                    String folderName = albumFolderInfo.getFolderName();
                    refreshFolderName(folderName);
                }
            }
        });
        //fragmentTransaction.commit(); //会产生 java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        fragmentTransaction.commitAllowingStateLoss();//http://stackoverflow.com/questions/25486656/java-lang-illegalstateexceptioncan-not-perform-this-action-after-onsaveinstance
    }


    /**
     * 刷新选中按钮的状态
     */
    private void refreshSelectedViewState() {
        if (mSelectedImageFileList.size() == 0) {
            mSelectedView.setVisibility(View.GONE);
        } else {
            String selectedStringFormat = getString(R.string.selected_ok);
            int selectedSize = mSelectedImageFileList.size();
            String selectedString = String.format(selectedStringFormat, selectedSize, MAX_SIZE);
            mSelectedView.setText(selectedString);
            mSelectedView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void refreshAlbumData(AlbumViewData albumData) {
        if (albumData != null) {
            mAlbumFolderInfoList = albumData.getAlbumFolderInfoList();
            mAlbumFolderFragment = AlbumFolderFragment.newInstance(mAlbumFolderInfoList);
            switchAlbumFolderList();
            findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);//显示相册列表区域
        } else {
            findViewById(R.id.fragment_container).setVisibility(View.GONE);//隐藏显示相册列表的区域
            findViewById(R.id.tv_no_image).setVisibility(View.VISIBLE);//显示没有相片的提示
        }
    }

    @Override
    public void refreshSelectedCounter(ImageInfo imageInfo) {
        if (imageInfo != null) {
            boolean isSelected = imageInfo.isSelected();
            String path = imageInfo.getImageFilepath();
            if (isSelected) {//选中
                if (!mSelectedImageFileList.contains(path)) {
                    mSelectedImageFileList.add(path);
                }
            } else {//取消选中
                if (mSelectedImageFileList.contains(path)) {
                    mSelectedImageFileList.remove(path);
                }
            }
            refreshSelectedViewState();
        }
        Log.i("mSelectedImageFileList", "" + mSelectedImageFileList.size());
    }

}
