package com.example.hotel.service;

import com.example.hotel.dto.CommentDto;
import com.example.hotel.model.Comment;
import com.example.hotel.model.Hotel;
import com.example.hotel.model.Users;
import com.example.hotel.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Data
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final HotelService hotelService;
    private final UsersService usersService;

    @Transactional
    public void save(CommentDto commentDto) {
        Hotel hotel = hotelService.getHotel(commentDto.getHotelId());
        Users user = usersService.getUser(commentDto.getUserId());
        hotelService.updateRating(commentDto.getHotelId(), commentDto.getStars());

        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setStars(commentDto.getStars());
        comment.setHotel(hotel);
        comment.setUsers(user);
//        ModelMapp
        commentRepository.save(comment);
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public void deleteComment(Long id) {
        commentRepository.findById(id).ifPresentOrElse((e)->{
            commentRepository.deleteById(id);
        }, ()->{
            throw new IllegalStateException("Comment does not exist");
        });
    }

    @Transactional
    public void updateComment(Long id,CommentDto commentDto) {

        Hotel hotel = hotelService.getHotel(commentDto.getHotelId());
        Users user = usersService.getUser(commentDto.getUserId());
        hotelService.updateRating(commentDto.getHotelId(), commentDto.getStars());

        Comment comment = new Comment();
        comment.setId(id);
        comment.setComment(commentDto.getComment());
        comment.setStars(commentDto.getStars());
        comment.setHotel(hotel);
        comment.setUsers(user);


//        ModelMapp
        commentRepository.save(comment);
    }
}
