package com.vehicleauctionsystem.vehicle_service.mapper;

import com.vehicleauctionsystem.vehicle_service.dto.VehicleDto;
import com.vehicleauctionsystem.vehicle_service.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleDto toDto(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        return VehicleDto.builder()
                .id(vehicle.getId())
                .title(vehicle.getTitle())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .startingPrice(vehicle.getStartingPrice())
                .build();
    }

    public Vehicle toEntity(VehicleDto dto) {
        if (dto == null) {
            return null;
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setTitle(dto.getTitle());
        vehicle.setBrand(dto.getBrand());
        vehicle.setModel(dto.getModel());
        vehicle.setYear(dto.getYear());
        vehicle.setStartingPrice(dto.getStartingPrice());
        return vehicle;
    }
}
