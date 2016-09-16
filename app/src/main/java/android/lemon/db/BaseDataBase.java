package android.lemon.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * 使用默认的基础的数据操作方式
 * Created by Kevin on 2015/9/27.
 */
public class BaseDataBase<T> {
    protected LiteOrm liteOrm;

    Context context;
    /**
     * 初始化对于T表的操作
     *
     * @param context 上下文
     */
    public BaseDataBase(Context context) {
        this.context = context;
        liteOrm = DefaultDB.getSingleInstance(context);
        liteOrm.setDebugged(true);
    }

    /** 得到当前的数据库名称。
     * @return 数据库名
     */
    public String getDBName(){
        return liteOrm.getDataBaseConfig().dbName;
    }

    /**
     * 切换带有关联关系的表操作
     */
    public void cascade() {
        liteOrm = DefaultDB.getCascadeInstance(context);
    }

    /**
     * 切换为调试模式
     *
     * @param b 是否启用调试
     */
    public void debug(boolean b) {
        liteOrm.setDebugged(b);
    }

    /**
     * 保存T
     *
     * @param t 需要被保存的对象
     */
    public long save(T t) {
        return liteOrm.save(t);
    }

    /** 保存一个集合
     * @param ts 被保存的数据集合
     */
    public void saveAll(Collection<T> ts){
        liteOrm.save(ts);
    }

    /**
     * 根据t中的id删除数据
     *
     * @param t 需要被删除的对象
     */
    public int delete(T t) {
        return liteOrm.delete(t);
    }

    /**
     * 根据t中的id更新数据
     *
     * @param t
     */
    public void update(T t) {
        liteOrm.update(t);
    }

    /**
     * 返回所有的t表数据
     */
    public List<T> findAll() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return liteOrm.query( (Class)params[0]);
    }

    /**
     * 删除指定id
     *
     * @param id int类型
     * @return 返回查找到的对象
     */
    public T findOne(long id) {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (T) liteOrm.queryById(id, (Class)params[0]);
    }

    /**
     * 得到操作数据库对象
     * @return
     */
    public LiteOrm getLiteOrm() {
        return liteOrm;
    }

    /**
     * 删除指定id
     *
     * @param id String类型
     * @return 返回当前查找的对象
     */
    public T findOne(String id) {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (T) liteOrm.queryById(id, (Class)params[0]);
    }

    /**
     * 删除所有数据
     *
     * @return 删除的行数
     */
    public int deleteAll() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return liteOrm.deleteAll( (Class)params[0]);
    }


}
