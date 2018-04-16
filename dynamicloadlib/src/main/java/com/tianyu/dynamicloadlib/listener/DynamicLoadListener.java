package com.tianyu.dynamicloadlib.listener;

/**
 * @author shisheng.zhao
 * @Description: 插件初始化状态Listener
 * @date 2018-04-13 下午14:53
 */
public interface DynamicLoadListener {
    void onLoadStart();
    void onLoadSuccess();
    void onLoadFail(String errMsg);
    void onLoadProgress(int process);
}
