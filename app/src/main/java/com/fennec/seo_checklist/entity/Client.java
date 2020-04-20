package com.fennec.seo_checklist.entity;

public class Client {

    //INSERT INTO `client`(`id`, `first_name`, `last_name`, `tel`, `sexe`, `email`, `pass`, `role`)

    public int id;
    public String first_name;
    public String last_name;
    public String tel;
    public int sexe;
    public String email;
    public String pass;
    public int role;

    public Client() { }

    public Client(int id, String first_name, String last_name, String tel, int sexe, String email, String pass, int role) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.tel = tel;
        this.sexe = sexe;
        this.email = email;
        this.pass = pass;
        this.role = role;
    }
}
