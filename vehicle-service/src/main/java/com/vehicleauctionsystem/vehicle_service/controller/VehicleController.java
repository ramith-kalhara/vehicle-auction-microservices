package com.vehicleauctionsystem.vehicle_service.controller;

import com.vehicleauctionsystem.vehicle_service.dto.VehicleDto;
import com.vehicleauctionsystem.vehicle_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto) {
        VehicleDto savedVehicle = vehicleService.createVehicle(vehicleDto);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable Long id) {
        VehicleDto vehicleDto = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicleDto);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        VehicleDto updatedVehicle = vehicleService.updateVehicle(id, vehicleDto);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully!");
    }
}
