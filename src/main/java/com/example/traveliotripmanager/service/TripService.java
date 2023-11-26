package com.example.traveliotripmanager.service;

import com.example.traveliotripmanager.dto.LocationDto;
import com.example.traveliotripmanager.dto.RouteDto;
import com.example.traveliotripmanager.exception.EntityNotFoundException;
import com.example.traveliotripmanager.model.Message;
import com.example.traveliotripmanager.model.Trip;
import com.example.traveliotripmanager.repository.MessageRepository;
import com.example.traveliotripmanager.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MapService mapService;

    public Trip createTrip(Trip trip){
        return tripRepository.save(trip);
    }

    @Transactional
    public Trip updateTrip(Trip newTrip) throws EntityNotFoundException {
        if(tripRepository.existsById(newTrip.getId())){
            List<Message> messages = messageRepository.saveAll(newTrip.getMessages());
            newTrip.setMessages(messages);
            Trip save = tripRepository.save(newTrip);
            System.out.println(save);
            return save;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Trip getTrip(long id) throws EntityNotFoundException {
        return tripRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteTrip(long tripId){
        Trip trip = tripRepository.findById(tripId).orElse(null);
        if(trip == null) return;
        trip.getPlacesId().forEach(id -> mapService.deleteLocation(id));
        trip.getRoutesId().forEach(id->mapService.deleteRoute(id));
        tripRepository.deleteById(tripId);
    }

    @Transactional
    public Trip addMessage(long tripId, Message message) throws EntityNotFoundException {
        Trip trip = tripRepository.findById(tripId).orElseThrow(EntityNotFoundException::new);
        trip.getMessages().add(message);
        return trip;
    }

    @Transactional
    public Trip addLocation(long id, String pointWkt) throws EntityNotFoundException {
        Trip trip = tripRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        LocationDto location = mapService.createLocation(pointWkt);
        trip.getPlacesId().add(location.id);
        return trip;
    }

    @Transactional
    public Trip addRoute(long id, String json) throws EntityNotFoundException {
        Trip trip = tripRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        RouteDto route = mapService.createRoute(json);
        trip.getRoutesId().add(route.getId());
        return trip;
    }

    @Transactional
    public void deleteLocation(long tripId, long locId) {
        Trip trip = tripRepository.findById(tripId).orElse(null);
        if(trip==null) return;
        mapService.deleteLocation(locId);
        trip.setPlacesId(trip.getPlacesId().stream().filter(id -> id != locId).toList());
    }

    @Transactional
    public void deleteRoute(long tripId, long rId) {
        Trip trip = tripRepository.findById(tripId).orElse(null);
        if(trip==null) return;
        mapService.deleteRoute(rId);
        trip.setRoutesId(trip.getRoutesId().stream().filter(id -> id != rId).toList());
    }
}
