package com.mybank.entity;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(unique = true)
    private String name; //  "ADMIN", "BANK", "CLIENT"

    public Role() {}

    public Role(String name) {
        this.name = name;
    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
