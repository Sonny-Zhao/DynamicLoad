package com.tianyu.dynamicloadlib;

import android.content.Context;

import com.tianyu.dynamicloadlib.listener.DynamicLoadListener;
import com.tianyu.dynamicloadlib.manager.DynamicLoadManager;
import com.tianyu.dynamicloadlib.proxy.DynamicLoadProxy;

/**
 * @author shisheng.zhao
 * @Description: 动态加载宿主Demo调用入口类
 * @date 2018-04-13 下午14:46
 */
public class DynamicLoad {
    private Context mContext;
    private static DynamicLoad instance;
    private DynamicLoadProxy proxy;

    public DynamicLoad(Context context) {
        this.mContext = context;
    }

    /**
     * 构建DynamicLoad单例
     * @param context
     * @return
     */
    public static DynamicLoad getInstance(Context context) {
        if (null == instance) {
            synchronized (DynamicLoad.class) {
                if (null == instance)
                    instance = new DynamicLoad(context);
            }
        }
        return instance;
    }

    /**
     * 初始化插件和动态代理类
     * @param loadListener
     */
    public void init (DynamicLoadListener loadListener){
        DynamicLoadManager.getInstance(mContext).init(loadListener);
        proxy = new DynamicLoadProxy();
    }

    /**
     * 加载模板, 最终调用的是插件的方法
     */
    public Object getTemplate(){
        return proxy.getTemplate(mContext);
    }
}
