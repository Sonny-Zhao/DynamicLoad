package com.tianyu.dynamicloadlib.utils;

import android.content.Context;
import android.content.res.AssetManager;
import com.tianyu.dynamicloadlib.listener.DownLoadListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author shisheng.zhao
 * @Description: 下载插件工具类
 * @date 2018-04-13 下午15:53
 */
public class DownloadUtils {

    /**
     * 从assets目录下将指定文件copy到指定位置
     * @param context
     * @param srcFile
     * @param destFile
     * @param listener
     */
    public static void copyPluginFromAssets(Context context, String srcFile, String destFile, DownLoadListener listener) {
        try {
            AssetManager manager = context.getAssets();
            InputStream inputStream = manager.open(srcFile);
            FileOutputStream outputStream = new FileOutputStream(new File(destFile));
            byte[] bytes = new byte[1024];
            int offest = -1;
            int total = -1;
            while ((offest = inputStream.read(bytes)) != -1) {
                total += offest;
                if (null != listener) {
                    listener.onProgress((total * 100) / 10000);
                }
                outputStream.write(bytes, 0, offest);
            }
            inputStream.close();
            outputStream.close();
            if (null != listener) {
                listener.onSuccess();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (null != listener) {
                listener.onFail(ex.getMessage());
            }
        }
    }
}
