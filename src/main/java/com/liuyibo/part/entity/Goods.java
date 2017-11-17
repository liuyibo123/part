package com.liuyibo.part.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
public class Goods {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String price;
    private String dw;
    private String category;
    private String bz;
    private String imageUrl;

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    private String idnumber;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", dw='" + dw + '\'' +
                ", category='" + category + '\'' +
                ", bz='" + bz + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", IDNumber='" + idnumber + '\'' +
                '}';
    }

    public static Goods  stringToGoods(String s){
        String[] strings  = s.split("-");
        for(int i=0;i<strings.length;i++){
            if(strings[i].length()>=3){
                strings[i] = strings[i].trim();
            }
        }
        Goods goods = new Goods();
        goods.setName(strings[0]);
        goods.setPrice(strings[1]);
        goods.setDw(strings[2]);
        goods.setCategory(strings[3]);
        goods.setBz(strings[4]);
        goods.setIdnumber(strings[5]);
        return goods;
    }
    public static String goodsToString(Goods g){
        StringBuilder sb = new StringBuilder("");
        sb.append(g.getName()).append(" - ")
                .append(g.getPrice()).append(" - ")
                .append(g.getDw()).append(" - ")
                .append(g.getCategory()).append(" - ")
                .append(g.getBz()).append(" - ")
                .append(g.getIdnumber());
        return sb.toString();
    }
}
