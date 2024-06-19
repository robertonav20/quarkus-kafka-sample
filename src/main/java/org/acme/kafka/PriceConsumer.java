package org.acme.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.acme.model.Price;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class PriceConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PriceConsumer.class);

    @Incoming("prices")
    public CompletionStage<Void> consume(Message<Record<String, Price>> message) {
        Metadata metadata = message.getMetadata();
        Record<String, Price> record = message.getPayload();

        String key = record.key();
        Price value = record.value();
        logger.info("Message : {}, {}, {}", metadata, key, value.toString());
        return message.ack();
    }

}