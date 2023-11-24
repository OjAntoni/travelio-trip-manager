package com.example.traveliotripmanager.service;

import com.example.traveliotripmanager.dto.LocationDto;
import com.example.traveliotripmanager.dto.RouteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "map-service")
public interface MapService {
    @PostMapping("/api/location")
    LocationDto createLocation(@RequestBody String json);

    @GetMapping("/api/location/{id}")
    LocationDto getLocation(@PathVariable("id") long id);

    @PatchMapping("/api/location/{id}")
    LocationDto updateLocation(@PathVariable("id") long id, @RequestBody String json);

    @DeleteMapping("/api/location/{id}")
    void deleteLocation(@PathVariable("id") long id);

    @PostMapping("/api/route")
    RouteDto createRoute(@RequestBody String json);

    @GetMapping("/api/route/{id}")
    RouteDto getRoute(@PathVariable("id") long id);

    @PatchMapping("/api/route")
    RouteDto updateRoute(@RequestBody RouteDto dto);

    @DeleteMapping("/api/route/{id}")
    void deleteRoute(@PathVariable("id") long id);
}
