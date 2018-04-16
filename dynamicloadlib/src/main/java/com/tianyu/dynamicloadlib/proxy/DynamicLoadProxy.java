package com.tianyu.dynamicloadlib.proxy;

import android.content.Context;
import android.util.Log;
import com.tianyu.dynamicloadlib.interfaces.IDynamicLoad;
import com.tianyu.dynamicloadlib.manager.DynamicLoadManager;

/**
 * @author shisheng.zhao
 * @Description: 动态加载插件代理, 代理插件行为, 实际调用插件的方法
 * @date 2018-04-13 下午14:46
 */
public class DynamicLoadProxy implements IDynamicLoad.aa {
    private String TAG = DynamicLoadProxy.class.getName();

//    @Override
//    public Object getTemplate(Context context) {
//        IDynamicLoad instance = (IDynamicLoad) DynamicLoadManager.getInstance(context).newInstance("com.tianyu.dynamicloadplu.Template");
//        if (null == instance) {
//            Log.e(TAG, "插件未加载完成!");
//        }
//        return instance;
//    }
//
//    @Override
//    public int getTemplateType() {
//        return 0;
//    }


    @Override
    public void geta() {

    }
}
