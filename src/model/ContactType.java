package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public enum ContactType {
    CITY("Город"),
    PHONE("Телефон"),
    EMAIL("E-mail"),
    SKYPE("Skype"),
    PROFILE_HABR("Habr"),
    PROFILE_STACK("StackOverFlow"),
    ACCOUNT_GIT("Github");

    private String title;

    ContactType() {
    }

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
