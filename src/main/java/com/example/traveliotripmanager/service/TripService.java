package com.example.traveliotripmanager.service;

import com.example.traveliotripmanager.exception.EntityNotFoundException;
import com.example.traveliotripmanager.model.Message;
import com.example.traveliotripmanager.model.Trip;
import com.example.traveliotripmanager.repository.MessageRepository;
import com.example.traveliotripmanager.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private MessageRepository messageRepository;

    public Trip createTrip(Trip trip){
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Trip newTrip) throws EntityNotFoundException {
        if(tripRepository.existsById(newTrip.getId())){
            return tripRepository.save(newTrip);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Trip getTrip(long id) throws EntityNotFoundException {
        return tripRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteTrip(long id){
        tripRepository.deleteById(id);
    }

    @Transactional
    public Trip addMessage(long tripId, Message message) throws EntityNotFoundException {
        Trip trip = tripRepository.findById(tripId).orElseThrow(EntityNotFoundException::new);
        trip.getMessages().add(message);
        return trip;
    }

}
