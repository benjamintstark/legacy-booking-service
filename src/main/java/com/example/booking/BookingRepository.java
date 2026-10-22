package com.example.booking;

import java.util.List;

public interface BookingRepository {
    Booking findById(String id);

    List<Booking> findAll();

    void save(Booking booking);
}
