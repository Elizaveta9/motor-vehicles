package com.example.motorvehicles.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Впишите марку")
    private String brand;
    @NotBlank(message = "Впишите модель")
    private String model;
    @NotBlank(message = "Впишите категорию")
    private String category;
    @NotBlank(message = "Впишите государственный номер")
    @Pattern(regexp = "[а-яА-Я]\\d\\d\\d[а-яА-Я]{2}", message = "Государственный номер записан некорректно")
    @Column(unique = true)
    private String licenseNumber;
    @NotBlank(message = "Впишите тип ТС")
    private String type;
    @NotNull(message = "Впишите год выпуска")
    private Integer releaseYear;
    private Boolean hasTrailer;
}
