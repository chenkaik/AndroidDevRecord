package com.android.record.db;

import com.android.record.entity.Person;
import com.android.record.entity.User;
import com.android.record.greendao.PersonDao;
import com.android.record.greendao.UserDao;

/**
 * date：2021-07-14
 * desc：初始化、存放及获取DaoUtils
 */
public class DaoUtilsStore {

    private volatile static DaoUtilsStore instance = new DaoUtilsStore();
    private final CommonDaoUtils<User> mUserDaoUtils;
    private final CommonDaoUtils<Person> mPersonDaoUtils;

    public static DaoUtilsStore getInstance() {
        return instance;
    }

    private DaoUtilsStore() {
        DaoManager mManager = DaoManager.getInstance();
        UserDao _UserDao = mManager.getDaoSession().getUserDao();
        mUserDaoUtils = new CommonDaoUtils<>(User.class, _UserDao);

        PersonDao _PersonDao = mManager.getDaoSession().getPersonDao();
        mPersonDaoUtils = new CommonDaoUtils<>(Person.class,_PersonDao);
    }

    public CommonDaoUtils<User> getUserDaoUtils() {
        return mUserDaoUtils;
    }

    public CommonDaoUtils<Person> getPersonDaoUtils() {
        return mPersonDaoUtils;
    }

}
