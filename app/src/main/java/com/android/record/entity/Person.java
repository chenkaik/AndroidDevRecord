package com.android.record.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author: chen_kai
 * @date：2021-07-16
 * @desc：
 */
@Entity
public class Person {

    @Id(autoincrement = true)
    private Long id;

    private String name;

    @Generated(hash = 628813901)
    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
