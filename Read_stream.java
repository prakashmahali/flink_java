import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WikipediaStreamJob {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Add custom Source
        env.addSource(new WikipediaEventSource())
            .map((MapFunction<String, String>) value -> "Received: " + value)
            .print();

        env.execute("Wikipedia Real-Time Stream");
    }

    public static class WikipediaEventSource implements SourceFunction<String> {
        private volatile boolean isRunning = true;

        @Override
        public void run(SourceContext<String> ctx) throws Exception {
            URL url = new URL("https://stream.wikimedia.org/v2/stream/recentchange");
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while (isRunning && (line = reader.readLine()) != null) {
                if (line.startsWith("data: ")) {
                    String eventJson = line.substring(6);
                    ctx.collect(eventJson);
                }
            }
        }

        @Override
        public void cancel() {
            isRunning = false;
        }
    }
}
