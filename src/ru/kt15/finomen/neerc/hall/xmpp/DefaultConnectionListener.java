package ru.kt15.finomen.neerc.hall.xmpp;

import org.jivesoftware.smack.ConnectionListener;

/**
 * @author Evgeny Mandrikov
 */
public abstract class DefaultConnectionListener implements ConnectionListener {
    public void connectionClosed() {
    }

    public void connectionClosedOnError(Exception e) {
    }

    public void reconnectingIn(int i) {
    }

    public void reconnectionSuccessful() {
    }

    public void reconnectionFailed(Exception e) {
    }
}
