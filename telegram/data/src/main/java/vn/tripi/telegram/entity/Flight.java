package vn.tripi.telegram.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "flight")
@Setter
@Getter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Long;

    @Column(name = "departure", length = 255)
    private String departure;

    @Column(name = "destination", length = 255)
    private String destination;

    @Column(name = "customer_name", length = 255)
    private String customerName;

    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Column(name = "username_tele")
    private String usernameTele;

// constructors, getters, and setters
}