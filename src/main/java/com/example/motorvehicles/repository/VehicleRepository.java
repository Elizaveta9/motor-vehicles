package com.example.motorvehicles.repository;

import com.example.motorvehicles.model.Vehicle;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    static Specification<Vehicle> hasBrand(String brand) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("brand"), "%" + brand + "%"));
    }

    static Specification<Vehicle> hasModel(String model) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("model"), "%" + model + "%"));
    }

    static Specification<Vehicle> hasCategory(String category) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("category"), "%" + category + "%"));
    }

    static Specification<Vehicle> hasLicenseNumber(String licenseNumber) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("licenseNumber"), "%" + licenseNumber + "%"));
    }

    static Specification<Vehicle> hasReleaseYear(String releaseYear) {
        if (releaseYear == null || releaseYear.equals("")) {
            return null;
        }
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("releaseYear"), releaseYear));
    }

    List<Vehicle> findAll(Specification<Vehicle> specification);
}
