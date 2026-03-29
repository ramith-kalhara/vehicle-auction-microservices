package com.vehicleauctionsystem.vehicle_service.service;

import com.vehicleauctionsystem.vehicle_service.dto.VehicleDto;
import java.util.List;

public interface VehicleService {
    VehicleDto createVehicle(VehicleDto vehicleDto);
    VehicleDto getVehicleById(Long id);
    List<VehicleDto> getAllVehicles();
    VehicleDto updateVehicle(Long id, VehicleDto vehicleDto);
    void deleteVehicle(Long id);
}
