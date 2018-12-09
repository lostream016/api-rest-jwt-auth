package com.backend.api.DAO;

import com.backend.api.Exceptions.ResourceNotFoundException;
import com.backend.api.Model.User;
import com.backend.api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAO {
    @Autowired
    UserRepository userRepository;

    /* Save User*/
    public User save(User usr){
        return userRepository.save(usr);
    }

    /* Search all users*/
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        User temp = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID", "id", id));

        return temp;
    }

    public void deleteById(Long id){
        User temp = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID", "id", id));

        userRepository.deleteById(id);
    }
}
