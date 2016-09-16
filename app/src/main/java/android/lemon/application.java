package android.lemon;

import android.lemon.config.BaseConfig;
import android.lemon.utils.FileUtil;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by babyt on 2016/9/16.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileUtil.initFileDir(this);
        BaseConfig.DB_NAME = null;
    }

    public void initBugShow() {
        //来自 https://github.com/Ereza/CustomActivityOnCrash
        CustomActivityOnCrash.install(this);
        //如果程序在后台显示，不在用户前显示，是否弹出框
        CustomActivityOnCrash.setLaunchErrorActivityWhenInBackground(true);
//        //是否显示错误详情
        CustomActivityOnCrash.setShowErrorDetails(true);
        //设置是否可以重启app
        CustomActivityOnCrash.setEnableAppRestart(true);
//        //设置错误之后被重启的界面
//        CustomActivityOnCrash.setRestartActivityClass();
        //设置显示错误的界面
        //必须在manifest里指定
//       CustomActivityOnCrash.setErrorActivityClass(Class<? extends Activity>);
    }
}
