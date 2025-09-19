package com.ws_solution.WS_Rental_Solutions_property_service.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "TB_PROPERTIES")
public class Property {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PropertyId", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PropertyTypeId", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private PropertyType propertyType;

    @Column(name = "Street", nullable = false, length = 100)
    @NotBlank(message = "Logradouro é obrigatório")
    @Size(max = 100, message = "Logradouro deve ter no máximo 100 caracteres")
    private String street;

    @Column(name = "Number", nullable = false, length = 20)
    @NotBlank(message = "Número é obrigatório")
    @Size(max = 20, message = "Número deve ter no máximo 20 caracteres")
    private String number;

    @Column(name = "AdditionalData", length = 255)
    @Size(max = 255, message = "Complemento devem ter no máximo 255 caracteres")
    private String additionalData;

    @Column(name = "Neighborhood", nullable = false, length = 100)
    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 100, message = "Bairro deve ter no máximo 100 caracteres")
    private String neighborhood;

    @Column(name = "City", nullable = false, length = 100)
    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
    private String city;

    @Column(name = "State", nullable = false, length = 2)
    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
    private String state;

    @Column(name = "Country", nullable = false, length = 50)
    @NotBlank(message = "País é obrigatório")
    @Size(max = 50, message = "País deve ter no máximo 50 caracteres")
    private String country;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<PropertyOwner> propertyOwners;


}
