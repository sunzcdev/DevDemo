package com.cnjaj.myapplication.dao;

import com.cnjaj.myapplication.dao.inject.Column;
import com.cnjaj.myapplication.utils.DateUtils;

public class OfflineMessage implements IDao {
    /**
     * 插入数据库中的主键id
     */
    @Column(primaryKey = true)
    private int id;
    @Column
    private String rktime = DateUtils.getCurrentDateTime();// 入库时间
    @Column
    private int isupload;// 是否上传0未上传1上传,
    private String positionreportstate;//盲区标识
    private String current_mileage;//里程
    private String current_machine_speed;//转速
    private DBDao dbDao = DBDao.getInstance();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRktime() {
        return rktime;
    }

    public void setRktime(String rktime) {
        this.rktime = rktime;
    }

    public int getIsupload() {
        return isupload;
    }

    public void setIsupload(int isupload) {
        this.isupload = isupload;
    }

    public String getPositionreportstate() {
        return positionreportstate;
    }

    public void setPositionreportstate(String positionreportstate) {
        this.positionreportstate = positionreportstate;
    }

    public String getCurrent_mileage() {
        return current_mileage;
    }

    public void setCurrent_mileage(String current_mileage) {
        this.current_mileage = current_mileage;
    }

    public String getCurrent_machine_speed() {
        return current_machine_speed;
    }

    public void setCurrent_machine_speed(String current_machine_speed) {
        this.current_machine_speed = current_machine_speed;
    }

    @Override
    public String toString() {
        return "OfflineMessage{" +
                "id=" + id +
                ", rktime='" + rktime + '\'' +
                ", isupload=" + isupload +
                ", positionreportstate='" + positionreportstate + '\'' +
                ", current_mileage='" + current_mileage + '\'' +
                ", current_machine_speed='" + current_machine_speed + '\'' +
                '}';
    }

    @Override
    public int save() {
        dbDao.insert(this);
        return 0;
    }

    @Override
    public int del() {
        dbDao.delByBeans(this);
        return 0;
    }

    @Override
    public int update() {
        dbDao.update(this);
        return 0;
    }
}
