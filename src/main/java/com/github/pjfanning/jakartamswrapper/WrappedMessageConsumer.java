package com.github.pjfanning.jakartamswrapper;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageListener;
import java.util.concurrent.atomic.AtomicInteger;

public class WrappedMessageConsumer implements MessageConsumer {

    private final MessageConsumer wrappedMessageConsumer;
    private final AtomicInteger closedCount = new AtomicInteger(0);

    public WrappedMessageConsumer(MessageConsumer wrappedMessageConsumer) {
        this.wrappedMessageConsumer = wrappedMessageConsumer;
    }

    public int getClosedCount() {
        return closedCount.get();
    }

    @Override
    public String getMessageSelector() throws JMSException {
        return wrappedMessageConsumer.getMessageSelector();
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        return wrappedMessageConsumer.getMessageListener();
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
        wrappedMessageConsumer.setMessageListener(listener);
    }

    @Override
    public Message receive() throws JMSException {
        return wrappedMessageConsumer.receive();
    }

    @Override
    public Message receive(long timeout) throws JMSException {
        return wrappedMessageConsumer.receive(timeout);
    }

    @Override
    public Message receiveNoWait() throws JMSException {
        return wrappedMessageConsumer.receiveNoWait();
    }

    @Override
    public void close() throws JMSException {
        closedCount.incrementAndGet();
        wrappedMessageConsumer.close();
    }
}
