package com.ws_solution.WS_Rental_Solutions_property_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PropertyOwnerDTO {

    private String id;
    private String propertyId;
    private String personId;
    private BigDecimal percentageOwnership;
    private LocalDateTime startDate;


}
