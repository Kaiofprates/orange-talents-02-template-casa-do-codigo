package br.com.zup.desafio1.controllers.form.response;

public class BookFindAllRespose {
    private String title;
    private String id;

    public BookFindAllRespose(String title, String id) {
        this.title = title;
        this.id = id;
    }
    @Deprecated
    public BookFindAllRespose() {
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
