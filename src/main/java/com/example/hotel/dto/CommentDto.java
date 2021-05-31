package com.example.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Data
public class CommentDto {
    private Long id;
    private Float stars;
    private String comment;
    private Long hotelId;
    private Long userId;
}
