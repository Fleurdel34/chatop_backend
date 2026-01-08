package com.example.chatop.service;

import com.example.chatop.enumeration.TypeRole;
import com.example.chatop.pojo.Role;
import com.example.chatop.pojo.User;
import com.example.chatop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    public void registrer (User user){

        if(!user.getUsername().contains("@")){
            throw new RuntimeException("Votre mail est invalide");
        }

        if(!user.getUsername().contains(".")){
            throw new RuntimeException("Votre mail est invalide");
        }

        Optional<User> userOptional = this.userRepository.findByUsername(user.getUsername());

        if(userOptional.isPresent()){
            throw new RuntimeException("Votre mail est déjà utilisé");
        }

        String cryptPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(cryptPassword);

        Role roleUser = new Role();
        roleUser.setRole(TypeRole.USER);
        user.setRole(roleUser);

        this.userRepository.save(user);

    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
