//package com.example.common.kafka;
//
//
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//public class KafkaExactlyOnceDemo {
//    private static final String INPUT_TOPIC = "input-topic";
//    private static final String OUTPUT_TOPIC = "output-topic";
//    public static final String GROUP_NAME = "check-group";
//
//    public static void main(String[] args) {
//        try {
//
//            int numPartitions = 1;
//            int numInstances = 1;
//            int numRecords = 1024;
//
//            // stage 1: clean any topics left from previous runs
//            Utils.recreateTopics(KafkaProperties.BOOTSTRAP_SERVERS, numPartitions, INPUT_TOPIC, OUTPUT_TOPIC);
//
//            // stage 2: send demo records to the input-topic
//            CountDownLatch producerLatch = new CountDownLatch(1);
//            Producer producerThread = new Producer(
//                    "producer",
//                    KafkaProperties.BOOTSTRAP_SERVERS,
//                    INPUT_TOPIC,
//                    false,
//                    null,
//                    true,
//                    numRecords,
//                    -1,
//                    producerLatch);
//            producerThread.start();
//            if (!producerLatch.await(2, TimeUnit.MINUTES)) {
//                Utils.printErr("Timeout after 2 minutes waiting for data load");
//                producerThread.shutdown();
//                return;
//            }
//
//            // stage 3: read from input-topic, process once and write to the output-topic
//            CountDownLatch processorsLatch = new CountDownLatch(numInstances);
//            List<ExactlyOnceMessageProcessor> processors = IntStream.range(0, numInstances)
//                    .mapToObj(id -> new ExactlyOnceMessageProcessor(
//                            "processor-" + id,
//                            KafkaProperties.BOOTSTRAP_SERVERS,
//                            INPUT_TOPIC,
//                            OUTPUT_TOPIC,
//                            processorsLatch))
//                    .collect(Collectors.toList());
//            processors.forEach(ExactlyOnceMessageProcessor::start);
//            if (!processorsLatch.await(2, TimeUnit.MINUTES)) {
//                Utils.printErr("Timeout after 2 minutes waiting for record copy");
//                processors.forEach(ExactlyOnceMessageProcessor::shutdown);
//                return;
//            }
//
//            // stage 4: check consuming records from the output-topic
//            CountDownLatch consumerLatch = new CountDownLatch(1);
//            Consumer consumerThread = new Consumer(
//                    "consumer",
//                    KafkaProperties.BOOTSTRAP_SERVERS,
//                    OUTPUT_TOPIC,
//                    GROUP_NAME,
//                    Optional.empty(),
//                    true,
//                    numRecords,
//                    consumerLatch);
//            consumerThread.start();
//            if (!consumerLatch.await(2, TimeUnit.MINUTES)) {
//                Utils.printErr("Timeout after 2 minutes waiting for output read");
//                consumerThread.shutdown();
//            }
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static class KafkaProperties{
//        public static final String BOOTSTRAP_SERVERS = "192.168.43.59:9092";
//    }
//}
