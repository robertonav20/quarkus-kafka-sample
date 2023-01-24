package org.acme.kafka;

import org.acme.model.Price;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PriceDeserializer implements Deserializer<Price> {

    @Override
    public Price deserialize(String topic, byte[] data) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data)) {
            ObjectInputStream objectOutputStream = new ObjectInputStream(inputStream);
            return (Price) objectOutputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}