package com.example.tatoebascraper.dto.tatoeba;

import lombok.Data;

@Data
class Audio {
    public int id;
    public String author;
    public String attribution_url;
    public Object license;


    @Override
    public String toString() {
        return "Audio{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", attribution_url='" + attribution_url + '\'' +
                ", license=" + license +
                '}';
    }
}
