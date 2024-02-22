package com.github.pjfanning.jmswrapper;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;

public class WrappedConnectionFactory implements ConnectionFactory {
    private ConnectionFactory wrappedConnectionFactory;

    public WrappedConnectionFactory(ConnectionFactory wrappedConnectionFactory) {
        this.wrappedConnectionFactory = wrappedConnectionFactory;
    }

    @Override
    public Connection createConnection() throws JMSException {
        return new WrappedConnection(wrappedConnectionFactory.createConnection());
    }

    @Override
    public Connection createConnection(String userName, String password) throws JMSException {
        return new WrappedConnection(wrappedConnectionFactory.createConnection(userName, password));
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
}
