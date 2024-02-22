package com.github.pjfanning.jakartamswrapper;

import jakarta.jms.CompletionListener;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageProducer;
import java.util.concurrent.atomic.AtomicInteger;

public class WrappedMessageProducer implements MessageProducer {

    private final MessageProducer wrappedMessageProducer;
    private final AtomicInteger closedCount = new AtomicInteger(0);

    public WrappedMessageProducer(MessageProducer wrappedMessageProducer) {
        this.wrappedMessageProducer = wrappedMessageProducer;
    }

    public int getClosedCount() {
        return closedCount.get();
    }

    @Override
    public void setDisableMessageID(boolean value) throws JMSException {
        wrappedMessageProducer.setDisableMessageID(value);
    }

    @Override
    public boolean getDisableMessageID() throws JMSException {
        return wrappedMessageProducer.getDisableMessageID();
    }

    @Override
    public void setDisableMessageTimestamp(boolean value) throws JMSException {
        wrappedMessageProducer.setDisableMessageTimestamp(value);
    }

    @Override
    public boolean getDisableMessageTimestamp() throws JMSException {
        return wrappedMessageProducer.getDisableMessageTimestamp();
    }

    @Override
    public void setDeliveryMode(int deliveryMode) throws JMSException {
        wrappedMessageProducer.setDeliveryMode(deliveryMode);
    }

    @Override
    public int getDeliveryMode() throws JMSException {
        return wrappedMessageProducer.getDeliveryMode();
    }

    @Override
    public void setPriority(int defaultPriority) throws JMSException {
        wrappedMessageProducer.setPriority(defaultPriority);
    }

    @Override
    public int getPriority() throws JMSException {
        return wrappedMessageProducer.getPriority();
    }

    @Override
    public void setTimeToLive(long timeToLive) throws JMSException {
        wrappedMessageProducer.setTimeToLive(timeToLive);
    }

    @Override
    public long getTimeToLive() throws JMSException {
        return wrappedMessageProducer.getTimeToLive();
    }

    @Override
    public void setDeliveryDelay(long deliveryDelay) throws JMSException {
        wrappedMessageProducer.setDeliveryDelay(deliveryDelay);
    }

    @Override
    public long getDeliveryDelay() throws JMSException {
        return wrappedMessageProducer.getDeliveryDelay();
    }

    @Override
    public Destination getDestination() throws JMSException {
        return wrappedMessageProducer.getDestination();
    }

    @Override
    public void close() throws JMSException {
        closedCount.incrementAndGet();
        wrappedMessageProducer.close();
    }

    @Override
    public void send(Message message) throws JMSException {
        wrappedMessageProducer.send(message);
    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        wrappedMessageProducer.send(message, deliveryMode, priority, timeToLive);
    }

    @Override
    public void send(Destination destination, Message message) throws JMSException {
        wrappedMessageProducer.send(destination, message);
    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        wrappedMessageProducer.send(destination, message, deliveryMode, priority, timeToLive);
    }

    @Override
    public void send(Message message, CompletionListener completionListener) throws JMSException {
        wrappedMessageProducer.send(message, completionListener);
    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {
        wrappedMessageProducer.send(message, deliveryMode, priority, timeToLive, completionListener);
    }

    @Override
    public void send(Destination destination, Message message, CompletionListener completionListener) throws JMSException {
        wrappedMessageProducer.send(destination, message, completionListener);
    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {
        wrappedMessageProducer.send(destination, message, deliveryMode, priority, timeToLive, completionListener);
    }
}
