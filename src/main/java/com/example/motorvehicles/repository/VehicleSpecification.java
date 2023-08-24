package com.example.motorvehicles.repository;

import com.example.motorvehicles.model.Vehicle;
import com.example.motorvehicles.service.SearchParameter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class VehicleSpecification implements Specification<Vehicle> {

    private SearchParameter searchParameter;

    @Override
    public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchParameter.getValue().equals("")) {
            return null;
        }
        if (searchParameter.getKey().equals("releaseYear")){
            return criteriaBuilder.equal(root.get(searchParameter.getKey()), searchParameter.getValue());
        }
        if (searchParameter.getValue() != null)
            return criteriaBuilder.like(root.get(searchParameter.getKey()), "%" + searchParameter.getValue() + "%");
        return null;
    }
}
