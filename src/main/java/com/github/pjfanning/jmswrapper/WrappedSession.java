package com.github.pjfanning.jmswrapper;

import javax.jms.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WrappedSession implements Session {

    private final Session wrappedSession;
    private final AtomicInteger closedCount = new AtomicInteger(0);
    private final List<WrappedMessageProducer> messageProducers = new ArrayList<>();
    private final List<WrappedMessageConsumer> messageConsumers = new ArrayList<>();

    public WrappedSession(Session wrappedSession) {
        this.wrappedSession = wrappedSession;
    }

    public int getClosedCount() {
        return closedCount.get();
    }

    public int getTotalProducerCount() {
        return messageProducers.size();
    }

    public int getUnclosedProducerCount() {
        int count = 0;
        for (WrappedMessageProducer messageProducer : messageProducers) {
            if (messageProducer.getClosedCount() == 0) {
                count++;
            }
        }
        return count;
    }

    public int getTotalConsumerCount() {
        return messageConsumers.size();
    }

    public int getUnclosedConsumerCount() {
        int count = 0;
        for (WrappedMessageConsumer messageConsumer : messageConsumers) {
            if (messageConsumer.getClosedCount() == 0) {
                count++;
            }
        }
        return count;
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
        return wrapMessageProducer(wrappedSession.createProducer(destination));
    }

    @Override
    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        return wrapMessageConsumer(wrappedSession.createConsumer(destination));
    }

    @Override
    public MessageConsumer createConsumer(Destination destination, String messageSelector) throws JMSException {
        return wrapMessageConsumer(wrappedSession.createConsumer(destination, messageSelector));
    }

    @Override
    public MessageConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) throws JMSException {
        return wrapMessageConsumer(wrappedSession.createConsumer(destination, messageSelector, noLocal));
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
        return wrapMessageConsumer(wrappedSession.createSharedConsumer(topic, sharedSubscriptionName));
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) throws JMSException {
        return wrapMessageConsumer(wrappedSession.createSharedConsumer(topic, sharedSubscriptionName, messageSelector));
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic topic, String name) throws JMSException {
        return wrapMessageConsumer(wrappedSession.createDurableConsumer(topic, name));
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        return wrapMessageConsumer(wrappedSession.createDurableConsumer(topic, name, messageSelector, noLocal));
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name) throws JMSException {
        return wrapMessageConsumer(wrappedSession.createSharedDurableConsumer(topic, name));
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) throws JMSException {
        return wrapMessageConsumer(wrappedSession.createSharedDurableConsumer(topic, name, messageSelector));
    }

    private WrappedMessageProducer wrapMessageProducer(MessageProducer messageProducer) {
        WrappedMessageProducer wrappedMessageProducer = new WrappedMessageProducer(messageProducer);
        messageProducers.add(wrappedMessageProducer);
        return wrappedMessageProducer;
    }

    private WrappedMessageConsumer wrapMessageConsumer(MessageConsumer messageConsumer) {
        WrappedMessageConsumer wrappedMessageConsumer = new WrappedMessageConsumer(messageConsumer);
        messageConsumers.add(wrappedMessageConsumer);
        return wrappedMessageConsumer;
    }
}
