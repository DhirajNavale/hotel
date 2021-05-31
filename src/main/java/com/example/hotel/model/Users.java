package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(columnDefinition = "timestamp not null default current_timestamp", insertable = false)
    private Timestamp createdAt;

//    @OneToMany( targetEntity = Comment.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private List<Comment> comment;

}
