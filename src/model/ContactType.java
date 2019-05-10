package model;

public enum ContactType {
    CITY("Город"),
    PHONE("Телефон"),
    EMAIL("E-mail"),
    SKYPE("Skype"),
    PROFILE_HABR("Habr"),
    PROFILE_STACK("StackOverFlow"),
    ACCOUNT_GIT("Github");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
