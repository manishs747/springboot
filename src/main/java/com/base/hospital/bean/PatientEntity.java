package com.base.hospital.bean;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "patient")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PatientEntity {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "age", nullable = false, insertable = true, updatable = true)
    private int age;

    @Column(name = "gender", nullable = false, insertable = true, updatable = true)
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "meta_info")
    private String meta_info;

    @Column(name = "enabled", nullable = false, insertable = true, updatable = true)
    private boolean enabled;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;
}
