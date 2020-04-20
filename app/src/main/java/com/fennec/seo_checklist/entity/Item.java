package com.fennec.seo_checklist.entity;

public class Item {

    //INSERT INTO `items`(`id`, `id_cat`, `intituler`, `stats`, `link`)

    public int id;
    public int id_cat;
    public String intituler;
    public int stats;
    public String link;

    public Item() {}

    public Item(int id, int id_cat, String intituler, int stats, String link) {
        this.id = id;
        this.id_cat = id_cat;
        this.intituler = intituler;
        this.stats = stats;
        this.link = link;
    }
}
