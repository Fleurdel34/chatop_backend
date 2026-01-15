package com.example.chatop.service;

import com.example.chatop.dto.AuthMeDTO;
import com.example.chatop.dto.OwnerDTO;
import com.example.chatop.enumeration.TypeRole;
import com.example.chatop.exception.RequestException;
import com.example.chatop.pojo.Rental;
import com.example.chatop.pojo.Role;
import com.example.chatop.pojo.User;
import com.example.chatop.repository.RentalRepository;
import com.example.chatop.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private RentalRepository rentalRepository;


    @SneakyThrows
    public void register (User user)  {

        if(!user.getEmail().contains("@") || !user.getEmail().contains(".")){
            throw new RequestException(RequestException.ErrorRequest.BAD_REQUEST_EXCEPTION,"invalid email");
        }

        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());

        if(userOptional.isPresent()){
            throw new RequestException(RequestException.ErrorRequest.BAD_REQUEST_EXCEPTION,"mail is already in use");
        }

        LocalDateTime dateTime = LocalDateTime.now();
        user.setCreated_date(dateTime);

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
                .orElseThrow(() -> new RequestException(RequestException.ErrorRequest.UNAUTHORIZED_EXCEPTION,"Error: Authentification failed"));
    }

    public AuthMeDTO authMe(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new AuthMeDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreated_date(),
                user.getUpdated_date()
        );
    }

    public OwnerDTO getUserById(Long id){
        Optional<Rental> rentalList = this.rentalRepository.findAllRentalByUser_Id(id)
                .stream().findFirst();

        if(rentalList.isPresent()){
            Long ownerId = rentalList.get().getUser().getId();
            User user = this.userRepository.findById(ownerId).orElse(null);
            assert user != null;
            return new OwnerDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreated_date(),
                    user.getUpdated_date()
            );
        };
        return null;
    }
}
