package com.jump2digital.jump2digitalchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "website")
    private String website;

    @Column(name = "name")
    private String name;

    @Column(name = "founded")
    private Integer founded;

    @Column(name = "size")
    private String size;

    @Column(name = "locality")
    private String locality;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    @Column(name = "industry")
    private String industry;

    @Column(name = "linkedin_url")
    private String linkedInUrl;



}
