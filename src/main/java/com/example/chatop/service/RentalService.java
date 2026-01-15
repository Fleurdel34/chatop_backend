package com.example.chatop.service;


import com.example.chatop.dto.RentalDTO;
import com.example.chatop.pojo.Rental;
import com.example.chatop.pojo.User;
import com.example.chatop.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



/**
 * Create class RentalService
 * Execute business processing
 * Use the property RentalRepository
 */

@AllArgsConstructor
@Service
public class RentalService {

    private RentalRepository rentalRepository;

    public void createRental(Rental rental){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        rental.setUser(user);

        LocalDateTime dateTime = LocalDateTime.now();
        rental.setCreated_date(dateTime);

        if(rental.getRentalId() == null){
            rentalRepository.save(rental);
        }
    }

    public List<RentalDTO> getRentalsAll(){
        return this.rentalRepository.findAll().stream()
                .map(rental -> new RentalDTO(
                rental.getRentalId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPicture(),
                rental.getDescription(),
                rental.getUser().getId(),
                rental.getCreated_date(),
                rental.getUpdated_date()
        )).toList();
    }

    public RentalDTO getRentalById(Long id){
         return rentalRepository.findRentalByRentalId(id)
                 .map(rental-> new RentalDTO(
                 rental.getRentalId(),
                 rental.getName(),
                 rental.getSurface(),
                 rental.getPrice(),
                 rental.getPicture(),
                 rental.getDescription(),
                 rental.getUser().getId(),
                 rental.getCreated_date(),
                 rental.getUpdated_date()
                 )).orElse(null);

    }


    public void updateRentalById(Long id, Rental rental) {

        LocalDateTime dateTime = LocalDateTime.now();
        Rental existRental = this.rentalRepository.findRentalByRentalId(id).orElse(null);

        if(existRental!= null){
            existRental.setName(rental.getName());
            existRental.setSurface(rental.getSurface());
            existRental.setPrice(rental.getPrice());
            existRental.setDescription(rental.getDescription());
            existRental.setUpdated_date(dateTime);
            this.rentalRepository.save(existRental);
        }

    }
}
