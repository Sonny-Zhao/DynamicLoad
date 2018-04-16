package com.tianyu.dynamicloadlib.manager;

import android.content.Context;
import android.util.Log;
import com.tianyu.dynamicloadlib.constans.DConstans;
import com.tianyu.dynamicloadlib.listener.DownLoadListener;
import com.tianyu.dynamicloadlib.listener.DynamicLoadListener;
import com.tianyu.dynamicloadlib.utils.DownloadUtils;
import java.io.File;
import dalvik.system.DexClassLoader;

/**
 * @author shisheng.zhao
 * @Description: 插件管理类, 负责插件的初始化, 加载, 下载, 升级等操作
 * @date 2018-04-13 下午15:12
 */
public class DynamicLoadManager {
    private String TAG = DynamicLoadManager.class.getName();
    private Context mContext;
    private static DynamicLoadManager instance;
    private DynamicLoadListener listener;
    private String filePath;
    private DexClassLoader dexClassLoader;

    private DynamicLoadManager(Context context) {
        mContext = context;
        filePath = context.getFilesDir().getAbsolutePath();
    }

    /**
     * 构建DynamicLoadManager单例
     * @param context
     * @return
     */
    public static DynamicLoadManager getInstance(Context context) {
        if (null == instance) {
            synchronized (DynamicLoadManager.class) {
                if (null == instance) {
                    instance = new DynamicLoadManager(context);
                }
            }
        }
        return instance;
    }

    /**
     * 初始化插件
     * @param loadListener
     */
    public void init(DynamicLoadListener loadListener) {
        this.listener = loadListener;
        if (null != listener) {
            listener.onLoadStart();
        }
        // 检查插件是否存在
        File file = new File(filePath + File.separator + DConstans.jarName);
        if (file.exists()) {
            // 存在, 加载插件
            loadDynamicLoadPlugin();
        } else {
            // 不存在, 通过Server下载到本地或者模拟从assets复制
            downDynamicLoadPlugin();
        }

    }

    /**
     * 下载插件
     */
    private void downDynamicLoadPlugin() {
        DownloadUtils.copyPluginFromAssets(mContext, DConstans.jarName, filePath + File.separator + DConstans.jarName, new DownLoadListener() {
            @Override
            public void onSuccess() {
                loadDynamicLoadPlugin();
            }

            @Override
            public void onFail(String errMsg) {
                if (null != listener) {
                    listener.onLoadFail(errMsg);
                }
            }

            @Override
            public void onProgress(int process) {
                if (null != listener) {
                    listener.onLoadProgress(process);
                }
            }
        });
    }

    /**
     * 加载插件
     */
    private void loadDynamicLoadPlugin() {
        try {
            dexClassLoader = new DexClassLoader(filePath + File.separator + DConstans.jarName, filePath, null, mContext.getClassLoader());
            if (null != listener) {
                listener.onLoadProgress(100);
                listener.onLoadSuccess();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (null != listener) {
                listener.onLoadFail(ex.getMessage());
            }
        }
    }

    /**
     * 通过类名获取一个实例
     * @param className
     * @return
     */
    public Object newInstance(String className) {
        try {
            if (null != dexClassLoader) {
                Class<?> dynamicClass = dexClassLoader.loadClass(className);
                return dynamicClass.newInstance();
            }
        } catch (ClassNotFoundException ex) {
            Log.e(TAG, "newInstance: " + ex.getMessage());
        } catch (InstantiationException ex) {
            Log.e(TAG, "newInstance: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            Log.e(TAG, "newInstance: " + ex.getMessage());
        }
        return null;
    }
}
