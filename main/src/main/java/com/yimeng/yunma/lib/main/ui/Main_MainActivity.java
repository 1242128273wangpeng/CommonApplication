package com.yimeng.yunma.lib.main.ui;

import android.app.Activity;
import android.os.Bundle;

import com.github.mzule.activityrouter.annotation.Router;
import com.yimeng.yunma.lib.main.R;

@Router(value = "main_mainActivity")
public class Main_MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
    }
}
