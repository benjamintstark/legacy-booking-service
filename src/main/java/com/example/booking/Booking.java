package com.example.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private String id;
    private String guestName;
    private Integer nights;
    private Double nightlyRate;
    private Date checkIn;
}
