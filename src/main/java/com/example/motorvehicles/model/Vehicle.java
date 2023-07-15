package com.example.motorvehicles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

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
    @NotBlank(message = "Впишите государсвенный номер")
    @Pattern(regexp = "[а-яА-Я]{2}\\d\\d\\d[а-яА-Я]", message = "Государственный номер записан некорректно")
    private String licenseNumber;
    @NotBlank(message = "Впишите тип транспортного средства")
    private String type;
    @NotNull(message = "Впишите год выпуска")
    private Integer releaseYear;
    private Boolean hasTrailer;
}
