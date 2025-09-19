package com.ws_solution.WS_Rental_Solutions_property_service.services;


import com.ws_solution.WS_Rental_Solutions_property_service.dto.PropertyDTO;
import com.ws_solution.WS_Rental_Solutions_property_service.dto.PropertyOwnerDTO;
import com.ws_solution.WS_Rental_Solutions_property_service.entities.Property;
import com.ws_solution.WS_Rental_Solutions_property_service.entities.PropertyOwner;
import com.ws_solution.WS_Rental_Solutions_property_service.repositories.PropertyOwnerRepository;
import com.ws_solution.WS_Rental_Solutions_property_service.repositories.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PropertyOwnerService {

    public final PropertyOwnerRepository propertyOwnerRepository;
    public final PropertyRepository propertyRepository;

    public PropertyOwner createPropertyOwner(PropertyOwnerDTO dto) {

        PropertyOwner propertyOwner = new PropertyOwner();

        if (dto.getPropertyId() != null) {
            Property propertyId = propertyRepository.findById(UUID.fromString(dto.getPropertyId()))
                    .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));
            propertyOwner.setProperty(propertyId);
        }
        else {
            throw new RuntimeException("ID imóvel é obrigatório");
        }

        propertyOwner.setPersonId(UUID.fromString(dto.getPersonId()));
        propertyOwner.setPercentageOwnership(dto.getPercentageOwnership());
        propertyOwner.setStartDate(dto.getStartDate());

        return propertyOwnerRepository.save(propertyOwner);
    }

    public PropertyOwner updatePropertyOwner(UUID id, PropertyOwnerDTO dto) {

        // Buscar o PropertyOwner existente
        PropertyOwner propertyOwner = propertyOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietário do imóvel não encontrado"));

        // Atualizar o Property se fornecido
        if (dto.getPropertyId() != null) {
            Property property = propertyRepository.findById(UUID.fromString(dto.getPropertyId()))
                    .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));
            propertyOwner.setProperty(property);
        }

        // Atualizar o PersonId se fornecido
        if (dto.getPersonId() != null) {
            propertyOwner.setPersonId(UUID.fromString(dto.getPersonId()));
        }

        // Atualizar os outros campos
        if (dto.getPercentageOwnership() != null) {
            propertyOwner.setPercentageOwnership(dto.getPercentageOwnership());
        }

        if (dto.getStartDate() != null) {
            propertyOwner.setStartDate(dto.getStartDate());
        }

        return propertyOwnerRepository.save(propertyOwner);
    }


    public void delete (UUID id) {
        PropertyOwner propertyOwner = propertyOwnerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Proprietário não encontrato"));
        propertyOwnerRepository.delete(propertyOwner);
    }

    public List<PropertyOwnerDTO> findAll() {
        List<PropertyOwner> propertyOwners = propertyOwnerRepository.findAll();

        return propertyOwners.stream().map(propertyOwner -> {
            PropertyOwnerDTO dto = new PropertyOwnerDTO();
            dto.setId(propertyOwner.getId().toString());
            dto.setPercentageOwnership(propertyOwner.getPercentageOwnership());
            dto.setStartDate(propertyOwner.getStartDate());

            if (propertyOwner.getProperty() != null) {
                dto.setPropertyId(propertyOwner.getProperty().getId().toString());
            }
            else {
                dto.setPropertyId(null);
            }

            if (propertyOwner.getPersonId() != null) {
                dto.setPersonId(propertyOwner.getPersonId().toString());
            }
            else {
                dto.setPropertyId(null);
            }


            return dto;
        }).collect(Collectors.toList());

    }

    public PropertyOwnerDTO findById(UUID id) {
        PropertyOwner propertyOwner = propertyOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propriedade não encontrada"));

        PropertyOwnerDTO dto = new PropertyOwnerDTO();
        dto.setId(propertyOwner.getId().toString());
        dto.setId(propertyOwner.getId().toString());
        dto.setPercentageOwnership(propertyOwner.getPercentageOwnership());
        dto.setStartDate(propertyOwner.getStartDate());

        if (propertyOwner.getProperty() != null) {
            dto.setPropertyId(propertyOwner.getProperty().getId().toString());
        }
        else {
            dto.setPropertyId(null);
        }

        if (propertyOwner.getPersonId() != null) {
            dto.setPersonId(propertyOwner.getPersonId().toString());
        }
        else {
            dto.setPropertyId(null);
        }

        return  dto;
    }


}
