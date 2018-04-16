package com.tianyu.dynamicloadplu;

import android.content.Context;
import android.util.Log;
import com.tianyu.dynamicloadlib.interfaces.IDynamicLoad;

/**
 * @author shisheng.zhao
 * @Description: 插件模板
 * @date 2018-04-13 下午16:31
 */
public class Template implements IDynamicLoad {
    private String TAG = Template.class.getName();

    @Override
    public Object getTemplate(Context context) {
        Log.e(TAG, "get template success!!!!");
        return this;
    }

    @Override
    public int getTemplateType() {
        return 1;
    }
}
