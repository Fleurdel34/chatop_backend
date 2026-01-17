package com.example.chatop.repository;

import com.example.chatop.pojo.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Build RentalRepository
 * extends CrudRepository from tools Jpa
 * Use method findAll, findAllRentalByUser_Id and findRentalById
 * @Params Long id
 */

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
      List<Rental> findAll();

      Optional<Rental> findRentalById(Long id);

      List <Rental> findAllRentalByUser_Id(Long id);
}