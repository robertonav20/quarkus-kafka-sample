package org.acme.kafka;

import io.reactivex.rxjava3.core.Flowable;
import io.smallrye.reactive.messaging.kafka.Record;
import org.acme.model.Price;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class PriceGenerator {

    private Random random = new Random();

    @Outgoing("generated-price")
    public Flowable<Record<String, Price>> generate() {
        return Flowable.interval(5, TimeUnit.SECONDS)
                .map(tick -> {
                    Price price = Price.builder()
                            .id(UUID.randomUUID().toString())
                            .value(random.nextDouble())
                            .build();
                    return Record.of(UUID.randomUUID().toString(), price);
                });
    }
}
