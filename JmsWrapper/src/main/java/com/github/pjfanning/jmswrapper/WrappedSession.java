package com.github.pjfanning.jmswrapper;

import javax.jms.*;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class WrappedSession implements Session {

    private final Session wrappedSession;
    private final AtomicInteger closedCount = new AtomicInteger(0);

    public WrappedSession(Session wrappedSession) {
        this.wrappedSession = wrappedSession;
    }

    public int getClosedCount() {
        return closedCount.get();
    }

    @Override
    public void close() throws JMSException {
        closedCount.incrementAndGet();
        wrappedSession.close();
    }

    @Override
    public void commit() throws JMSException {
        wrappedSession.commit();
    }

    @Override
    public void rollback() throws JMSException {
        wrappedSession.rollback();
    }

    @Override
    public void recover() throws JMSException {
        wrappedSession.recover();
    }

    @Override
    public void run() {
        wrappedSession.run();
    }

    @Override
    public MessageProducer createProducer(Destination destination) throws JMSException {
        return wrappedSession.createProducer(destination);
    }

    @Override
    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        return wrappedSession.createConsumer(destination);
    }

    @Override
    public MessageConsumer createConsumer(Destination destination, String messageSelector) throws JMSException {
        return wrappedSession.createConsumer(destination, messageSelector);
    }

    @Override
    public MessageConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) throws JMSException {
        return wrappedSession.createConsumer(destination, messageSelector, noLocal);
    }

    @Override
    public Queue createQueue(String queueName) throws JMSException {
        return wrappedSession.createQueue(queueName);
    }

    @Override
    public Topic createTopic(String topicName) throws JMSException {
        return wrappedSession.createTopic(topicName);
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String name) throws JMSException {
        return wrappedSession.createDurableSubscriber(topic, name);
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        return wrappedSession.createDurableSubscriber(topic, name, messageSelector, noLocal);
    }

    @Override
    public QueueBrowser createBrowser(Queue queue) throws JMSException {
        return wrappedSession.createBrowser(queue);
    }

    @Override
    public QueueBrowser createBrowser(Queue queue, String messageSelector) throws JMSException {
        return wrappedSession.createBrowser(queue, messageSelector);
    }

    @Override
    public TemporaryQueue createTemporaryQueue() throws JMSException {
        return wrappedSession.createTemporaryQueue();
    }

    @Override
    public TemporaryTopic createTemporaryTopic() throws JMSException {
        return wrappedSession.createTemporaryTopic();
    }

    @Override
    public void unsubscribe(String name) throws JMSException {
        wrappedSession.unsubscribe(name);
    }

    @Override
    public void setMessageListener(MessageListener messageListener) throws JMSException {
        wrappedSession.setMessageListener(messageListener);
    }

    @Override
    public BytesMessage createBytesMessage() throws JMSException {
        return wrappedSession.createBytesMessage();
    }

    @Override
    public MapMessage createMapMessage() throws JMSException {
        return wrappedSession.createMapMessage();
    }

    @Override
    public Message createMessage() throws JMSException {
        return wrappedSession.createMessage();
    }

    @Override
    public ObjectMessage createObjectMessage() throws JMSException {
        return wrappedSession.createObjectMessage();
    }

    @Override
    public ObjectMessage createObjectMessage(Serializable object) throws JMSException {
        return wrappedSession.createObjectMessage();
    }

    @Override
    public StreamMessage createStreamMessage() throws JMSException {
        return wrappedSession.createStreamMessage();
    }

    @Override
    public TextMessage createTextMessage() throws JMSException {
        return wrappedSession.createTextMessage();
    }

    @Override
    public TextMessage createTextMessage(String text) throws JMSException {
        return wrappedSession.createTextMessage(text);
    }

    @Override
    public boolean getTransacted() throws JMSException {
        return wrappedSession.getTransacted();
    }

    @Override
    public int getAcknowledgeMode() throws JMSException {
        return wrappedSession.getAcknowledgeMode();
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        return wrappedSession.getMessageListener();
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) throws JMSException {
        return wrappedSession.createSharedConsumer(topic, sharedSubscriptionName);
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) throws JMSException {
        return wrappedSession.createSharedConsumer(topic, sharedSubscriptionName, messageSelector);
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic topic, String name) throws JMSException {
        return wrappedSession.createDurableConsumer(topic, name);
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        return wrappedSession.createDurableConsumer(topic, name, messageSelector, noLocal);
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name) throws JMSException {
        return wrappedSession.createSharedDurableConsumer(topic, name);
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) throws JMSException {
        return wrappedSession.createSharedDurableConsumer(topic, name, messageSelector);
    }
}
