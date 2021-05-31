package com.example.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 0)
    @Max(value = 10)
    private Float stars;
    private String comment;


    @JsonIgnore
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", nullable = false, referencedColumnName = "id")
    private Hotel hotel;

//    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @NotFound(action= NotFoundAction.IGNORE)
    private Users users;

}
