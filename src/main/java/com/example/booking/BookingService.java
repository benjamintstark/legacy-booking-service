package com.example.booking;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingService {

    private final BookingRepository repository;
    private Integer maxNights;
    private SimpleDateFormat dateFormat;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        maxNights = new Integer(30);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public Booking create(String id, String guestName, int nights, double nightlyRate, Date checkIn) {
        if (nights > maxNights.intValue()) {
            throw new IllegalArgumentException("Stay exceeds " + maxNights + " nights");
        }
        Booking booking = new Booking(id, guestName, new Integer(nights), new Double(nightlyRate), checkIn);
        repository.save(booking);
        return booking;
    }

    public Double totalFor(String id) {
        Booking booking = repository.findById(id);
        if (booking == null) {
            return new Double(0);
        }
        return new Double(booking.getNights().intValue() * booking.getNightlyRate().doubleValue());
    }

    public List<String> summaries() {
        List<String> lines = new ArrayList<String>();
        for (Booking booking : repository.findAll()) {
            lines.add(booking.getGuestName() + " / " + dateFormat.format(booking.getCheckIn())
                    + " / " + booking.getNights() + " nights");
        }
        return lines;
    }
}
