package com.ws_solution.WS_Rental_Solutions_property_service.controllers;

import com.ws_solution.WS_Rental_Solutions_property_service.dto.PropertyOwnerDTO;
import com.ws_solution.WS_Rental_Solutions_property_service.entities.PropertyOwner;
import com.ws_solution.WS_Rental_Solutions_property_service.services.PropertyOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/propertyowner")
public class PropertyOwnerController {

    private final PropertyOwnerService propertyOwnerService;

    @PostMapping
    public ResponseEntity<?> createPropertyOwner(@RequestBody PropertyOwnerDTO dto) {
        PropertyOwner propertyOwner = propertyOwnerService.createPropertyOwner(dto);
        return ResponseEntity.ok(propertyOwner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyOwner> updatePropertyOwner(@PathVariable UUID id, @RequestBody PropertyOwnerDTO propertyOwnerDTO) {
        PropertyOwner updatedPropertyOwner = propertyOwnerService.updatePropertyOwner(id, propertyOwnerDTO);
        return ResponseEntity.ok(updatedPropertyOwner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePropertyOwner(@PathVariable UUID id) {
        propertyOwnerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public PropertyOwnerDTO findPropertyOwnerById (@PathVariable UUID id) {
        return propertyOwnerService.findById(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAllPropertyOwners() {
        return ResponseEntity.ok(propertyOwnerService.findAll());
    }
}
