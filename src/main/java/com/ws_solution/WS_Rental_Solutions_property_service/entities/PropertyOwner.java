package com.ws_solution.WS_Rental_Solutions_property_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "TB_PROPERTIES_OWNERS")
public class PropertyOwner {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PropertyOwnerId", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PropertyId", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Property property;

    @Column(name = "PersonId", nullable = false)
    @NotNull(message = "ID da pessoa é obrigatório")
    private UUID personId;

    @Column(name = "PercentegeOwnership", nullable = false, precision = 5, scale = 2)
    @NotNull(message = "Percentual de propriedade é obrigatório")
    @DecimalMin(value = "0.00", message = "Percentual deve ser maior ou igual a 0")
    @DecimalMax(value = "100.00", message = "Percentual deve ser menor ou igual a 100")
    private BigDecimal percentageOwnership;

    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "CreatedBy", nullable = false, length = 100)
    @NotBlank(message = "Criado por é obrigatório")
    private String createdBy = "System";

    @Column(name = "StartDate", nullable = false)
    @NotNull(message = "Data de início é obrigatória")
    private LocalDateTime startDate;

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @Column(name = "IsActive")
    private Boolean isActive = true;
}