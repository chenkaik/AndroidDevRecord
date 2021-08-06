package com.android.record.db;

import com.android.record.greendao.DaoSession;
import com.android.record.greendao.UserDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * @author: chen_kai
 * @date：2021-07-15
 * @desc：用于完成对数据表的操作
 */
public class CommonDaoUtils<T> {

    private final DaoSession mDaoSession;
    private final Class<T> entityClass;
    private final AbstractDao<T, Long> entityDao;

    public CommonDaoUtils(Class<T> pEntityClass, AbstractDao<T, Long> pEntityDao) {
        DaoManager mManager = DaoManager.getInstance();
        mDaoSession = mManager.getDaoSession();
        entityClass = pEntityClass;
        entityDao = pEntityDao;
    }

    /**
     * 插入记录，如果表未创建，先创建表
     */
    public boolean insert(T pEntity) {
        return entityDao.insert(pEntity) != -1;
    }

    /**
     * 存在就更新 不存在就存储
     */
    public void saveOrUpdate(T item) {
        mDaoSession.insertOrReplace(item);
    }

    /**
     * 刷新
     */
    public void refresh(T item) {
        mDaoSession.refresh(item);
    }

    /**
     * 插入多条数据，在子线程操作
     */
    public boolean insertMultiple(final List<T> pEntityList) {
        try {
            mDaoSession.runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T entity : pEntityList) {
                        // 数据存在则替换 数据不存在则插入
                        mDaoSession.insertOrReplace(entity);
                    }
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改一条数据
     */
    public boolean update(T entity) {
        try {
            mDaoSession.update(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除单条记录
     */
    public boolean delete(T entity) {
        try {
            // 按照id删除
            mDaoSession.delete(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 批量删除
     */
    public boolean deleteItem(WhereCondition cond) {
        try {
            QueryBuilder<T> where = mDaoSession.queryBuilder(entityClass).where(cond);
            DeleteQuery<T> deleteQuery = where.buildDelete();
            deleteQuery.executeDeleteWithoutDetachingEntities();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除所有记录
     */
    public boolean deleteAll() {
        try {
            // 按照id删除
            mDaoSession.deleteAll(entityClass);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有记录
     */
    public List<T> queryAll() {
        return mDaoSession.loadAll(entityClass);
    }

    /**
     * 根据主键id查询记录
     */
    public T queryById(long key) {
        return mDaoSession.load(entityClass, key);
    }

    /**
     * 使用native sql进行查询操作
     * <p>
     * List<Student> students = daoSession.queryRaw(Student.class, " where id = ?", s);
     * eq()："equal ('=?')" 等于；
     * notEq() ："not equal ('<>?')" 不等于；
     * like()：" LIKE ?" 值等于；
     * between()：" BETWEEN ? AND ?" 取中间范围；
     * in()：" IN (" in命令;
     * notIn()：" NOT IN (" not in 命令;
     * gt()：">?" 大于;
     * lt()："<? " 小于;
     * ge()：">=?" 大于等于;
     * le()："<=? " 小于等于;
     * isNull()：" IS NULL" 为空;
     * isNotNull()：" IS NOT NULL" 不为空;
     */
    public List<T> queryByNativeSql(String sql, String[] conditions) {
        return mDaoSession.queryRaw(entityClass, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * <p>
     * where(WhereCondition cond, WhereCondition... condMore): 查询条件，参数为查询的条件！
     * or(WhereCondition cond1, WhereCondition cond2, WhereCondition... condMore): 嵌套条件或者，用法同or。
     * and(WhereCondition cond1, WhereCondition cond2, WhereCondition... condMore): 嵌套条件且，用法同and。
     * join(Property sourceProperty, Class<J> destinationEntityClass):多表查询，后面会讲。
     * 输出结果有四种方式，选择其中一种最适合的即可，list()返回值是List,而其他三种返回值均实现Closeable,需要注意的不使用数据时游标的关闭操作：
     * list （）所有实体都加载到内存中。结果通常是一个没有魔法的 ArrayList。最容易使用。
     * listLazy （）实体按需加载到内存中。首次访问列表中的元素后，将加载并缓存该元素以供将来使用。必须关闭。
     * listLazyUncached （）实体的“虚拟”列表：对列表元素的任何访问都会导致从数据库加载其数据。必须关闭。
     * listIterator （）让我们通过按需加载数据（懒惰）来迭代结果。数据未缓存。必须关闭。
     * orderAsc() 按某个属性升序排；
     * orderDesc() 按某个属性降序排；
     */
    public List<T> queryByQueryBuilder(WhereCondition cond, WhereCondition... condMore) {
        QueryBuilder<T> queryBuilder = mDaoSession.queryBuilder(entityClass);
        return queryBuilder.where(cond, condMore).list();
    }

    /**
     * //搜索条件为Id值大于1，即结果为[2,3,4,5,6,7,8,9,10,11];
     * // offset(2)表示往后偏移2个，结果为[4,5,6,7,8,9,10,11,12,13];
     * List<Student> list = qb.where(StudentDao.Properties.Id.gt(1)).limit(10).offset(2).list();
     */
    public List<T> queryByQueryName(WhereCondition cond, WhereCondition... condMore) {
        // 名字查询
//        QueryBuilder<T> queryBuilder = mDaoSession.queryBuilder(entityClass).orderAsc(UserDao.Properties.Id);
        // id条件查询
        QueryBuilder<T> queryBuilder = mDaoSession
                .queryBuilder(entityClass);
        return queryBuilder.where(cond, queryBuilder.and(condMore[0], condMore[1])).list();
    }

}
