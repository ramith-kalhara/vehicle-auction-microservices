package com.vehicleauctionsystem.vehicle_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicleauctionsystem.vehicle_service.dto.VehicleDto;
import com.vehicleauctionsystem.vehicle_service.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateVehicle() throws Exception {
        VehicleDto vehicleDto = VehicleDto.builder()
                .title("Toyota Camry 2024")
                .brand("Toyota")
                .model("Camry")
                .year(2024)
                .startingPrice(30000.0)
                .build();

        when(vehicleService.createVehicle(any(VehicleDto.class))).thenReturn(vehicleDto);

        mockMvc.perform(post("/api/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Toyota Camry 2024"))
                .andExpect(jsonPath("$.brand").value("Toyota"));
    }
}
