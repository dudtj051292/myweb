package com.example.myweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bld_role")
@Data
public class Role {
    @Id
    private Long no_seq;

    private String st_role;

    private String ds_role;


    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>() ;

}
