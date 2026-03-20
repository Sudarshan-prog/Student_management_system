package StudentMS;

public class StudentService {
    private final StudentDAO dao = new StudentDAO();

    public void add(String name, int age, String course, double marks) {
        if (name == null || name.isBlank()) {
            System.out.println("Name cannot be empty!"); return;
        }
        if (age < 5 || age > 100) {
            System.out.println("Invalid age!"); return;
        }
        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be 0–100!"); return;
        }
        dao.addStudent(new Student(0, name, age, course, marks));
    }

    public void listAll() {
        var list = dao.getAllStudents();
        if (list.isEmpty()) { System.out.println("No students found."); return; }
        System.out.printf("%-5s %-20s %-5s %-20s %s%n", "ID","Name","Age","Course","Marks");
        System.out.println("-".repeat(60));
        list.forEach(System.out::println);
    }

    public void search(int id) {
        var s = dao.searchById(id);
        System.out.println(s != null ? s : "Not found.");
    }

    public void update(int id, String name, int age, String course, double marks) {
        dao.updateStudent(new Student(id, name, age, course, marks));
    }

    public void delete(int id) { dao.deleteStudent(id); }
}