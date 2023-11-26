package com.example.traveliotripmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long organizerId;
    private String name;
    private LocalDateTime start;
    @Column(name="\"end\"")
    private LocalDateTime end;
    @OneToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Message> messages;
    @ElementCollection
    private List<Long> placesId;
    @ElementCollection
    private List<Long> routesId;

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }

}
