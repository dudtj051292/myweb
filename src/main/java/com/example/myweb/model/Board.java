package com.example.myweb.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min=2, max =30, message="제목은 2자 이상 30자 이하여야 합니다.")
    private String title;
    @NotNull
    @Column(name="st_content")
    @Size(min=3, message="내용은 2자 이상 입력해야합니다.")
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
