package com.example.myweb.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "bld_board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="no_boardseq")
    private Long seq;
    @Column(name="st_writer")
    private String writer;
    @NotNull
    @Column(name="st_title")
    private String title;
    @Column(name="st_content")
    private String content;
    @Column(name="st_boardpw")
    private String boardpw;
    @Column(name="yn_delete")
    private String yn_delete;
    @Column(name="dm_register")
    private String dm_register;
    @Column(name="dm_change")
    private String dm_change;
    
}
