package com.example.myweb.model;




import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "bld_user")
public class User {
    @Id
    @Column(name="st_id")
    private String id;
    @Column(name="st_password")
    private String password;
    @Column(name="st_name")
    private String username;
    @Column(name="st_phonenumber")
    private String phone;
    @Column(name="st_address")
    private String address;
    @Column(name="st_email")
    private String email;
    private String Enabled;

    @ManyToMany
    @JoinTable(
        name = "bld_userrole",
        joinColumns = @JoinColumn(name = "st_id"),
        inverseJoinColumns = @JoinColumn(name ="st_role")
    )
    private List<Role> roles = new ArrayList();

}

