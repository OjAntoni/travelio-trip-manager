package com.example.traveliotripmanager.mapper;

import com.example.traveliotripmanager.dto.LocationDto;
import com.example.traveliotripmanager.dto.RouteDto;
import com.example.traveliotripmanager.dto.TripDto;
import com.example.traveliotripmanager.model.Trip;
import com.example.traveliotripmanager.service.MapService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class TripMapper {
    @Autowired
    private MapService mapService;
    public TripDto map(Trip trip){
        return TripDto.builder()
                .id(trip.getId())
                .start(trip.getStart())
                .end(trip.getEnd())
                .name(trip.getName())
                .organizerId(trip.getOrganizerId())
                .locations(retrieveLocations(trip))
                .routes(retrieveRoutes(trip))
                .messages(trip.getMessages())
                .build();
    }

    private List<LocationDto> retrieveLocations(Trip trip){
        if(trip.getPlacesId()==null) return Collections.emptyList();
        return trip.getPlacesId().stream().map(id -> mapService.getLocation(id)).toList();
    }

    private List<RouteDto> retrieveRoutes(Trip trip){
        if(trip.getRoutesId()==null) return Collections.emptyList();
        return trip.getRoutesId().stream().map(id -> mapService.getRoute(id)).toList();
    }
}
