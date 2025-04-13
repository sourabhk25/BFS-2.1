// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach -
// - Build a HashMap from employee id to Employee object for O(1) access.
// - Use DFS to recursively sum up importance of given employee and their subordinates.


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
};

public class EmployeeImportance {
    Map<Integer, Employee> emap;    //map to put id and employee
    public int getImportance(List<Employee> employees, int id) {
        emap = new HashMap<>();
        for(Employee e: employees) {
            emap.put(e.id, e);
        }
        return dfs(id);
    }

    public int dfs(int id) {
        Employee employee = emap.get(id);   //get that employee
        int ans = employee.importance;  //get importance in ans
        for(int subid: employee.subordinates) {
            ans += dfs(subid);  //call dfs on subid
        }
        return ans;
    }

    public static void main(String[] args) {
        Employee e1 = new Employee(1, 5, Arrays.asList(2, 3));
        Employee e2 = new Employee(2, 3, new ArrayList<>());
        Employee e3 = new Employee(3, 3, new ArrayList<>());

        List<Employee> employees = Arrays.asList(e1, e2, e3);
        EmployeeImportance sol = new EmployeeImportance();

        int result = sol.getImportance(employees, 1);
        System.out.println("Total Importance for Employee 1: " + result);
    }
}
