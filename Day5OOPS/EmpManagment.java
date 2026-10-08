package Day5OOPS;

class Employee {

    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;

        if (salary >= 0) {
            this.salary = salary;
        } else {
            this.salary = 0.0;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Error: Salary cannot be negative.");
        }
    }

    public void giveRaise(double percent) {
        if (percent > 0) {
            double raiseAmount = this.salary * (percent / 100.0);
            this.salary += raiseAmount;

            System.out.println(name + " received a " + percent + "% raise.");
            System.out.println("New Salary: ₹" + this.salary);
        } else {
            System.out.println("Raise percentage must be positive.");
        }
    }
}

public class EmpManagment {

    public static void main(String[] args) {

        Employee emp = new Employee(101, "Harshada", 50000.0);

        System.out.println("Initial Salary: ₹" + emp.getSalary());

        emp.giveRaise(8);

        emp.setSalary(-25000);

        System.out.println("Final Verified Salary: ₹" + emp.getSalary());
    }
}