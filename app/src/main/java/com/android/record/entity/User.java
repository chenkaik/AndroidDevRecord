package com.android.record.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * date：2021-07-14
 * desc：
 */
@Entity // 实体类会在数据库中生成一个与之相对应的表
public class User {

    // 对应数据表中的主键，是一条数据的唯一标识。如果实体没有声明主键，默认创建Long类型主键"_id"自增。
    // 使用Long类型主键时可以通过@Id(autoincrement = true)设置为自增。
    @Id(autoincrement = true)
    private Long id;

//    @Unique // 该属性在数据库中只能有唯一值
    private String userId;

//    @Unique // 该属性在数据库中只能有唯一值
    private String userName;

    // @Property(nameInDb = "USER_NAME" )：可以自定义字段名
    // 注意外键不能使用该属性。表明这个属性对应数据表中的 USER_NAME 字段
//    @Property
    private int age;

    @Generated(hash = 1975221261)
    public User(Long id, String userId, String userName, int age) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
