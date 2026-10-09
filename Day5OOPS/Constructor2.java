package Day5OOPS;

class StudentProfile {

    String name;
    int id;
    double score;

    StudentProfile(String name, int id, double score) {
        this.name = name;
        this.id = id;
        this.score = score;
    }

    StudentProfile(String name, int id) {
        this.name = name;
        this.id = id;
        this.score = 0.0;
    }

    String getGrade() {
        if (score >= 90) {
            return "A";
        } else if (score >= 75) {
            return "B";
        } else if (score >= 50) {
            return "C";
        } else {
            return "F";
        }
    }

    void displayReport() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Score: " + score);
        System.out.println("Grade: " + getGrade());
        System.out.println();
    }
}

public class Constructor2 {

    public static void main(String[] args) {

        StudentProfile student1 =
                new StudentProfile("Pooja", 101, 82.5);

        StudentProfile student2 =
                new StudentProfile("Harshada", 102);

        student1.displayReport();
        student2.displayReport();
    }
}