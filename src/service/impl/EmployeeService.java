package service.impl;

import entity.Employee;
import enums.Department;
import enums.Position;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class EmployeeService {
    List<Employee> employeeList = new ArrayList<>();

    public EmployeeService() {
        createDefaultEmployee();
    }

    public static void main(String[] args) {
        EmployeeService employeeService=new EmployeeService();
        employeeService.getNotEmployeeNameList();
        employeeService.getEmployeesFullName();
        employeeService.getGroupEmployeesByEmployeeStatus();
        employeeService.getEmployeeNamesListBySalary();
        employeeService.getITDepartmentEmployees();
        employeeService.employeeListBySurname();
        employeeService.findEmployeeWithLowestSalary();
        employeeService.findEmployeeWithHighestSalary();
        employeeService.getTotalSalary();
        employeeService.calculateAgeAverageByDepartment();
        employeeService.filterEmployeesByAge();
        employeeService.getListOfEmployeeNames();
        employeeService.calculateAverageSalary();
        employeeService.getGroupEmployeesByDepartment();
        employeeService.countEmployeesByDepartment();
    }

    public List<Employee> createDefaultEmployee() {

        employeeList = Arrays.asList(new Employee(1, "John", "Doe", 28, 50000, Department.IT, false, LocalDate.of(2015, 5, 1), "john.doe@example.com", "1234567890", Position.JUNIOR, "123 Main St"), new Employee(2, "Jane", "Smith", 35, 60000, Department.HR, true, LocalDate.of(2010, 3, 15), "jane.smith@example.com", "0987654321", Position.MANAGER, "456 Elm St"), new Employee(3, "Mary", "Johnson", 45, 75000, Department.IT, true, LocalDate.of(2005, 8, 20), "mary.johnson@example.com", "1122334455", Position.SENIOR, "789 Oak St"), new Employee(4, "James", "Brown", 22, 40000, Department.SALES, false, LocalDate.of(2020, 2, 10), "james.brown@example.com", "6677889900", Position.INTERN, "101 Maple St"), new Employee(5, "Patricia", "Williams", 30, 55000, Department.MARKETING, false, LocalDate.of(2013, 6, 5), "patricia.williams@example.com", "3344556677", Position.JUNIOR, "202 Pine St"), new Employee(6, "Michael", "Jones", 40, 80000, Department.FINANCE, true, LocalDate.of(2008, 11, 25), "michael.jones@example.com", "9988776655", Position.DIRECTOR, "303 Cedar St"), new Employee(7, "Linda", "Taylor", 32, 62000, Department.IT, false, LocalDate.of(2016, 7, 19), "linda.taylor@example.com", "1212121212", Position.SENIOR, "123 Birch St"), new Employee(8, "Robert", "Anderson", 29, 58000, Department.HR, false, LocalDate.of(2012, 11, 1), "robert.anderson@example.com", "1313131313", Position.JUNIOR, "456 Spruce St"), new Employee(9, "Jennifer", "Moore", 38, 67000, Department.SALES, true, LocalDate.of(2009, 4, 22), "jennifer.moore@example.com", "1414141414", Position.MANAGER, "789 Willow St"), new Employee(10, "William", "Jackson", 47, 90000, Department.IT, true, LocalDate.of(2003, 10, 3), "william.jackson@example.com", "1515151515", Position.EXECUTIVE, "101 Pineapple St"), new Employee(11, "Barbara", "White", 26, 45000, Department.MARKETING, false, LocalDate.of(2018, 1, 12), "barbara.white@example.com", "1616161616", Position.JUNIOR, "202 Apple St"), new Employee(12, "Charles", "Harris", 36, 68000, Department.FINANCE, true, LocalDate.of(2011, 9, 27), "charles.harris@example.com", "1717171717", Position.MANAGER, "303 Peach St"), new Employee(13, "Jessica", "Martin", 34, 64000, Department.IT, false, LocalDate.of(2014, 6, 15), "jessica.martin@example.com", "1818181818", Position.SENIOR, "404 Orange St"), new Employee(14, "Daniel", "Thompson", 31, 59000, Department.SALES, false, LocalDate.of(2017, 2, 25), "daniel.thompson@example.com", "1919191919", Position.JUNIOR, "505 Banana St"), new Employee(15, "Elizabeth", "Garcia", 28, 54000, Department.HR, false, LocalDate.of(2016, 3, 7), "elizabeth.garcia@example.com", "2020202020", Position.JUNIOR, "606 Grape St"), new Employee(16, "Matthew", "Martinez", 42, 85000, Department.FINANCE, true, LocalDate.of(2007, 5, 30), "matthew.martinez@example.com", "2121212121", Position.DIRECTOR, "707 Cherry St"), new Employee(17, "Susan", "Robinson", 37, 69000, Department.MARKETING, true, LocalDate.of(2010, 10, 10), "susan.robinson@example.com", "2222222222", Position.MANAGER, "808 Lemon St"), new Employee(18, "Joseph", "Clark", 24, 42000, Department.IT, false, LocalDate.of(2019, 8, 8), "joseph.clark@example.com", "2323232323", Position.INTERN, "909 Lime St"), new Employee(19, "Sarah", "Rodriguez", 39, 72000, Department.SALES, true, LocalDate.of(2008, 12, 21), "sarah.rodriguez@example.com", "2424242424", Position.MANAGER, "1010 Plum St"), new Employee(20, "Christopher", "Lewis", 41, 81000, Department.IT, true, LocalDate.of(2006, 7, 16), "christopher.lewis@example.com", "2525252525", Position.DIRECTOR, "1111 Kiwi St"), new Employee(21, "Karen", "Lee", 30, 56000, Department.HR, false, LocalDate.of(2015, 11, 5), "karen.lee@example.com", "2626262626", Position.JUNIOR, "1212 Pear St"), new Employee(22, "Mark", "Walker", 35, 60000, Department.MARKETING, false, LocalDate.of(2013, 4, 9), "mark.walker@example.com", "2727272727", Position.SENIOR, "1313 Mango St"), new Employee(23, "Donald", "Hall", 33, 61000, Department.FINANCE, false, LocalDate.of(2014, 5, 18), "donald.hall@example.com", "2828282828", Position.SENIOR, "1414 Avocado St"), new Employee(24, "Betty", "Allen", 44, 76000, Department.IT, true, LocalDate.of(2009, 8, 28), "betty.allen@example.com", "2929292929", Position.MANAGER, "1515 Pine St"), new Employee(25, "Steven", "Young", 38, 68000, Department.SALES, true, LocalDate.of(2012, 3, 3), "steven.young@example.com", "3030303030", Position.MANAGER, "1616 Maple St"), new Employee(26, "Dorothy", "Hernandez", 29, 53000, Department.IT, false, LocalDate.of(2016, 12, 12), "dorothy.hernandez@example.com", "3131313131", Position.JUNIOR, "1717 Birch St"), new Employee(27, "Paul", "King", 32, 62000, Department.FINANCE, false, LocalDate.of(2017, 1, 23), "paul.king@example.com", "3232323232", Position.SENIOR, "1818 Spruce St"), new Employee(28, "Nancy", "Wright", 36, 67000, Department.MARKETING, true, LocalDate.of(2010, 5, 12), "nancy.wright@example.com", "3333333333", Position.MANAGER, "1919 Willow St"), new Employee(29, "Edward", "Lopez", 25, 44000, Department.SALES, false, LocalDate.of(2019, 2, 14), "edward.lopez@example.com", "3434343434", Position.INTERN, "2020 Pineapple St"), new Employee(30, "Ruth", "Scott", 31, 59000, Department.IT, false, LocalDate.of(2015, 6, 22), "ruth.scott@example.com", "3535353535", Position.JUNIOR, "2121 Apple St"), new Employee(31, "Frank", "Hill", 40, 80000, Department.HR, true, LocalDate.of(2008, 11, 20), "frank.hill@example.com", "3636363636", Position.DIRECTOR, "2222 Peach St"), new Employee(32, "Emma", "Green", 28, 55000, Department.FINANCE, false, LocalDate.of(2016, 7, 19), "emma.green@example.com", "3737373737", Position.JUNIOR, "2323 Orange St"), new Employee(33, "Henry", "Adams", 37, 70000, Department.IT, true, LocalDate.of(2011, 8, 25), "henry.adams@example.com", "3838383838", Position.SENIOR, "2424 Banana St"), new Employee(34, "Mildred", "Baker", 34, 64000, Department.MARKETING, false, LocalDate.of(2014, 4, 15), "mildred.baker@example.com", "3939393939", Position.SENIOR, "2525 Grape St"), new Employee(35, "Walter", "Gonzalez", 29, 58000, Department.IT, false, LocalDate.of(2016, 10, 10), "walter.gonzalez@example.com", "4040404040", Position.JUNIOR, "2626 Cherry St"), new Employee(36, "Alice", "Nelson", 45, 75000, Department.FINANCE, true, LocalDate.of(2004, 3, 9), "alice.nelson@example.com", "4141414141", Position.EXECUTIVE, "2727 Lemon St"), new Employee(37, "Albert", "Carter", 42, 83000, Department.IT, true, LocalDate.of(2006, 5, 22), "albert.carter@example.com", "4242424242", Position.DIRECTOR, "2828 Lime St"), new Employee(38, "Marie", "Mitchell", 33, 61000, Department.HR, false, LocalDate.of(2015, 7, 1), "marie.mitchell@example.com", "4343434343", Position.SENIOR, "2929 Plum St"), new Employee(39, "Evelyn", "Perez", 27, 52000, Department.MARKETING, false, LocalDate.of(2017, 9, 18), "evelyn.perez@example.com", "4444444444", Position.JUNIOR, "3030 Kiwi St"), new Employee(40, "Jack", "Roberts", 36, 67000, Department.IT, true, LocalDate.of(2011, 12, 11), "jack.roberts@example.com", "4545454545", Position.MANAGER, "3131 Pear St"), new Employee(41, "Lillian", "Turner", 31, 59000, Department.FINANCE, false, LocalDate.of(2015, 6, 30), "lillian.turner@example.com", "4646464646", Position.JUNIOR, "3232 Mango St"), new Employee(42, "David", "Phillips", 48, 92000, Department.IT, true, LocalDate.of(2002, 1, 22), "david.phillips@example.com", "4747474747", Position.EXECUTIVE, "3333 Avocado St"), new Employee(43, "Dorothy", "Campbell", 29, 54000, Department.HR, false, LocalDate.of(2016, 12, 1), "dorothy.campbell@example.com", "4848484848", Position.JUNIOR, "3434 Pine St"), new Employee(44, "Harold", "Parker", 35, 63000, Department.SALES, true, LocalDate.of(2013, 7, 14), "harold.parker@example.com", "4949494949", Position.MANAGER, "3535 Maple St"), new Employee(45, "Betty", "Evans", 39, 71000, Department.MARKETING, true, LocalDate.of(2008, 10, 5), "betty.evans@example.com", "5050505050", Position.MANAGER, "3636 Birch St"), new Employee(46, "Helen", "Edwards", 38, 69000, Department.FINANCE, true, LocalDate.of(2010, 4, 30), "helen.edwards@example.com", "5151515151", Position.MANAGER, "3737 Spruce St"), new Employee(47, "George", "Collins", 28, 55000, Department.IT, false, LocalDate.of(2017, 5, 20), "george.collins@example.com", "5252525252", Position.JUNIOR, "3838 Willow St"), new Employee(48, "Catherine", "Stewart", 42, 81000, Department.HR, true, LocalDate.of(2006, 11, 10), "catherine.stewart@example.com", "5353535353", Position.DIRECTOR, "3939 Pineapple St"), new Employee(49, "Henry", "Sanchez", 26, 46000, Department.MARKETING, false, LocalDate.of(2018, 2, 28), "henry.sanchez@example.com", "5454545454", Position.JUNIOR, "4040 Apple St"), new Employee(50, "Janet", "Morris", 37, 70000, Department.FINANCE, true, LocalDate.of(2009, 5, 15), "janet.morris@example.com", "5555555555", Position.MANAGER, "4141 Peach St"));
        return employeeList;
    }

    public void filterEmployeesByAge() {
        System.out.println("Filtering employees older than 30:");
        employeeList.stream()
                .filter(employee1 -> employee1.getAge() > 30)
                .forEach(System.out::println);
    }

    public void getListOfEmployeeNames() {
        System.out.println("Getting list of employee names:");
        employeeList.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    public void findEmployeeWithHighestSalary() {
        System.out.println("Finding employee with the highest salary:");
        Employee highestSalaryEmployee = employeeList.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .get();
        System.out.println(highestSalaryEmployee);
    }

    public void calculateAverageSalary() {
        System.out.println("Calculating average salary: ");
        double averageSalary = employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .average().getAsDouble();
        System.out.println("Average salary: " + averageSalary);
    }

    public void employeeListBySurname() {
        System.out.println("Sorting employees by surname:");
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSurname))
                .forEach(System.out::println);
    }

    public void checkAllEmployeesAgeOlderThan25() {
        System.out.println("Checking if all employees are older than 25:");
        boolean allOlderThan25 = employeeList.stream()
                .allMatch(employee1 -> employee1.getAge() > 25);
        System.out.println("All employees older than 25: " + allOlderThan25);
    }

    public void getITDepartmentEmployees() {
        System.out.println("Getting employees from IT department:");
        employeeList.stream()
                .filter(employee -> Department.IT.equals(employee.getDepartment()))
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    public void getGroupEmployeesByDepartment() {
        System.out.println("Grouping employees by department:");
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .forEach((department, employees) -> {
                    System.out.println(department);
                    employees.forEach(System.out::println);
                });
    }

    public void countEmployeesByDepartment() {
        System.out.println("Counting employees by department:");
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .forEach((department, count) -> {
                    System.out.println(department + " => " + count);
                });
    }

    public void calculateAgeAverageByDepartment() {
        System.out.println("The average age of employees in each department: ");
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .forEach((department, employees) -> System.out.println(department + "=> "
                        + employees.stream().mapToDouble(Employee::getSalary).average().getAsDouble()));
    }

    public void getGroupEmployeesByEmployeeStatus() {
        System.out.println("Employees: ");
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::isEmployer))
                .forEach((isEmployee, employees) -> {
                    System.out.println("----------------" + isEmployee + "-------------------");
                    employees.forEach(System.out::println);
                });
    }

    public void getTotalSalary() {
        System.out.println("Total salary of all employees");
        Double allSalary = employeeList.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println(allSalary);
    }

    public void findEmployeeWithLowestSalary() {
        System.out.println("Finding employee with the lowest salary:");
        Employee lowestSalaryEmployee = employeeList.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .get();
        System.out.println(lowestSalaryEmployee);
    }
    //14
//    public void getUniqueSurnameEmployee(){
//        System.out.println("List of the unique surnames of all employees");
//        employeeList.stream().filter(employee ->
//
//            employeeList.stream()
//                    .anyMatch((employee1 ->
//                            !employee1.getSurname().equals(employee.getSurname())))).forEach(System.out::println);
//    }

    public void getEmployeeNamesListBySalary() {
        System.out.println("List of employee names to their respective salaries");
        employeeList.stream()
                .sorted(Comparator
                        .comparing(Employee::getSalary).reversed()).map(Employee::getName)
                .forEach(System.out::println);
    }

    public void getNotEmployeeNameList(){
        System.out.println("List the names of employees who are not employers: ");
        employeeList.stream()
                .filter(employee -> !employee.isEmployer()).map(Employee::getName)
                .forEach(System.out::println);
    }
    public void getEmployeesFullName(){
        System.out.println("Get a list of employees' full names (name + surname):");
        employeeList.stream()
                .map((employee -> employee.getName()+" "+employee.getSurname()))
                .forEach(System.out::println);
    }



}





