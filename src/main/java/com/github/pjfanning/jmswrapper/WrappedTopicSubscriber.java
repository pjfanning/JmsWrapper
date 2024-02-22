package com.github.pjfanning.jmswrapper;

import javax.jms.JMSException;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

public class WrappedTopicSubscriber extends WrappedMessageConsumer implements TopicSubscriber {
    private final TopicSubscriber wrappedTopicSubscriber;

    public WrappedTopicSubscriber(TopicSubscriber wrappedTopicSubscriber) {
        super(wrappedTopicSubscriber);
        this.wrappedTopicSubscriber = wrappedTopicSubscriber;
    }

    @Override
    public Topic getTopic() throws JMSException {
        return wrappedTopicSubscriber.getTopic();
    }

    @Override
    public boolean getNoLocal() throws JMSException {
        return wrappedTopicSubscriber.getNoLocal();
    }
}
