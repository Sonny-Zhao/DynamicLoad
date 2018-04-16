package com.tianyu.dynamicloadlib.listener;

/**
 * @author shisheng.zhao
 * @Description: 下载插件状态Listener
 * @date 2018-04-13 下午15:56
 */
public interface DownLoadListener {
    void onSuccess();
    void onFail(String errMsg);
    void onProgress(int process);
}
