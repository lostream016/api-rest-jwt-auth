package com.backend.api.DAO;

import com.backend.api.Exceptions.ResourceNotFoundException;
import com.backend.api.Model.User;
import com.backend.api.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service(value = "userDAO")
public class UserDAO implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    /* Save User
    public User save(User usr){
        return userRepository.save(usr);
    }*/

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

    public User findOne(String username){
        return userRepository.findByUsername(username);
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }


    public User save(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        newUser.setFk_city(user.getFk_city());
        return userRepository.save(newUser);
    }

    public User update(User userparam) {
        User user = findById(userparam.getId());
        if(user != null) {
            BeanUtils.copyProperties(userparam, user, "password");
            userRepository.save(user);
        }
        return userparam;
    }
}
