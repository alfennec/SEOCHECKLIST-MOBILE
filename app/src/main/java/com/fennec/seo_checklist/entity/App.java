package com.fennec.seo_checklist.entity;

public class App {

    //INSERT INTO `apps`(`id`, `title`, `link1`, `link2`, `text1`, `text2`, `indication`, `version_app`)

    public int id;
    public String title;
    public String link1;
    public String link2;
    public String text1;
    public String text2;
    public String indication;
    public String version_app;

    public App() {
    }

    public App(int id, String title, String link1, String link2, String text1, String text2, String indication, String version_app) {
        this.id = id;
        this.title = title;
        this.link1 = link1;
        this.link2 = link2;
        this.text1 = text1;
        this.text2 = text2;
        this.indication = indication;
        this.version_app = version_app;
    }
}
