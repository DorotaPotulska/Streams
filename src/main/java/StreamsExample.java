import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsExample {

    List<Employee> employees = new ArrayList<>();

    @Before
    public void setUp(){
        Employee employee1 = new Employee("Kakaka","Kdkdkd",12,List.of("Java","JavaScript","Python"));
        Employee employee2 = new Employee("Adafkakaka","Dsakdkdkd",45,List.of("HTML","JavaScript","CSS"));
        Employee employee3 = new Employee("Sdskakaka","Gtrkdkdkd",33,List.of("C#"));
        Employee employee4 = new Employee("Gfgkakaka","Rsdkdkdkd",88,List.of("Java","JavaScript","Python","Angular"));
        Employee employee5 = new Employee("Wtrsv","Cdskdkdkd",34,List.of("Java","Scala"));
        Employee employee6 = new Employee("Ngsfgf","Zaskdkdkd",2,List.of("Java","JavaScript","Python","JQuery"));
        Employee employee7 = new Employee("dgtvbg","Udskdkdkd",47,List.of("Rust","Python"));
        Employee employee8 = new Employee("Liguikg","Hytkdkdkd",33,List.of("Java","Spring"));

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        employees.add(employee7);
        employees.add(employee8);

    }

    @Test
    public void firstStream(){
        /*employees.stream()
                .forEach(employee -> System.out.println(employee));*/
        System.out.println(employees);
        employees.forEach(System.out::println);
    }

    @Test
    public void mapOperations(){
//        employees.stream()
//                .map(employee -> employee.getFirstName())
//                .forEach(System.out::println);

        employees.stream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

    @Test
    public void flatMapOperations(){
        List<List<String>> allSkills = employees.stream()
                //.map(employee -> employee.getSkills())
                .map(Employee::getSkills)
                .collect(Collectors.toList());
        System.out.println(allSkills);

        List<String> allSkills2 = employees.stream()
                .map(Employee::getSkills)
                .flatMap(list->list.stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(allSkills2);

    }

    @Test
    public void filterOperations(){
        employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("K"))
                .forEach(System.out::println);
    }

    @Test
    public void sortedOperations(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .forEach(System.out::println);
    }

    @Test
    public void limitOperations(){
        employees.stream()
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void skipOperations(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void countOperations(){
        long numberOfEmployees = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("K"))
                .count();

        System.out.println(numberOfEmployees);
    }

    @Test
    public void minMaxOperations(){
        Employee youngestEmployee = employees.stream()
                .min(Comparator.comparing(Employee::getAge)).get();

        System.out.println(youngestEmployee);

        Employee oldestEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getAge)).get();

        System.out.println(oldestEmployee);
    }

    @Test
    public void findAnyFirstOperations(){
        Employee employee = employees.stream()
                .filter(emp -> emp.getFirstName().startsWith("K"))
                .findFirst().get();
        System.out.println(employee);

        Employee employee2 = employees.stream()
                .filter(emp -> emp.getFirstName().startsWith("K"))
                .findAny().get();
        System.out.println(employee2);

    }

    @Test
    public void matchOperations(){
        boolean b = employees.stream()
                //.allMatch(emp -> emp.getFirstName().startsWith("E"));
                //.allMatch(emp -> emp.getFirstName().contains("a"));
                .anyMatch(emp -> emp.getFirstName().contains("a"));
                //.noneMatch(emp -> emp.getFirstName().startsWith("K"));

        System.out.println(b);
    }

    @Test
    public void reduceOperations(){
        /*Integer sumOfAllAges = employees.stream()
                .map(emp -> emp.getAge())
                .reduce((age1, age2) -> age1 + age2)
                .get();*/
        Integer sumOfAllAges = employees.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum)
                .get();
        System.out.println(sumOfAllAges);

        /*int sumOfAllAges2 = employees.stream()
                .map(Employee::getAge)
                .reduce(0,Integer::sum)
                .intValue();*/

        Integer sumOfAllAges2 = employees.stream()
                .map(Employee::getAge)
                .reduce(0,Integer::sum);

        System.out.println(sumOfAllAges2);

        Integer sumOfAllAges3 = employees.stream()
                .reduce(0, (age, employee) -> age + employee.getAge(), Integer::sum);

        System.out.println(sumOfAllAges3);

        String allNames = employees.stream()
                .map(Employee::getFirstName)
                .reduce((name, name2) -> name + ", " + name2)
                .get();
        System.out.println(allNames);

    }

    @Test
    public void takeWhileOperations(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .takeWhile(employee -> employee.getAge()<30)
                .forEach(System.out::println);
    }

    @Test
    public void dropWhileOperations(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .dropWhile(employee -> employee.getAge()<30)
                .forEach(System.out::println);
    }

    @Test
    public void forEachOrdered(){
        String sentence = "Jak nauczyć się programowania?";
        sentence.chars().forEach(s->System.out.print((char) s));
        System.out.println();
        sentence.chars().parallel().forEach(s->System.out.print((char)s));
        System.out.println();
        sentence.chars().parallel().forEachOrdered(s->System.out.print((char)s));
    }

    @Test
    public void peekOperation(){
        List<Employee> newEmployees = employees.stream()
                .peek(employee -> employee.setFirstName("Kamil"))
                .collect(Collectors.toList());

        System.out.println(newEmployees);
        System.out.println();
        System.out.println(employees);
    }
}
