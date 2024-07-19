import java.util.*;

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;

    public Employee(int id, int importance, List<Integer> subordinates) {
        this.id = id;
        this.importance = importance;
        this.subordinates = subordinates;
    }
}

public class EmployeeImportance {
    public static int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Employee> map = new HashMap<>();
        for(Employee employee : employees) { //O(n) S.C
            map.put(employee.id, employee); //key as e.id, values as same employee class in the map
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(id); //add initial search id
        int result = 0;

        while(!q.isEmpty()) { //BFS, O(n) T.C
            int size = q.size();

            for(int i=0; i<size; i++) {
                int current = q.poll();
                Employee e = map.get(current); //get the current employee from queue
                result += e.importance; //add his importance to the result
                q.addAll(e.subordinates); //add all his subordinates to the queue for further BFS
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Employee> employees1 = Arrays.asList(
                new Employee(1, 5, Arrays.asList(2, 3)),
                new Employee(2, 3, Collections.singletonList(4)),
                new Employee(3, 4, Collections.emptyList()),
                new Employee(4, 1, Collections.emptyList())
        );

        System.out.println("Total Importance for ID 1 in Test Case 1: " + getImportance(employees1, 1)); // Output: 13

        // Test case 2
        List<Employee> employees2 = Arrays.asList(
                new Employee(1, 2, List.of(2)),
                new Employee(2, 3, Collections.emptyList())
        );

        System.out.println("Total Importance for ID 1 in Test Case 2: " + getImportance(employees2, 1)); // Output: 5

        // Test case 3
        List<Employee> employees3 = Arrays.asList(
                new Employee(1, 3, Arrays.asList(2, 3)),
                new Employee(2, 3, Collections.emptyList()),
                new Employee(3, 3, Collections.emptyList())
        );

        System.out.println("Total Importance for ID 1 in Test Case 3: " + getImportance(employees3, 1)); // Output: 9

    }
}