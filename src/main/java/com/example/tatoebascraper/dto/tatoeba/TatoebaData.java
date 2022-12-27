package com.example.tatoebascraper.dto.tatoeba;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

@Data
public class TatoebaData {
    public int id;
    public String text;
    public String lang;
    public int correctness;
    public Object script;
    public String license;
    public ArrayList<ArrayList<Translations>> translations;
    public ArrayList<Object> transcriptions;
    public ArrayList<Audio> audios;
    public User user;
    public String highlightedText;
    public String expandLabel;
    public String lang_name;
    public String dir;
    public String lang_tag;
    public Object is_favorite;
    public boolean is_owned_by_current_user;
    public Object permissions;
    public int max_visible_translations;
    public Object current_user_review;

    @Override
    public String toString() {
        return "Root{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", lang='" + lang + '\'' +
                ", correctness=" + correctness +
                ", script=" + script +
                ", license='" + license + '\'' +
                ", transcriptions=" + transcriptions +
                ", audios=" + audios +
                ", user=" + user +
                ", highlightedText='" + highlightedText + '\'' +
                ", expandLabel='" + expandLabel + '\'' +
                ", lang_name='" + lang_name + '\'' +
                ", dir='" + dir + '\'' +
                ", lang_tag='" + lang_tag + '\'' +
                ", is_favorite=" + is_favorite +
                ", is_owned_by_current_user=" + is_owned_by_current_user +
                ", permissions=" + permissions +
                ", max_visible_translations=" + max_visible_translations +
                ", current_user_review=" + current_user_review +
                '}';
    }
}

