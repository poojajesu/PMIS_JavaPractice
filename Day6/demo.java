package Day6;

class Emp{
    double salary = 300000;
    
    }

class Manager extends Emp{
    double salary = 600000;

    void displaySalary(){
        System.out.println("Manager Salary " +salary );
        System.out.println("Employee Salary" + super.salary );
    }
}
