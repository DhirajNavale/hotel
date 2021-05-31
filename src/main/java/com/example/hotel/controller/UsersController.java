package com.example.hotel.controller;

import com.example.hotel.model.Users;
import com.example.hotel.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "api/v1/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody Users users){
        usersService.addUser(users);
        return new ResponseEntity<>("User Added Successfully.", HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestParam("id") Long id, @RequestBody Users users){
        usersService.updateUser(id, users);
        return new ResponseEntity<>("User updated successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping
    public List<Users> getUsers(){
        return usersService.getUsers();

    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam("id") Long id){
        usersService.deleteUser(id);
        return new  ResponseEntity<>("User deleted Successfully.", HttpStatus.ACCEPTED);
    }
}
