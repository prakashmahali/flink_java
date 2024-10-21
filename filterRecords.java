import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FilterExample {

    public static void main(String[] args) throws Exception {
        
        // Step 1: Set up the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Step 2: Create a DataStream (this could be from Kafka, files, etc.)
        // For demonstration, we use fromElements for static data
        DataStream<String> inputDataStream = env.fromElements(
                "John", "prakash", "Alice", "prakash", "Bob"
        );

        // Step 3: Apply filter to find records where the name is "prakash"
        DataStream<String> filteredDataStream = inputDataStream.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String name) {
                return name.equals("prakash");  // Filter condition
            }
        });

        // Step 4: Print the filtered records
        filteredDataStream.print();

        // Step 5: Execute the program
        env.execute("Flink Filter Example");
    }
}
