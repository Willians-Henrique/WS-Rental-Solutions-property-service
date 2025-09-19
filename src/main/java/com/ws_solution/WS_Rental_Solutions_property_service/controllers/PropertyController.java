package com.ws_solution.WS_Rental_Solutions_property_service.controllers;


import com.ws_solution.WS_Rental_Solutions_property_service.dto.PropertyDTO;
import com.ws_solution.WS_Rental_Solutions_property_service.entities.Property;
import com.ws_solution.WS_Rental_Solutions_property_service.repositories.PropertyRepository;
import com.ws_solution.WS_Rental_Solutions_property_service.services.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/property")
public class PropertyController {

    public final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<?> createProperty(@RequestBody PropertyDTO dto) {
        Property property = propertyService.createProperty(dto);
        return ResponseEntity.ok(property);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable UUID id, @RequestBody PropertyDTO propertyDTO) {
        Property updatedProperty = propertyService.updateProperty(id, propertyDTO);
        return ResponseEntity.ok(updatedProperty);
    }

    @GetMapping("/{id}")
    public PropertyDTO findPropertyById (@PathVariable UUID id) {
        return propertyService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable UUID id) {
        propertyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?>  findAllProperty() {
        return ResponseEntity.ok(propertyService.findAll());
    }
}
