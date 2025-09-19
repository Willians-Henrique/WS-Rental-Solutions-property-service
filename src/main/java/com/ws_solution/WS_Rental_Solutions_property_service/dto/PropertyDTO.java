package com.ws_solution.WS_Rental_Solutions_property_service.dto;

import lombok.Data;

@Data
public class PropertyDTO {
    private String id;
    private String propertyTypeId; // ID do tipo de propriedade
    private String street;
    private String number;
    private String additionalData;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
}
