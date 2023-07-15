package com.example.motorvehicles.controller;

import com.example.motorvehicles.model.Vehicle;
import com.example.motorvehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String addVehiclePost(@ModelAttribute("vehicle") Vehicle vehicle) {
        repository.save(vehicle);
        return "redirect:/vehicles";
    }
}
