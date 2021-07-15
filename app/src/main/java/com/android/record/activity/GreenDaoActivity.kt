package com.android.record.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.record.R
import com.android.record.databinding.ActivityGreenDaoBinding
import com.android.record.db.DaoManager
import com.android.record.db.DaoUtilsStore
import com.android.record.entity.User
import java.util.*

class GreenDaoActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "GreenDaoActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityGreenDaoBinding = ActivityGreenDaoBinding.inflate(layoutInflater)
        setContentView(activityGreenDaoBinding.root)
        activityGreenDaoBinding.btnAdd.setOnClickListener(this)
        activityGreenDaoBinding.btnDelete.setOnClickListener(this)
        activityGreenDaoBinding.btnUpdate.setOnClickListener(this)
        activityGreenDaoBinding.btnQuery.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add -> {
//                User user = new User();
//                user.setUserId("" + new Random().nextInt(4) + 1);
//                user.setUserName("张三" + new Random().nextInt(4) + 1);
//                user.setAge(18 + new Random().nextInt(10));
//                DaoUtilsStore.getInstance().getUserDaoUtils().insert(user);
                val user = User()
                user.userId = "" + Random().nextInt(4) + 1
                user.userName = "张三" + Random().nextInt(4) + 1
                user.age = 18 + Random().nextInt(10)
                DaoUtilsStore.getInstance().userDaoUtils.insert(user)

                val user1 = User()
                user1.userId = "" + Random().nextInt(4) + 1
                user1.userName = "王五" + Random().nextInt(4) + 1
                user1.age = 18 + Random().nextInt(10)
                DaoUtilsStore.getInstance().userDaoUtils.insert(user1)
            }
            R.id.btn_delete -> {
                val userList = DaoUtilsStore.getInstance().userDaoUtils.queryAll()
                if (userList.size > 0) {
                    val isSuccess = DaoUtilsStore.getInstance().userDaoUtils.delete(userList[userList.size - 1])
                    if (isSuccess) {
                        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            R.id.btn_update -> {
                val userList = DaoUtilsStore.getInstance().userDaoUtils.queryAll()
                if (userList.size > 0) {
                    val user = userList[userList.size - 1]
                    user.userName = "更新姓名" + Random().nextInt(4) + 1
                    val isSuccess = DaoUtilsStore.getInstance().userDaoUtils.update(user)
                    if (isSuccess) {
                        Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            R.id.btn_query -> {
                val userList = DaoUtilsStore.getInstance().userDaoUtils.queryAll()
                for (user: User in userList) {
                    Log.e(TAG, "onClick: id= " + user.id);
                    Log.e(TAG, "onClick: " + user.userId);
                    Log.e(TAG, "onClick: " + user.userName);
                    Log.e(TAG, "onClick: " + user.age);
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        DaoManager.getInstance().closeConnection()
    }


    /**
     * private void initUser() {

    //        //用户ID生成器
    //        mIdWorker = new SnowflakeIdGenerator(0, 0);

    DaoUtilsStore.getInstance().getUserDaoUtils().deleteAll();

    List<User> mUserList = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
    User user = new User();
    user.setId((long) i);
    user.setUserId(i + "");
    // 随机生成汉语名称
    user.setUserName("测试" + i);
    user.setAge(18 + random.nextInt(10));
    mUserList.add(user);
    }

    DaoUtilsStore.getInstance().getUserDaoUtils().insertMultiple(mUserList);
    }
     */

}