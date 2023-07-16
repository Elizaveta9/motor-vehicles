package com.example.motorvehicles.controller;

import com.example.motorvehicles.model.Vehicle;
import com.example.motorvehicles.repository.VehicleRepository;
import com.example.motorvehicles.repository.VehicleSpecification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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
        Map<String, String> searchParameters = new HashMap<>();
        if (!brand.isEmpty()) searchParameters.put("brand", brand);
        if (!model.isEmpty()) searchParameters.put("model", model);
        if (!category.isEmpty()) searchParameters.put("category", category);
        if (!licenseNumber.isEmpty()) searchParameters.put("licenseNumber", licenseNumber);
        if (!releaseYear.isEmpty()) searchParameters.put("releaseYear", releaseYear);

        Iterable<Vehicle> vehicles;
        if (!searchParameters.isEmpty()) {
            vehicles = repository.findAll(new VehicleSpecification().getSpecification(searchParameters));
        } else {
            vehicles = repository.findAll();
        }
        modelTH.addAttribute("vehicleList", vehicles);
        return "vehicles-list";
    }

    @GetMapping("/add")
    public String addVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle-add";
    }

    @PostMapping("/add")
    public String addVehiclePost(@ModelAttribute("vehicle") @Valid Vehicle vehicle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vehicle-add";
        }
        repository.save(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/{id}/edit")
    public String editVehicle(Model model, @PathVariable Long id) {
        model.addAttribute("vehicleToEdit", repository.findById(id));
        return "vehicle-edit";
    }

    @PostMapping
    public String editVehiclePatch(@ModelAttribute("vehicleToEdit") @Valid Vehicle vehicle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "vehicle-edit";
        }
        repository.save(vehicle);
        return "redirect:/vehicles";
    }
}
