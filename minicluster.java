package com.example;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class HelloFlinkMiniCluster {
    public static void main(String[] args) throws Exception {
        // Create local execution environment (MiniCluster)
        Configuration config = new Configuration();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment(config);

        // Create a simple data stream
        DataStream<String> names = env.fromElements("Alice", "Bob", "Charlie");

        // Map transformation
        DataStream<String> greetings = names.map(new MapFunction<String, String>() {
            @Override
            public String map(String name) {
                return "Hello, " + name + "!";
            }
        });

        // Print to console
        greetings.print();

        // Trigger execution
        JobExecutionResult result = env.execute("MiniCluster Hello World");
    }
}
