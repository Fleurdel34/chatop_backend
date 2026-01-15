package com.example.chatop.service;

import com.example.chatop.exception.RequestException;
import com.example.chatop.pojo.Message;
import com.example.chatop.pojo.Rental;
import com.example.chatop.pojo.User;
import com.example.chatop.repository.MessageRepository;
import com.example.chatop.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 * Create class MessageService
 * Execute create processing
 * Use the property MessageRepository
 */

@AllArgsConstructor
@Service
public class MessageService {

    MessageRepository messageRepository;
    RentalRepository rentalRepository;

    public void createMessage(Message message) throws RequestException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setUser(user);

       Long user_id= user.getId();
       Rental rental = this.rentalRepository.findRentalByUser_Id(user_id).orElse(null);
       message.setRental(rental);

       if (message.getMessage() == null){
           throw new RequestException(RequestException.ErrorRequest.BAD_REQUEST_EXCEPTION, "Message not null");
       }

       this.messageRepository.save(message);
    }

}
