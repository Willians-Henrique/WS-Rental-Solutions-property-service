package com.ws_solution.WS_Rental_Solutions_property_service.services;

import com.ws_solution.WS_Rental_Solutions_property_service.dto.PropertyDTO;
import com.ws_solution.WS_Rental_Solutions_property_service.entities.Property;
import com.ws_solution.WS_Rental_Solutions_property_service.entities.PropertyType;
import com.ws_solution.WS_Rental_Solutions_property_service.repositories.PropertyRepository;
import com.ws_solution.WS_Rental_Solutions_property_service.repositories.PropertyTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PropertyService {

    public final PropertyRepository propertyRepository;
    private final PropertyTypeRepository propertyTypeRepository;

    public Property createProperty(PropertyDTO dto) {
        Property property = new Property();

        // Buscar o PropertyType pelo ID e associar à propriedade
        if (dto.getPropertyTypeId() != null) {
            PropertyType propertyType = propertyTypeRepository.findById(UUID.fromString(dto.getPropertyTypeId()))
                    .orElseThrow(() -> new RuntimeException("Tipo de propriedade não encontrado"));
            property.setPropertyType(propertyType);
        } else {
            throw new RuntimeException("ID do tipo de propriedade é obrigatório");
        }

        // Mapear os outros campos do DTO para a entidade
        property.setStreet(dto.getStreet());
        property.setNumber(dto.getNumber());
        property.setAdditionalData(dto.getAdditionalData());
        property.setNeighborhood(dto.getNeighborhood());
        property.setCity(dto.getCity());
        property.setState(dto.getState());
        property.setCountry(dto.getCountry());

        return propertyRepository.save(property);
    }

    public Property updateProperty(UUID id, PropertyDTO dto) {
        // Buscar a propriedade existente
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propriedade não encontrada"));

        // Atualizar o PropertyType se fornecido
        if (dto.getPropertyTypeId() != null) {
            PropertyType propertyType = propertyTypeRepository.findById(UUID.fromString(dto.getPropertyTypeId()))
                    .orElseThrow(() -> new RuntimeException("Tipo de propriedade não encontrado"));
            property.setPropertyType(propertyType);
        }

        // Atualizar os outros campos
        property.setStreet(dto.getStreet());
        property.setNumber(dto.getNumber());
        property.setAdditionalData(dto.getAdditionalData());
        property.setNeighborhood(dto.getNeighborhood());
        property.setCity(dto.getCity());
        property.setState(dto.getState());
        property.setCountry(dto.getCountry());

        return propertyRepository.save(property);
    }


    public PropertyDTO findById(UUID id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propriedade não encontrada"));

        PropertyDTO dto = new PropertyDTO();
        dto.setId(property.getId().toString());
        dto.setStreet(property.getStreet());
        dto.setNumber(property.getNumber());
        dto.setAdditionalData(property.getAdditionalData());
        dto.setNeighborhood(property.getNeighborhood());
        dto.setCity(property.getCity());
        dto.setState(property.getState());
        dto.setCountry(property.getCountry());

        if (property.getPropertyType() != null) {
            dto.setPropertyTypeId(property.getPropertyType().getId().toString());
        } else {
            dto.setPropertyTypeId(null);
        }

    return  dto;
    }

    public void delete (UUID id) {
        Property property = propertyRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Imóvel não encontrato"));
        propertyRepository.delete(property);
    }

    public List<PropertyDTO> findAll() {
        List<Property> properties = propertyRepository.findAll();

        return properties.stream().map(property -> {
            PropertyDTO dto = new PropertyDTO();
            dto.setId(property.getId().toString());
            dto.setStreet(property.getStreet());
            dto.setNumber(property.getNumber());
            dto.setAdditionalData(property.getAdditionalData());
            dto.setNeighborhood(property.getNeighborhood());
            dto.setCity(property.getCity());
            dto.setState(property.getState());
            dto.setCountry(property.getCountry());

            if (property.getPropertyType() != null) {
                dto.setPropertyTypeId(property.getPropertyType().getId().toString());
            }
            else {
                dto.setPropertyTypeId(null);
            }
            return dto;
        }).collect(Collectors.toList());

    }

}
