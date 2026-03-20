package StudentMS;  // ← this was missing!

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static StudentService service = new StudentService();
    static StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {
        dao.createTable();
        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Choice: ");
            switch (sc.nextLine().trim()) {
                case "1" -> addStudent();
                case "2" -> service.listAll();
                case "3" -> { System.out.print("Enter ID: ");
                              service.search(Integer.parseInt(sc.nextLine())); }
                case "4" -> updateStudent();
                case "5" -> { System.out.print("Enter ID to delete: ");
                              service.delete(Integer.parseInt(sc.nextLine())); }
                case "6" -> { System.out.println("Bye!"); return; }
                default  -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Name: ");   String name  = sc.nextLine();
        System.out.print("Age: ");    int age       = Integer.parseInt(sc.nextLine());
        System.out.print("Course: "); String course = sc.nextLine();
        System.out.print("Marks: ");  double marks  = Double.parseDouble(sc.nextLine());
        service.add(name, age, course, marks);
    }

    static void updateStudent() {
        System.out.print("ID to update: "); int id        = Integer.parseInt(sc.nextLine());
        System.out.print("New Name: ");     String name   = sc.nextLine();
        System.out.print("New Age: ");      int age       = Integer.parseInt(sc.nextLine());
        System.out.print("New Course: ");   String course = sc.nextLine();
        System.out.print("New Marks: ");    double marks  = Double.parseDouble(sc.nextLine());
        service.update(id, name, age, course, marks);
    }
}