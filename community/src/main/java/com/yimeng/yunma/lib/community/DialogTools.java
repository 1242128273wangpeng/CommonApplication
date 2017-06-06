package com.yimeng.yunma.lib.community;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.github.mzule.activityrouter.router.Routers;
import com.yimeng.common.CommonConstants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.yimeng.common.album.AlbumConstants.PHOTO_REQUEST_MANY_GALLERY;
import static com.yimeng.common.album.AlbumConstants.PHOTO_REQUEST_TAKE_PHOTO;
import static com.yimeng.common.album.AlbumConstants.PHOTO_TYPE;
import static com.yimeng.common.album.AlbumConstants.PHOTO_TYPE_ARTICLE;
import static com.yimeng.common.album.AlbumConstants.PHOTO_TYPE_MOOD;

/**
 * Desc :
 * Created by WangPeng on 2017/5/27 0027.
 */

public class DialogTools {

    static File cameraTempFile = null;

    /**
     * @param activity
     */
    public static void showCameraAndChooseManyDialog(final Activity activity, final int type, final int chooseSize) {
        final Dialog dialog;
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.community_photo_choose_dialog, null);
        Button camera = (Button) view.findViewById(R.id.camera);
        Button photo = (Button) view.findViewById(R.id.photo);
        Button call_off = (Button) view.findViewById(R.id.call_off);
        dialog = new Dialog(activity, R.style.community_transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.community_main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = activity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
        call_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.cancel();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {//拍照按钮
            @Override
            public void onClick(View arg0) {
                dialog.cancel();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(activity.getPackageManager()) != null) {
                    cameraTempFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + CommonConstants.IMG + File.separator + DialogTools.getPhotoFileName());
                    cameraTempFile.getParentFile().mkdirs();
                    if(!cameraTempFile.exists()){
                        try {
                            cameraTempFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (cameraTempFile != null && cameraTempFile.exists()) {
                         /*获取当前系统的android版本号*/
                        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                        Log.i("currentApiVersion", "currentApiVersion====>" + currentApiVersion);
                        if (currentApiVersion < 24) {
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraTempFile));
                        } else {
                            ContentValues contentValues = new ContentValues(1);
                            contentValues.put(MediaStore.Images.Media.DATA, cameraTempFile.getAbsolutePath());
                            Uri uri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        }
                        if (type == PHOTO_TYPE_MOOD) {
                            cameraIntent.putExtra(PHOTO_TYPE, PHOTO_TYPE_MOOD);
                        } else if (type == PHOTO_TYPE_ARTICLE) {
                            cameraIntent.putExtra(PHOTO_TYPE, PHOTO_TYPE_ARTICLE);
                        }
                        activity.startActivityForResult(cameraIntent, PHOTO_REQUEST_TAKE_PHOTO);
                    } else {
                        Toast.makeText(activity, R.string.community_mis_error_image_not_exist, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(activity, R.string.community_mis_msg_no_camera, Toast.LENGTH_SHORT).show();
                }
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {//从相册选择按钮
            @Override
            public void onClick(View arg0) {
                dialog.cancel();
                String moodOrArticleString = "";
                if (type == PHOTO_TYPE_MOOD) {
                    moodOrArticleString = "module://common_albumActivity/" + PHOTO_TYPE_MOOD;
                } else if (type == PHOTO_TYPE_ARTICLE) {
                    moodOrArticleString = "module://common_albumActivity/" + PHOTO_TYPE_ARTICLE;
                }
                Routers.openForResult(activity, moodOrArticleString, PHOTO_REQUEST_MANY_GALLERY);
            }
        });
    }

    public static File getCameraTempFile() {
        return cameraTempFile;
    }

    // 使用系统当前日期加以调整作为照片的名称
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
}
