package com.epam.parsing.bean;



import java.util.Map;

public class Dish {
    private MenuItem item;
    private int id;
    private String src;
    private String name;
    private Map<String,String> description;
    private String portion;


    public Dish() {

    }

    public Dish(MenuItem item, int id, String src, String name, Map<String,String> description, String portion) {
        this.item = item;
        this.id = id;
        this.src = src;
        this.name = name;
        this.description = description;
        this.portion = portion;

    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String,String> getDescription() {
        return description;
    }

    public void setDescription(Map<String,String> description) {
        this.description = description;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }



    @Override
    public String toString() {
        return "Dish{" +
                "item=" + item +
                ", id=" + id +
                ", src='" + src + '\'' +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", portion='" + portion + '\''+
                '}';
    }
}
