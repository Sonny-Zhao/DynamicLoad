package com.tianyu.dynamicloadhost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tianyu.dynamicloadlib.DynamicLoad;
import com.tianyu.dynamicloadlib.interfaces.IDynamicLoad;
import com.tianyu.dynamicloadlib.listener.DynamicLoadListener;

/**
 * @author shisheng.zhao
 * @Description: 动态加载Demo宿主工程---做了很简单的事，初始化动态加载插件，点击Button显示需要加载的Class Name
 * @date 2018-04-13 下午14:35
 */
public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getName();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDynamicLoadLib();
        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDynamicLoad dynamicLoad = (IDynamicLoad) DynamicLoad.getInstance(getApplicationContext()).getTemplate();
                textView.setText("ITemplate type is: " + dynamicLoad.getTemplateType());
            }
        });
    }

    /**
     * 初始化插件
     */
    private void initDynamicLoadLib() {
        DynamicLoad.getInstance(this).init(new DynamicLoadListener() {
            @Override
            public void onLoadStart() {
                Log.e(TAG, "onLoadStart");
            }

            @Override
            public void onLoadSuccess() {
                Log.e(TAG, "onLoadSuccess");
            }

            @Override
            public void onLoadFail(String errMsg) {
                Log.e(TAG, "onLoadFail: " + "Msg: " + errMsg);
            }

            @Override
            public void onLoadProgress(int process) {
                Log.e(TAG, "onLoadProgress: " + process);
            }
        });
    }
}
