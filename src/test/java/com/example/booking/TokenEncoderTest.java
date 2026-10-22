package com.example.booking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenEncoderTest {

    private final TokenEncoder encoder = new TokenEncoder();

    @Test
    public void roundTripsToken() throws Exception {
        String token = encoder.encode("B-200");
        assertEquals("B-200", encoder.decode(token));
    }

    @Test
    public void producesHexChecksum() {
        assertEquals("422D323030", encoder.checksum("B-200"));
    }
}
