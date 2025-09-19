package com.ws_solution.WS_Rental_Solutions_property_service.repositories;

import com.ws_solution.WS_Rental_Solutions_property_service.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {
}
