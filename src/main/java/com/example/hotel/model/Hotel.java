package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data
@Table
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private Date date;
    private Integer rooms;
    @Min(value = 0)
    @Max(value = 10)
    private Float stars;
    @Column(columnDefinition = "varchar(1) default 'Y'")
    private String availability = "Y";
    @Column(columnDefinition = "varchar(1) default 'N'")
    private String wifi = "N";
    @Column(columnDefinition = "varchar(1) default 'N'")
    private String restaurant = "N";
    @Column(columnDefinition = "varchar(1) default 'N'")
    private String airConditioning = "N";
    @Column(columnDefinition = "varchar(1) default 'N'")
    private String meals = "N";


    @OneToMany( targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private List<Comment> comment;

}
