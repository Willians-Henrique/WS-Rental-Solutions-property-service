package com.ws_solution.WS_Rental_Solutions_property_service.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "TB_PROPERTY_TYPES")
public class PropertyType {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PropertyTypeId", nullable = false)
    private UUID id;

    @Column(name = "Name", nullable = false, unique = true, length = 100)
    @NotBlank(message = "Nome do tipo de propriedade é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String name;

    @OneToMany(mappedBy = "propertyType", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Property> properties;
}
