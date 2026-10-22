package com.example.booking;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookingServiceTest {

    private BookingRepository repository;
    private BookingService service;

    @Before
    public void setUp() {
        repository = mock(BookingRepository.class);
        service = new BookingService(repository);
        service.init();
    }

    @Test
    public void createsAndSavesBooking() {
        Booking booking = service.create("B-100", "Ada", 3, 120.0, new Date(0));
        verify(repository).save(booking);
        assertEquals(Integer.valueOf(3), booking.getNights());
    }

    @Test
    public void rejectsLongStays() {
        try {
            service.create("B-101", "Grace", 45, 99.0, new Date(0));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // ok
        }
    }

    @Test
    public void computesTotal() {
        when(repository.findById("B-102")).thenReturn(new Booking("B-102", "Linus", 4, 100.0, new Date(0)));
        assertEquals(400.0, service.totalFor("B-102"), 0.001);
    }

    @Test
    public void buildsSummaries() {
        when(repository.findAll()).thenReturn(Arrays.asList(
                new Booking("B-103", "Barbara", 2, 80.0, new Date(0))));
        List<String> lines = service.summaries();
        assertEquals(1, lines.size());
        assertEquals(true, lines.get(0).startsWith("Barbara / "));
    }
}
