package com.github.pjfanning.jakartamswrapper;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

public class WrappedConnectionFactory implements ConnectionFactory {
    private ConnectionFactory wrappedConnectionFactory;
    private final List<WrappedConnection> connections = new ArrayList<>();

    public WrappedConnectionFactory(ConnectionFactory wrappedConnectionFactory) {
        this.wrappedConnectionFactory = wrappedConnectionFactory;
    }

    public int getTotalConnectionCount() {
        return connections.size();
    }

    public int getUnclosedConnectionCount() {
        int count = 0;
        for (WrappedConnection connection : connections) {
            if (connection.getClosedCount() == 0) {
                count++;
            }
        }
        return count;
    }

    public int getTotalSessionCount() {
        int count = 0;
        for (WrappedConnection connection : connections) {
            count += connection.getTotalSessionCount();
        }
        return count;
    }

    public int getUnclosedSessionCount() {
        int count = 0;
        for (WrappedConnection connection : connections) {
            count += connection.getUnclosedSessionCount();
        }
        return count;
    }

    public int getTotalProducerCount() {
        int count = 0;
        for (WrappedConnection connection : connections) {
            count += connection.getTotalProducerCount();
        }
        return count;
    }

    public int getUnclosedProducerCount() {
        int count = 0;
        for (WrappedConnection connection : connections) {
            count += connection.getUnclosedProducerCount();
        }
        return count;
    }

    /**
     * @return the total number of MessageConsumers (including TopicSubscribers) created across all connections
     */
    public int getTotalConsumerCount() {
        int count = 0;
        for (WrappedConnection connection : connections) {
            count += connection.getTotalConsumerCount();
        }
        return count;
    }

    /**
     * @return the total number of unclosed MessageConsumers (including TopicSubscribers) across all connections
     */
    public int getUnclosedConsumerCount() {
        int count = 0;
        for (WrappedConnection connection : connections) {
            count += connection.getUnclosedConsumerCount();
        }
        return count;
    }

    @Override
    public Connection createConnection() throws JMSException {
        return wrapConnection(wrappedConnectionFactory.createConnection());
    }

    @Override
    public Connection createConnection(String userName, String password) throws JMSException {
        return wrapConnection(wrappedConnectionFactory.createConnection(userName, password));
    }

    @Override
    public JMSContext createContext() {
        return wrappedConnectionFactory.createContext();
    }

    @Override
    public JMSContext createContext(String userName, String password) {
        return wrappedConnectionFactory.createContext(userName, password);
    }

    @Override
    public JMSContext createContext(String userName, String password, int sessionMode) {
        return wrappedConnectionFactory.createContext(userName, password, sessionMode);
    }

    @Override
    public JMSContext createContext(int sessionMode) {
        return wrappedConnectionFactory.createContext(sessionMode);
    }

    private WrappedConnection wrapConnection(Connection connection) {
        WrappedConnection wrappedConnection = new WrappedConnection(connection);
        connections.add(wrappedConnection);
        return wrappedConnection;
    }
}
