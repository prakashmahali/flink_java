import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.List;

// Step 1: Define the user-defined class
class Employee {
    public String name;
    public int age;
    public double salary;
    public String deptName;

    public Employee(String name, int age, double salary, String deptName) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + ", salary=" + salary + ", deptName='" + deptName + "'}";
    }
}

public class FlinkFilterEmployeeByDept {

    public static void main(String[] args) throws Exception {

        // Step 2: Set up the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Step 3: Create a DataStream with some Employee objects
        DataStream<Employee> employeeDataStream = env.fromElements(
                new Employee("John", 35, 50000, "HR"),
                new Employee("Prakash", 45, 60000, "ECo"),
                new Employee("Alice", 30, 40000, "IT"),
                new Employee("Bob", 40, 70000, "DR"),
                new Employee("Eve", 29, 30000, "Sales")
        );

        // Step 4: Define the list of departments to filter
        List<String> deptList = Arrays.asList("HR", "ECo", "DR");

        // Step 5: Apply filter to find employees whose deptName is in the list
        DataStream<Employee> filteredDataStream = employeeDataStream.filter(new FilterFunction<Employee>() {
            @Override
            public boolean filter(Employee employee) {
                return deptList.contains(employee.deptName);  // Filter condition for department name
            }
        });

        // Step 6: Print the filtered records
        filteredDataStream.print();

        // Step 7: Execute the program
        env.execute("Flink Filter Employee by Department Example");
    }
}
