package com.yimeng.yunma.lib.main.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mzule.activityrouter.annotation.Router;
import com.github.mzule.activityrouter.router.Routers;
import com.yimeng.yunma.lib.main.R;
import com.yimeng.yunma.lib.main.R2;
import com.yimeng.yunma.lib.main.adapter.UltraPagerAdapter;
import com.yimeng.common.base.BaseActivity;
import com.yimeng.common.ultraviewpager.UltraViewPager;
import com.yimeng.common.ultraviewpager.transformer.UltraScaleTransformer;
import com.yimeng.common.utils.ToastUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * BaseActivity的使用
 */
@Router(value = "main_mainActivity2")
public class Main_MainActivity2 extends BaseActivity {
    @BindView(R2.id.but)
    Button but;
    @BindView(R2.id.toRouterHttp)
    Button toRouterHttp;
    @BindView(R2.id.toTestActivity)
    Button toTestActivity;
    @BindView(R2.id.toRouterShopActivity)
    Button toRouterShopActivity;
    @BindView(R2.id.toRouterSignActivity)
    Button toRouterSignActivity;
    @BindView(R2.id.toRouterCommunityActivity)
    Button toRouterCommunityActivity;
    @BindView(R2.id.ultra_viewpager)
    UltraViewPager ultraViewPager;
    private PagerAdapter adapter;

    @Override
    protected void initData() {
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        List<String> mList = new ArrayList<String>();
        mList.add("http://img4.imgtn.bdimg.com/it/u=64893803,2425314413&fm=23&gp=0.jpg");
        mList.add("http://img4.imgtn.bdimg.com/it/u=1039121132,2413990628&fm=23&gp=0.jpg");
        mList.add("http://img0.imgtn.bdimg.com/it/u=4003739204,1776157220&fm=23&gp=0.jpg");
        adapter = new UltraPagerAdapter(this, mList);
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.setMultiScreen(0.6f);
        ultraViewPager.setMaxHeight(500);
        ultraViewPager.setPageTransformer(false, new UltraScaleTransformer());
        //initialize built-in indicator
        ultraViewPager.initIndicator();
        //set style of indicators
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        //set the alignment
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        ultraViewPager.getIndicator().setMargin(0, 0, 0, 15);
        //construct built-in indicator, and add it to  UltraViewPager
        ultraViewPager.getIndicator().build();
        //set an infinite loop
        ultraViewPager.setInfiniteLoop(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity_main2;
    }

    @Override
    protected String[] getNeedPermissions() {
        return new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE};
    }

    @Override
    protected void permissionGrantedSuccess() {
        ToastUtils.showToast("权限申请成功");
    }

    @Override
    protected void permissionGrantedFail() {
        ToastUtils.showToast("权限申请失败");
    }

    @OnClick({R2.id.but, R2.id.toRouterHttp, R2.id.toTestActivity, R2.id.toRouterShopActivity, R2.id.toRouterSignActivity, R2.id.toRouterCommunityActivity})
    public void OnClick(View view) {
        int id = view.getId();
        if (id == R.id.toRouterHttp) {
            Routers.open(Main_MainActivity2.this, "http://mzule.com/download");
        } else if (id == R.id.but) {
            getContent("http://news.baidu.com/internet/");
        } else if (id == R.id.toTestActivity) {
            startActivity(new Intent(Main_MainActivity2.this, TestActivity.class));
        } else if (id == R.id.toRouterShopActivity) {
            Routers.open(Main_MainActivity2.this, "module://shop_mainActivity?color=0xff876877&id=123126");  // 在发送方定义参数，所有的参数默认为String类型
        } else if (id == R.id.toRouterSignActivity) {
            Routers.open(Main_MainActivity2.this, "module://sign_mainActivity/asdasd");  // 在接收方定义参数名称，和参数类型
        } else if (id == R.id.toRouterCommunityActivity) {
            Routers.open(Main_MainActivity2.this, "module://community_mainActivity/社区/1");  // 在发送方定义参数，所有的参数默认为String类型
        }
    }

    public void getContent(String surl) {
        AsyncTask asyncTask = new AsyncTask<String, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {
                StringBuilder stringBuilder = null;
                try {
                    String u = strings[0];
                    URL url = new URL(u);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(false);
                    connection.setRequestProperty("Accept-Charset", "utf-8");
                    connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8\n");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0)");
                    connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
                    connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                    // 其中的memberName和password可通过fiddler来抓取
                    out.write("username=admin&password=123456");
                    out.flush();
                    out.close();
                    connection.connect();
                    InputStream in = connection.getInputStream();
                    StringBuilder retStr = new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String temp = br.readLine();
                    while (temp != null) {
                        retStr.append(temp);
                        temp = br.readLine();
                    }
                    br.close();
                    in.close();
                    stringBuilder = new StringBuilder();
                    for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
                        stringBuilder.append(header.getKey() + " " + header.getValue() + "\n");
                    }
                    return stringBuilder.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(Main_MainActivity2.this, s, Toast.LENGTH_SHORT).show();
            }
        };
        Object[] arg = new String[]{surl};
        asyncTask.execute(arg);
    }

}
