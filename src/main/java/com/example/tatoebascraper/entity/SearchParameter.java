package com.example.tatoebascraper.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SEARCH_PARAMETER", schema = "public")
public class SearchParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MESSAGE_ID")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "CHAT_ID")
    private Chat chat;


    @Column(name = "FROM_WHERE")
    private String fromWhere;

    @Column(name = "FROM_WHERE_RAW")
    private String fromWhereRaw;

    @Column(name = "TO_WHERE")
    private String toWhere;

    @Column(name = "TO_WHERE_RAW")
    private String toWhereRaw;

    @Column(name = "DATE")
    private String date;

    @Column(name = "TIME")
    private String time;

}
