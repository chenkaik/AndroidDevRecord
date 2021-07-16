package com.android.record.db;

import com.android.record.entity.Person;
import com.android.record.entity.User;
import com.android.record.greendao.PersonDao;
import com.android.record.greendao.UserDao;

/**
 * @author chen_kai
 * @date：2021-07-15
 * @desc：初始化、存放及获取DaoUtils
 */
public class DaoUtilsStore {

    private volatile static DaoUtilsStore instance = new DaoUtilsStore();
    private final DaoManager mManager;
    private CommonDaoUtils<User> mUserDaoUtils;
    private CommonDaoUtils<Person> mPersonDaoUtils;

    public static DaoUtilsStore getInstance() {
        return instance;
    }

    private DaoUtilsStore() {
        mManager = DaoManager.getInstance();
//        UserDao _UserDao = mManager.getDaoSession().getUserDao();
//        mUserDaoUtils = new CommonDaoUtils<>(User.class, _UserDao);
    }

    public synchronized CommonDaoUtils<User> getUserDaoUtils() {
        if (mUserDaoUtils == null) {
            UserDao userDao = mManager.getDaoSession().getUserDao();
            mUserDaoUtils = new CommonDaoUtils<>(User.class, userDao);
        }
        return mUserDaoUtils;
    }

    public synchronized CommonDaoUtils<Person> getPersonDaoUtils() {
        if (mPersonDaoUtils == null) {
            PersonDao personDao = mManager.getDaoSession().getPersonDao();
            mPersonDaoUtils = new CommonDaoUtils<>(Person.class, personDao);
        }
        return mPersonDaoUtils;
    }

}
