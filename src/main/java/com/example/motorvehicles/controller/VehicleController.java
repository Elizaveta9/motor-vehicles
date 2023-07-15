package com.example.motorvehicles.controller;

import com.example.motorvehicles.model.Vehicle;
import com.example.motorvehicles.repository.VehicleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleRepository repository;

    @GetMapping
    public String vehiclesList(Model model) {
        model.addAttribute("vehicleList", repository.findAll());
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
