package com.example.motorvehicles.controller;

import com.example.motorvehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("vehicleList", repository.findAll());
        return "vehicles-list";
    }
}
