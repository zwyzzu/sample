package com.zhangwy.sample.entity;

/**
 * Created by 张维亚(zhangwy) on 2016/12/20 下午4:50.
 * Updated by zhangwy on 2016/12/20 下午4:50.
 * Description
 */
public class HomeSampleItem extends BaseEntity{
    private String name;
    private String desc;
    private Class<?> clazz;

    public HomeSampleItem(){
        super();
    }
    public HomeSampleItem(int code, String msg, String name, String desc, Class<?> clazz) {
        super(code, msg);
        this.setName(name);
        this.setDesc(desc);
        this.setClazz(clazz);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
