package com.example.btrestful1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Composer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int age;
    private String hometown;

    @OneToMany(mappedBy = "composer")

    //xoa music dung  @JsonIgnore
    @JsonIgnore
    private Collection<Music> music;


    public Composer(int id, String name, int age, String hometown) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hometown = hometown;
    }

}