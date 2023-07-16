package com.example.motorvehicles.repository;

import com.example.motorvehicles.model.Vehicle;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    List<Vehicle> findAll(Specification<Vehicle> specification);
}
