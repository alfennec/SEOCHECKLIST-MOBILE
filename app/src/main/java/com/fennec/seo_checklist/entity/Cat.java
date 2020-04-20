package com.fennec.seo_checklist.entity;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class Cat implements Parent<Item> {

    //INSERT INTO `Cat`(`id`, `id_app`, `intituler`, `nbr_item`)

    public int id;
    public int id_app;
    public String intituler;
    public int nbr_item;


    public ArrayList<Item> item_list;

    public Cat() {
    }

    public Cat(int id, int id_app, String intituler, int nbr_item, ArrayList<Item> item_list) {
        this.id = id;
        this.id_app = id_app;
        this.intituler = intituler;
        this.nbr_item = nbr_item;
        this.item_list = item_list;
    }

    @Override
    public List<Item> getChildList() {
        return item_list;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
