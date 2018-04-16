package com.tianyu.dynamicloadlib.interfaces;

import android.content.Context;

/**
 * @author shisheng.zhao
 * @Description: 动态加载插件接口,插件负责实现,宿主调用使用
 * @date 2018-04-13 下午15:06
 */
public interface IDynamicLoad {
    interface aa{
        void geta();
    }

    interface bb{
        void getb();
    }


    Object getTemplate(Context context);

    int getTemplateType();
}
