package com.example.hotel.controller;

import com.example.hotel.dto.CommentDto;
import com.example.hotel.model.Comment;
import com.example.hotel.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping(path = "api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<String> saveComment(@RequestBody CommentDto commentDto){
        commentService.save(commentDto);
        return new ResponseEntity<>("Comment Added Successfully.", HttpStatus.ACCEPTED);
    }

    @GetMapping
    public List<Comment> getComments(){
        List<Comment> comments = commentService.getComments();
        return comments;
    }

    @DeleteMapping
    public ResponseEntity<String> deleteComment(@RequestParam("id") Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment deleted successfully.", HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<String> updateComment(@RequestParam("id") Long id, @RequestBody CommentDto commentDto){
        commentService.updateComment(id,commentDto);
        return new ResponseEntity<>("Comment updated successfully.", HttpStatus.ACCEPTED);
    }
}
