package com.example.booking;

/**
 * Runs the nightly occupancy report on a background thread.
 */
public class ReportWorker {

    private Thread thread;

    public void start() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ignored) {
                        // keep running until stopped
                    }
                }
            }
        }, "report-worker");
        thread.setDaemon(true);
        thread.start();
    }

    @SuppressWarnings("deprecation")
    public void cancel() throws InterruptedException {
        thread.stop();
        thread.join(2000);
    }

    public boolean isRunning() {
        return thread != null && thread.isAlive();
    }
}
