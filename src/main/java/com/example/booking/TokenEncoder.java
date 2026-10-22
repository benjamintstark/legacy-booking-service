package com.example.booking;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Encodes confirmation tokens sent to guests.
 */
public class TokenEncoder {

    @SuppressWarnings("restriction")
    public String encode(String bookingId) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(bookingId.getBytes(StandardCharsets.UTF_8));
    }

    @SuppressWarnings("restriction")
    public String decode(String token) throws IOException {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        return new String(decoder.decodeBuffer(token), StandardCharsets.UTF_8);
    }

    public String checksum(String bookingId) {
        return DatatypeConverter.printHexBinary(bookingId.getBytes(StandardCharsets.UTF_8));
    }
}
