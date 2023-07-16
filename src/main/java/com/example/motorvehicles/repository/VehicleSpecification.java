package com.example.motorvehicles.repository;

import com.example.motorvehicles.model.Vehicle;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VehicleSpecification {

    private List<Predicate> predicates = new ArrayList<>();

    public Specification<Vehicle> getSpecification(Map<String, String> parameters) {
        return (root, query, criteriaBuilder) -> {
            parameters.keySet().forEach(key -> {
                        if (!key.equals("releaseYear")) {
                            predicates.add(criteriaBuilder.like(root.get(key), "%" + parameters.get(key) + "%"));
                        } else {
                            predicates.add(criteriaBuilder.equal(root.get(key), parameters.get(key)));
                        }
                    }
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
