package com.example.traveliotripmanager.web;

import com.example.traveliotripmanager.dto.TripDto;
import com.example.traveliotripmanager.exception.EntityNotFoundException;
import com.example.traveliotripmanager.mapper.TripMapper;
import com.example.traveliotripmanager.model.Message;
import com.example.traveliotripmanager.model.Trip;
import com.example.traveliotripmanager.service.TripService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private TripMapper tripMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrip(@PathVariable long id) {
        try {
            return new ResponseEntity<>(tripMapper.map(tripService.getTrip(id)), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTrip(@RequestBody Trip trip) {
        return new ResponseEntity<>(tripMapper.map(tripService.createTrip(trip)), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateTrip(@RequestBody Trip trip) {
        try {
            return new ResponseEntity<>(tripMapper.map(tripService.updateTrip(trip)), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTrip(@PathVariable long id) {
        tripService.deleteTrip(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/message")
    public ResponseEntity<?> addMessage(@PathVariable long id, @RequestBody Message message) {
        try {
            Trip trip = tripService.addMessage(id, message);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/location")
    public ResponseEntity<?> addLocation(@PathVariable long id, @RequestBody String json) {
        try {
            Trip trip = tripService.addLocation(id, json);
            TripDto map = tripMapper.map(trip);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/route")
    public ResponseEntity<?> addRoute(@PathVariable long id, @RequestBody String json) {
        try {
            Trip trip = tripService.addRoute(id, json);
            return new ResponseEntity<>(tripMapper.map(trip), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/location/{locId}")
    public ResponseEntity<?> deleteLocation(@PathVariable long id, @PathVariable long locId) {
        tripService.deleteLocation(id, locId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/route/{rId}")
    public ResponseEntity<?> deleteRoute(@PathVariable long id, @PathVariable long rId) {
        tripService.deleteRoute(id, rId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
