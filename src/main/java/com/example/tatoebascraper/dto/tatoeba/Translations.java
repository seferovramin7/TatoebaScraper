package com.example.tatoebascraper.dto.tatoeba;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

public class Translations {
    public int id;
    public String text;
    public String lang;
    public int correctness;
    public Object script;
    public ArrayList<Object> transcriptions;
    public ArrayList<Audio2> audios;
    public boolean isDirect;
    public String lang_name;
    public String dir;
    public String lang_tag;
}
