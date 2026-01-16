package com.example.chatop.service;


import com.example.chatop.dto.RentalDTO;
import com.example.chatop.pojo.Rental;
import com.example.chatop.pojo.User;
import com.example.chatop.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;




import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

        Date dateTime = new Date();
        rental.setCreated_at(dateTime);

        if(rental.getId() == null){

            rentalRepository.save(rental);
        }
    }

    public Map<String, Object> getRentalsAll(){

        List<RentalDTO> rentals = this.rentalRepository.findAll().stream()
                .map(rental -> new RentalDTO(
                        rental.getId(),
                        rental.getName(),
                        rental.getSurface(),
                        rental.getPrice(),
                        rental.getPicture(),
                        rental.getDescription(),
                        rental.getUser().getId(),
                        rental.getCreated_at(),
                        rental.getUpdated_at() )).toList();
        Map<String, Object> response = new HashMap<>();
        response.put("rentals", rentals);
        return response;

    }

    public RentalDTO getRentalById(Long id){
         return rentalRepository.findRentalById(id)
                 .map(rental-> new RentalDTO(
                 rental.getId(),
                 rental.getName(),
                 rental.getSurface(),
                 rental.getPrice(),
                 rental.getPicture(),
                 rental.getDescription(),
                 rental.getUser().getId(),
                 rental.getCreated_at(),
                 rental.getUpdated_at()
                 )).orElse(null);

    }


    public void updateRentalById(Long id, Rental rental) {

        Date dateTime = new Date();
        Rental existRental = this.rentalRepository.findRentalById(id).orElse(null);

        if(existRental!= null){
            existRental.setName(rental.getName());
            existRental.setSurface(rental.getSurface());
            existRental.setPrice(rental.getPrice());
            existRental.setDescription(rental.getDescription());
            existRental.setUpdated_at(dateTime);
            this.rentalRepository.save(existRental);
        }
    }

}
