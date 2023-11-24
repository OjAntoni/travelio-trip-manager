package com.example.traveliotripmanager.dto;

import com.example.traveliotripmanager.model.Message;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TripDto {
    private long id;
    private long organizerId;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<Message> messages;
    private List<LocationDto> locations;
    private List<RouteDto> routes;
}
