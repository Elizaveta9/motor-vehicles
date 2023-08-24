package com.example.motorvehicles.controller;

import com.example.motorvehicles.model.Vehicle;
import com.example.motorvehicles.repository.VehicleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.example.motorvehicles.repository.VehicleRepository.*;

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

        Iterable<Vehicle> vehicles;
        vehicles = repository.findAll(
                hasBrand(brand)
                        .and(hasModel(model))
                        .and(hasCategory(category)
                                .and(hasLicenseNumber(licenseNumber)
                                        .and(hasReleaseYear(releaseYear)))));
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
