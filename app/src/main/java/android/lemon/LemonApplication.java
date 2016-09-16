package android.lemon;

import android.app.Application;
import android.lemon.config.BaseConfig;
import android.lemon.utils.FileUtil;

import com.squareup.leakcanary.LeakCanary;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by babyt on 2016/9/16.
 */
public class LemonApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileUtil.initFileDir(this);
        BaseConfig.DB_NAME = null;
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            initBugShow();
        } else {
//            initBugReport();
        }
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
