package com.example.motorvehicles.controller;

import com.example.motorvehicles.model.Vehicle;
import com.example.motorvehicles.repository.VehicleRepository;
import com.example.motorvehicles.repository.VehicleSpecification;
import com.example.motorvehicles.service.SearchParameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleRepository repository;

    @GetMapping
    public String vehiclesList(Model modelTH,
                               @RequestParam(defaultValue = "") String brand,
                               @RequestParam(defaultValue = "") String model,
                               @RequestParam(defaultValue = "") String category,
                               @RequestParam(defaultValue = "") String licenseNumber,
                               @RequestParam(defaultValue = "") String releaseYear) {

        VehicleSpecification brandSpec = new VehicleSpecification(new SearchParameter("brand", brand));
        VehicleSpecification modelSpec = new VehicleSpecification(new SearchParameter("model", model));
        VehicleSpecification categorySpec = new VehicleSpecification(new SearchParameter("category", category));
        VehicleSpecification licenseNumberSpec = new VehicleSpecification(new SearchParameter("licenseNumber", licenseNumber));
        VehicleSpecification releaseYearSpec = new VehicleSpecification(new SearchParameter("releaseYear", releaseYear));

        Iterable<Vehicle> vehicles;
        vehicles = repository.findAll(Specification.where(brandSpec).and(modelSpec).and(categorySpec).and(licenseNumberSpec).and(releaseYearSpec));
        modelTH.addAttribute("vehicleList", vehicles);
        return "vehicles-list";
    }

    @GetMapping("/add")
    public String addVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle-add";
    }

    @PostMapping("/add")
    public String addVehiclePost(Model model, @ModelAttribute("vehicle") @Valid Vehicle vehicle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vehicle-add";
        }
        try {
            repository.save(vehicle);
        } catch (Exception e){
            model.addAttribute("uniqueLicenseNumberError", true);
            return "vehicle-add";
        }

        return "redirect:/vehicles";
    }

    @GetMapping("/{id}/edit")
    public String editVehicle(Model model, @PathVariable Long id) {
        model.addAttribute("vehicleToEdit", repository.findById(id));
        return "vehicle-edit";
    }

    @PostMapping
    public String editVehiclePatch(Model model, @ModelAttribute("vehicleToEdit") @Valid Vehicle vehicle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vehicle-edit";
        }
        try {
            repository.save(vehicle);
        } catch (Exception e){
            model.addAttribute("uniqueLicenseNumberError", true);
            return "vehicle-edit";
        }
        return "redirect:/vehicles";
    }
}
