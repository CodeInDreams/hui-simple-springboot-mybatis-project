package com.example.demo.entity;

import java.sql.Timestamp;

/**
 * Created by 张彦辉 on 2017/7/17.
 */
public class Message {

    private Long id;
    private String msg;
    private Timestamp createTime;

    public Message(String msg) {
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
