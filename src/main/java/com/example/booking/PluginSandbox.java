package com.example.booking;

import java.security.Permission;

/**
 * Runs third-party pricing plugins so they can't shut down the service.
 */
public class PluginSandbox {

    public void runRestricted(Runnable plugin) {
        SecurityManager previous = System.getSecurityManager();
        System.setSecurityManager(new NoExitSecurityManager());
        try {
            plugin.run();
        } finally {
            System.setSecurityManager(previous);
        }
    }

    static class NoExitSecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            // allow everything else
        }

        @Override
        public void checkExit(int status) {
            throw new SecurityException("Plugins may not call System.exit(" + status + ")");
        }
    }
}
