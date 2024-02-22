package com.github.pjfanning.jmswrapper;

import javax.jms.Connection;
import javax.jms.ConnectionConsumer;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.ServerSessionPool;
import javax.jms.Session;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WrappedConnection implements Connection {


    private final Connection wrappedConnection;
    private final AtomicInteger closedCount = new AtomicInteger(0);
    private final List<WrappedSession> sessions = new ArrayList<>();

    public WrappedConnection(Connection wrappedConnection) {
        this.wrappedConnection = wrappedConnection;
    }

    public int getClosedCount() {
        return closedCount.get();
    }

    public int getTotalSessionCount() {
        return sessions.size();
    }

    public int getUnclosedSessionCount() {
        int count = 0;
        for (WrappedSession session : sessions) {
            if (session.getClosedCount() == 0) {
                count++;
            }
        }
        return count;
    }

    public int getTotalProducerCount() {
        int count = 0;
        for (WrappedSession session : sessions) {
            count += session.getTotalProducerCount();
        }
        return count;
    }

    public int getUnclosedProducerCount() {
        int count = 0;
        for (WrappedSession session : sessions) {
            count += session.getUnclosedProducerCount();
        }
        return count;
    }

    public int getTotalConsumerCount() {
        int count = 0;
        for (WrappedSession session : sessions) {
            count += session.getTotalConsumerCount();
        }
        return count;
    }

    public int getUnclosedConsumerCount() {
        int count = 0;
        for (WrappedSession session : sessions) {
            count += session.getUnclosedConsumerCount();
        }
        return count;
    }

    @Override
    public void start() throws JMSException {
        wrappedConnection.start();
    }

    @Override
    public void stop() throws JMSException {
        wrappedConnection.stop();
    }

    @Override
    public void close() throws JMSException {
        closedCount.incrementAndGet();
        wrappedConnection.close();
    }

    @Override
    public Session createSession(boolean transacted, int acknowledgeMode) throws JMSException {
        return wrappedSession(wrappedConnection.createSession(transacted, acknowledgeMode));
    }

    @Override
    public Session createSession(int sessionMode) throws JMSException {
        return wrappedSession(wrappedConnection.createSession(sessionMode));
    }

    @Override
    public Session createSession() throws JMSException {
        return wrappedSession(wrappedConnection.createSession());
    }

    @Override
    public String getClientID() throws JMSException {
        return wrappedConnection.getClientID();
    }

    @Override
    public void setClientID(String clientID) throws JMSException {
        wrappedConnection.setClientID(clientID);
    }

    @Override
    public ConnectionMetaData getMetaData() throws JMSException {
        return wrappedConnection.getMetaData();
    }

    @Override
    public ExceptionListener getExceptionListener() throws JMSException {
        return wrappedConnection.getExceptionListener();
    }

    @Override
    public void setExceptionListener(ExceptionListener listener) throws JMSException {
        wrappedConnection.setExceptionListener(listener);
    }

    @Override
    public ConnectionConsumer createConnectionConsumer(Destination destination, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return wrappedConnection.createConnectionConsumer(destination, messageSelector, sessionPool, maxMessages);
    }

    @Override
    public ConnectionConsumer createSharedConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return wrappedConnection.createSharedConnectionConsumer(topic, subscriptionName, messageSelector, sessionPool, maxMessages);
    }

    @Override
    public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return wrappedConnection.createDurableConnectionConsumer(topic, subscriptionName, messageSelector, sessionPool, maxMessages);
    }

    @Override
    public ConnectionConsumer createSharedDurableConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return wrappedConnection.createSharedDurableConnectionConsumer(topic, subscriptionName, messageSelector, sessionPool, maxMessages);
    }

    private WrappedSession wrappedSession(Session session) {
        WrappedSession wrappedSession = new WrappedSession(session);
        sessions.add(wrappedSession);
        return wrappedSession;
    }
}
