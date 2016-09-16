package android.lemon.db;

import android.content.Context;
import android.lemon.config.BaseConfig;

import com.litesuits.orm.LiteOrm;

/**
 * 默认的数据库模式
 * Created by Kevin on 2015/9/27.
 */
public class DefaultDB {

    private static LiteOrm liteOrm;

    private static int mode = 0;

    private static final int SINGLE_MODE = 1;

    private static final int CASCADE_MODE = 2;

    /**
     * @param context
     * @return
     */
    public static LiteOrm getSingleInstance(Context context) {
        if (liteOrm == null || mode == CASCADE_MODE) {
            if (BaseConfig.DB_NAME == null || BaseConfig.DB_NAME.equals("")) {
                BaseConfig.DB_NAME = context.getPackageName();

            }
            liteOrm = LiteOrm.newSingleInstance(context,  BaseConfig.DB_NAME);
        }
        return liteOrm;
    }

    public static LiteOrm getCascadeInstance(Context context) {
        if (liteOrm == null || mode == SINGLE_MODE) {
            if (BaseConfig.DB_NAME == null || BaseConfig.DB_NAME.equals("")) {
                BaseConfig.DB_NAME = context.getPackageName();

            }
            liteOrm = LiteOrm.newCascadeInstance(context, context.getPackageName());
        }
        return liteOrm;
    }

    private DefaultDB() {
    }

}
