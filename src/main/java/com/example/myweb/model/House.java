package com.example.myweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "bld_house")
public class House {
    @Column
    private String st_area;
    @Id
    @Column
    private String st_housename;

    @Column
    private String st_address;
    @Column
    private String st_telephone;
    
}
