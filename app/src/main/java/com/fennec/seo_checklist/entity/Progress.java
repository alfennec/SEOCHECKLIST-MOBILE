package com.fennec.seo_checklist.entity;

public class Progress {

    //INSERT INTO `Progress`(`id`, `id_client`, `id_app`, `id_cat`, `id_item`, `status`, `date_status`)

    public int id;
    public int id_client;
    public int id_app;
    public int id_cat;
    public int id_item;
    public int status;
    public String date_status;


    public Progress() {
    }

    public Progress(int id_app, int id_cat, int id_item, int status, String date_status) {
        this.id_app = id_app;
        this.id_cat = id_cat;
        this.id_item = id_item;
        this.status = status;
        this.date_status = date_status;
    }

    public Progress(int id_client, int id_app, int id_cat, int id_item, int status, String date_status) {
        this.id_client = id_client;
        this.id_app = id_app;
        this.id_cat = id_cat;
        this.id_item = id_item;
        this.status = status;
        this.date_status = date_status;
    }

    public Progress(int id, int id_client, int id_app, int id_cat, int id_item, int status, String date_status) {
        this.id = id;
        this.id_client = id_client;
        this.id_app = id_app;
        this.id_cat = id_cat;
        this.id_item = id_item;
        this.status = status;
        this.date_status = date_status;
    }
}
