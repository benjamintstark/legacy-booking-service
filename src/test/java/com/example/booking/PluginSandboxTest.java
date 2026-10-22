package com.example.booking;

import org.junit.Test;

import static org.junit.Assert.fail;

public class PluginSandboxTest {

    @Test
    public void blocksPluginsFromExiting() {
        PluginSandbox sandbox = new PluginSandbox();
        try {
            sandbox.runRestricted(new Runnable() {
                @Override
                public void run() {
                    System.exit(1);
                }
            });
            fail("Expected SecurityException");
        } catch (SecurityException expected) {
            // ok
        }
    }
}
