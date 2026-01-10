package com.example.chatop.service;

import com.example.chatop.enumeration.TypeRole;
import com.example.chatop.exception.RequestException;
import com.example.chatop.pojo.Role;
import com.example.chatop.pojo.User;
import com.example.chatop.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Create class UserService
 * Execute business processing
 * Use the property UserRepository
 */
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;



    @SneakyThrows
    public void register (User user)  {

        if(!user.getEmail().contains("@") || !user.getEmail().contains(".")){
            throw new RequestException(RequestException.ErrorRequest.BAD_REQUEST_EXCEPTION,"Votre mail est invalide");
        }

        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());

        if(userOptional.isPresent()){
            throw new RequestException(RequestException.ErrorRequest.BAD_REQUEST_EXCEPTION,"Votre mail est déjà utilisé");
        }

        String cryptPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(cryptPassword);

        Role roleUser = new Role();
        roleUser.setRole(TypeRole.USER);
        user.setRole(roleUser);

        this.userRepository.save(user);

    }


    @SneakyThrows
    @Override
    public User loadUserByUsername(String email) {
        return this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new RequestException(RequestException.ErrorRequest.UNAUTHORIZED_EXCEPTION,"Error: Authentification échouée"));
    }
}
