package io.cucumber.skeleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SalaryManager {
    private List<Employee> employees = new ArrayList<>();

    public SalaryManager() {
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void increaseSalary(final Integer id, final int increaseInPercent) {
        Employee nominee = employees.get(id);
        float oldSalary = nominee.getSalary();
        nominee.setSalary(oldSalary + oldSalary * increaseInPercent / 100);
    }

    public Employee getPayroll(final int id) {
        return employees.get(id);
    }
}