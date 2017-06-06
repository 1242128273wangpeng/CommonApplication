package com.yimeng.yunma.lib.community;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mzule.activityrouter.annotation.Router;
import com.yimeng.common.album.AlbumConstants;
import com.yimeng.common.album.ui.activity.ImageSelectActivity;
import com.yimeng.common.base.BaseActivity;
import com.yimeng.common.downloader.model.FileUtils;
import com.yimeng.common.luban.Luban;
import com.yimeng.common.luban.OnCompressListener;
import com.yimeng.common.net.AppClient;
import com.yimeng.common.richeditor.EditItem;
import com.yimeng.common.richeditor.XCRichEditor;
import com.yimeng.common.utils.ToastUtils;
import com.yimeng.common.widget.CommonTitle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Router(value = "community_mainActivity/:title/:id", stringParams = "title", intParams = "id")
public class Community_MainActivity extends BaseActivity implements CommonTitle.onRightAddClickListener {
    @BindView(R2.id.publish)
    Button publish;
    @BindView(R2.id.xCRichEditor)
    XCRichEditor xcRichEditor;
    private String title;
    private int id;
    private ArrayList<String> mSelectedImageFileList; // 存选中文件的路径的。
    private HashMap<String, RequestBody> paramsMap;
    private MultipartBody.Part[] uploadFileBody;
    private List<MultipartBody.Part> mFileParts = new ArrayList<>(); // 存上传文件的参数的
    private Subscription subscription;

    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
        id = getIntent().getIntExtra("id", 0);
        CommonTitle.getInstance().setTitle(this, title, PGApp, true);
        CommonTitle.getInstance().setOnRightAddClickListener(this, this);
    }

    public void ShowDialog(View view) {
        DialogTools.showCameraAndChooseManyDialog(this, AlbumConstants.PHOTO_TYPE_MOOD, 1);
    }

    @Override
    public int getLayoutId() {
        return R.layout.community_activity_main;
    }

    @Override
    public void rightAddClick() {
        ToastUtils.showToast("添加成功title:" + title + " id:" + id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<File> selectFiles = new ArrayList<>();
        switch (requestCode) {
            case AlbumConstants.PHOTO_REQUEST_TAKE_PHOTO:// 当选择拍照时调用
                Log.i("REQUEST_TAKE_PHOTO", "调用了拍照-->" + DialogTools.getCameraTempFile().getAbsolutePath());
                String cameraPath = DialogTools.getCameraTempFile().getAbsolutePath();
                mSelectedImageFileList = new ArrayList<>();
                mSelectedImageFileList.clear();
                mSelectedImageFileList.add(cameraPath);
                uploadFileBody = new MultipartBody.Part[mSelectedImageFileList.size()];
                if (FileUtils.isFileExists(cameraPath)) {
                    for (int i = 0; i < mSelectedImageFileList.size(); i++) {
                        selectFiles.add(FileUtils.getFileByPath(mSelectedImageFileList.get(i)));
                    }
                    compressWithRx(selectFiles);
                }
                break;
            case AlbumConstants.PHOTO_REQUEST_MANY_GALLERY:
                if (data != null) {
                    if (mSelectedImageFileList != null) {
                        mSelectedImageFileList.clear();
                    }
                    mSelectedImageFileList = data.getStringArrayListExtra(ImageSelectActivity.EXTRA_SELECTED_IMAGE_LIST);
                    for (int i = 0; i < mSelectedImageFileList.size(); i++) {
                        Log.i("mOnActivityResult", mSelectedImageFileList.get(i).toString());
                    }
                    if (resultCode == AlbumConstants.PHOTO_REQUEST_MANY_GALLERY_ARTICLE_RESULT) {
                        Log.i("PHOTO_ARTICLE_RESULT", mSelectedImageFileList.size() + "");
                    } else if (resultCode == AlbumConstants.PHOTO_REQUEST_MANY_GALLERY_MOOD_RESULT) {
                        Log.i("PHOTO_MOOD_RESULT", mSelectedImageFileList.size() + "");
                    }
                    uploadFileBody = new MultipartBody.Part[mSelectedImageFileList.size()];
                    for (int i = 0; i < mSelectedImageFileList.size(); i++) {
                        selectFiles.add(FileUtils.getFileByPath(mSelectedImageFileList.get(i)));
                    }
                    List<EditItem> items = new ArrayList<>();
                    for (int i = 0; i < selectFiles.size(); i++) {
                        EditItem item = new EditItem();
                        item.setUri(Uri.fromFile(selectFiles.get(i)));
                        item.setType(1);
                        item.setContent(selectFiles.get(i).getAbsolutePath());
                        items.add(item);
                    }
                    xcRichEditor.addImage(items);
                    compressWithRx(selectFiles);
                }
                break;
        }
    }

    @OnClick({R2.id.publish})
    public void publish(View view) {
        RequestBody userid = AppClient.createPartFromString("296");
        RequestBody postContent = AppClient.createPartFromString("Hi");
        RequestBody postTitle = AppClient.createPartFromString("");
        RequestBody circleTypeid = AppClient.createPartFromString("12");
        RequestBody posttype = AppClient.createPartFromString("1");
        paramsMap = new HashMap<>();
        paramsMap.put("userid", userid);
        paramsMap.put("postContent", postContent);
        paramsMap.put("postTitle", postTitle);
        paramsMap.put("circleTypeid", circleTypeid);
        paramsMap.put("posttype", posttype);
        for (int i = 0; i < mFileParts.size(); i++) {
            uploadFileBody[i] = mFileParts.get(i);
        }
        if (view.getId() == R.id.publish) {
            AppClient.getInstance(ApiCommunity.class).getPublishByCall(paramsMap, uploadFileBody).enqueue(new Callback<PublishBean>() {
                @Override
                public void onResponse(Call<PublishBean> call, Response<PublishBean> response) {
                    if (response.isSuccessful()) {
                        PublishBean publishBean = response.body();
                        Log.i("==publishBean", publishBean.toString());
                    }
                }

                @Override
                public void onFailure(Call<PublishBean> call, Throwable t) {
                    Log.i("==error", t.getMessage() + "   cause-->" + t.toString());
                }
            });
        }
    }

    /**
     * 压缩单张图片 Listener 方式
     */
    private synchronized void compressWithLs(File file, final int index) {
        Luban.get(this)
                .load(file)
                .putGear(Luban.THIRD_GEAR)
                .setFilename(System.currentTimeMillis() + "")
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.i("Luban--", "onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.i("Luban--", "index" + index + "  onSuccess  " + file.getAbsolutePath() + " " + file.length());
                        uploadFileBody[index] = AppClient.prepareFilePathPart("imagelist", file.getAbsolutePath().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Luban--", "onError");
                    }
                }).launch();
    }

    /**
     * 压缩单张图片 RxJava 方式
     */
    private void compressWithRx(List<File> fileLists) {
        subscription = Luban.get(this)
                .loadList(fileLists)
                .putGear(Luban.THIRD_GEAR)
                .asListObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends File>>() {
                    @Override
                    public Observable<? extends File> call(Throwable throwable) {
                        return Observable.empty();
                    }
                })
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        Log.i("LubanRx--", "  onSuccess  " + file.getAbsolutePath() + " " + file.length());
                        mFileParts.add(AppClient.prepareFilePart("imagelist", file));
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }
}
