package com.vehicleauctionsystem.vehicle_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {
    private Long id;
    private String title;
    private String brand;
    private String model;
    private int year;
    private double startingPrice;
}
