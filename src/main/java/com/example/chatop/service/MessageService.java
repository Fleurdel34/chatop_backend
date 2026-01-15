package com.example.chatop.service;

import com.example.chatop.exception.RequestException;
import com.example.chatop.pojo.Message;
import com.example.chatop.repository.MessageRepository;
import com.example.chatop.repository.RentalRepository;
import lombok.AllArgsConstructor;
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

        if (message.getMessage() == null || message.getRental_id() == null || message.getUser_id() == null){
           throw new RequestException(RequestException.ErrorRequest.BAD_REQUEST_EXCEPTION, "Parameter not null");
        }
       this.messageRepository.save(message);
    }

}
