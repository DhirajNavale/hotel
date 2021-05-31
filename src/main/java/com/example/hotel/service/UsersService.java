package com.example.hotel.service;

import com.example.hotel.model.Users;
import com.example.hotel.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional
    public void addUser(Users users) {
        usersRepository.save(users);
    }

    public Users getUser(Long userId) {
        Optional<Users> user = usersRepository.findById(userId);
        if(!user.isPresent())
            throw new IllegalStateException("User Not Exits");
        return user.get();
    }

    @Transactional
    public void updateUser(Long id, Users users) {
        if(!usersRepository.findById(id).isPresent())
            throw new IllegalStateException("User not exist.");

        usersRepository.save(users);
    }

    public List<Users> getUsers() {
        List<Users> users = usersRepository.findAll();
        return users;
    }

    public void deleteUser(Long id) {
        usersRepository.findById(id).ifPresentOrElse((e)->{
            usersRepository.deleteById(id);
        }, ()->{
            throw new IllegalStateException("User does not exist");
        });
    }
}
