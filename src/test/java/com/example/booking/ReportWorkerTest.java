package com.example.booking;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReportWorkerTest {

    @Test
    public void cancelStopsTheWorker() throws Exception {
        ReportWorker worker = new ReportWorker();
        worker.start();
        assertTrue(worker.isRunning());
        worker.cancel();
        assertFalse(worker.isRunning());
    }
}
