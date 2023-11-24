package com.example.traveliotripmanager.repository;

import com.example.traveliotripmanager.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
    boolean existsById(long id);
}
