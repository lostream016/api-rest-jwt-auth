package com.backend.api.Controller;

import com.backend.api.Config.JwtTokenUtil;
import com.backend.api.DAO.UserDAO;
import com.backend.api.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public Response<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userDAO.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return new Response<>(200, "success",new AuthToken(token, user.getUsername()));
    }
}
