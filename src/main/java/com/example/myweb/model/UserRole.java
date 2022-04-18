package com.example.myweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bld_userrole")
@Data
public class UserRole {
    @Id
    @GeneratedValue
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "BLD_USER")
    private User id;
    @ManyToOne
    @JoinColumn(name = "BLD_ROLE")
    private Role role;
}
