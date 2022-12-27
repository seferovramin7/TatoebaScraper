package com.example.tatoebascraper.dto.tatoeba;

import lombok.Data;

@Data
class Audio2 {
    public int id;
    public Object external;
    public int sentence_id;
    public User user;
    public String author;
    public String attribution_url;
    public Object license;

    @Override
    public String toString() {
        return "Audio2{" +
                "id=" + id +
                ", external=" + external +
                ", sentence_id=" + sentence_id +
                ", user=" + user +
                ", author='" + author + '\'' +
                ", attribution_url='" + attribution_url + '\'' +
                ", license=" + license +
                '}';
    }
}
