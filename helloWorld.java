import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;

public class HelloWorldFlink {
    public static void main(String[] args) throws Exception {
        // Set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // Sample data
        DataSet<String> text = env.fromElements(
                "hello world",
                "apache flink",
                "hello flink",
                "big data streaming"
        );

        // Filter lines containing the word "hello"
        DataSet<String> filtered = text.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String value) {
                return value.contains("hello");
            }
        });

        // Print the results
        filtered.print();
    }
}
