package com.fennec.seo_checklist.entity;

public class Assigned {

    public int id;
    public int id_app;
    public int id_client;

    public Assigned() {
    }

    public Assigned(int id, int id_app, int id_client) {
        this.id = id;
        this.id_app = id_app;
        this.id_client = id_client;
    }
}
