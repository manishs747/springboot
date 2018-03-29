package com.base.entity;

import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Manish on 27/12/16.
 */




    @Entity
    @Table(name = "city")
   // @Getter
  //  @Sett
   // @EqualsAndHashCode(callSuper = false)
    public class CityEntity {
        @Id
        @Column(name = "id", nullable = false, insertable = false, updatable = false) private int id;
        @Column(name = "name", nullable = false, insertable = true, updatable = true) private String name;
        @Column(name = "state", nullable = false, insertable = true, updatable = true) private String state;
    }

