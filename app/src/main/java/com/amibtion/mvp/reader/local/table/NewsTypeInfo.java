package com.amibtion.mvp.reader.local.table;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by nieyuxin on 2017/3/13.
 */

public class NewsTypeInfo {

    @Id(autoincrement = true)
    private Long  id;
    private String name;
    private String typeId;

    @Generated(hash = 1707873593)
    public NewsTypeInfo(Long id,String name,String typeId){
        this.id = id;
        this.name = name;
        this.typeId = typeId;
    }

    @Generated(hash = 215923915)
    public NewsTypeInfo() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "NewsTypeInfo{" +
                "id=" + id +
                ", name='" +  name +'\'' +
                ",typeId='" + typeId + '\''+
                '}';
    }
}
