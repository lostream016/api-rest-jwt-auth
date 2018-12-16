package com.backend.api.Controller;

import com.backend.api.DAO.UserDAO;
import com.backend.api.Model.User;
import com.backend.api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserRepository ss;

    /* Save User */
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User usr){
        return userDAO.save(usr);
    }

    /* Get All Users */
    @GetMapping("/users")
    public List<User> getAllUser(){
        return userDAO.findAll();
    }

    /* Get Specific User */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Long id){
        User usr = userDAO.findById(id);

        return ResponseEntity.ok().body(usr);
    }

    /* Update User */
    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable(value = "id") Long id, @RequestBody User requestBody){
        User usr = userDAO.findById(id);

        usr.setName(requestBody.getName());
        usr.setAge(requestBody.getAge());
        usr.setFk_city(requestBody.getFk_city());

        userDAO.save(usr);
        return ResponseEntity.ok().body(usr);
    }

    /* Delete User */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id){
        User usr = userDAO.findById(id);

        userDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
