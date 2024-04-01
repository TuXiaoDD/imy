package com.example.common.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;


/**
 * A simple consumer thread that subscribes to a topic, fetches new records and prints them.
 * The thread does not stop until all records are completed or an exception is raised.
 */
public class Consumer implements ConsumerRebalanceListener {
    private final String bootstrapServers;
    private final String topic;
    private final String groupId;
    private final Optional<String> instanceId;
    private final boolean readCommitted;
    private final int numRecords;
    private volatile boolean closed;
    private int remainingRecords;


    public Consumer(
            String bootstrapServers,
            String topic,
            String groupId,
            boolean readCommitted,
            int numRecords
    ) {
        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
        this.groupId = groupId;
        this.instanceId = Optional.empty();
        this.readCommitted = readCommitted;
        this.numRecords = numRecords;
        this.remainingRecords = numRecords;
    }

    public Consumer(
            String bootstrapServers,
            String topic,
            String groupId,
            Optional<String> instanceId,
            boolean readCommitted,
            int numRecords
    ) {
        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
        this.groupId = groupId;
        this.instanceId = instanceId;
        this.readCommitted = readCommitted;
        this.numRecords = numRecords;
        this.remainingRecords = numRecords;
    }

    public void shutdown() {
        if (!closed) {
            closed = true;
        }
    }

    public KafkaConsumer<Integer, String> createKafkaConsumer() {
        Properties props = new Properties();
        // bootstrap server config is required for consumer to connect to brokers
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // client id is not required, but it's good to track the source of requests beyond just ip/port
        // by allowing a logical application name to be included in server-side request logging
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "client-" + UUID.randomUUID());
        // consumer group id is required when we use subscribe(topics) for group management
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        // sets static membership to improve availability (e.g. rolling restart)
        instanceId.ifPresent(id -> props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, id));
        // disables auto commit when EOS is enabled, because offsets are committed with the transaction
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, readCommitted ? "false" : "true");
        // key and value are just byte arrays, so we need to set appropriate deserializers
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        if (readCommitted) {
            // skips ongoing and aborted transactions
            props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        }
        // sets the reset offset policy in case of invalid or no offset
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new KafkaConsumer<>(props);
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        KafkaUtils.printOut("Revoked partitions: %s", partitions);
        // this can be used to commit pending offsets when using manual commit and EOS is disabled
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        KafkaUtils.printOut("Assigned partitions: %s", partitions);
        // this can be used to read the offsets from an external store or some other initialization
    }

    @Override
    public void onPartitionsLost(Collection<TopicPartition> partitions) {
        KafkaUtils.printOut("Lost partitions: %s", partitions);
        // this is called when partitions are reassigned before we had a chance to revoke them gracefully
        // we can't commit pending offsets because these partitions are probably owned by other consumers already
        // nevertheless, we may need to do some other cleanup
    }
}

