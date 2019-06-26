package com.fruit.xgh.home.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/9.
 * child商品类
 */
public class GoodsBean implements Serializable {

    private String name;
    private int cover_price;
    //图片
    private String figure;
    private String product_id;
    //详情
    private String remind;
    //规格
    private String specificaion;
    //级别
    private String grade;
    //贮存方式
    private String srorageCondition;
    //销量
    private int salesVolume;
    private int number = 1;

    /**
     * 是否处于编辑状态
     */
    private boolean isEditing;
    /**
     * 是否被选中
     */
    private boolean isChildSelected;


    public GoodsBean(String name, int cover_price, String figure, String product_id, String remind, String specificaion, String grade, String srorageCondition, int salesVolume) {
        this.name = name;
        this.cover_price = cover_price;
        this.figure = figure;
        this.product_id = product_id;
        this.remind = remind;
        this.specificaion = specificaion;
        this.grade = grade;
        this.srorageCondition = srorageCondition;
        this.salesVolume = salesVolume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCover_price() {

        return cover_price;
    }

    public void setCover_price(int cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }

    public boolean isChildSelected() {
        return isChildSelected;
    }

    public void setIsChildSelected(boolean isChildSelected) {
        this.isChildSelected = isChildSelected;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRemind(){
        return remind;
    }

    public void setRemind(String remind){
        this.remind = remind;
    }

    public String getSpecificaion(){
        return specificaion;
    }
    public void setSpecificaion(String specificaion){
        this.specificaion = specificaion;
    }

    public String getGrade(){
        return grade;
    }
    public void setGrade(String grade){
        this.grade = grade;
    }

    public String getSrorageCondition(){
        return srorageCondition;
    }
    public void setSrorageCondition(String srorageCondition){
        this.srorageCondition = srorageCondition;
    }

    public int getSalesVolume(){
        return salesVolume;
    }
    public void setSalesVolume(int salesVolume){
        this.salesVolume = salesVolume;
    }
    @Override
    public String toString() {
        return "GoodsBean{" +
                "name='" + name + '\'' +
                ", cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                ", isEditing=" + isEditing +
                ", isChildSelected=" + isChildSelected +
                '}';
    }
}
