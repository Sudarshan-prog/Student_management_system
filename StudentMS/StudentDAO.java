package StudentMS;
import java.sql.*;
import java.util.*;

public class StudentDAO {

    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS students (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(100) NOT NULL,
                age INT,
                course VARCHAR(100),
                marks DOUBLE
            )""";
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void addStudent(Student st) {
        String sql = "INSERT INTO students (name, age, course, marks) VALUES (?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, st.getName());
            ps.setInt(2, st.getAge());
            ps.setString(3, st.getCourse());
            ps.setDouble(4, st.getMarks());
            ps.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("id"), rs.getString("name"),
                    rs.getInt("age"), rs.getString("course"),
                    rs.getDouble("marks")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Student searchById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("id"), rs.getString("name"),
                    rs.getInt("age"), rs.getString("course"),
                    rs.getDouble("marks")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void updateStudent(Student st) {
        String sql = "UPDATE students SET name=?, age=?, course=?, marks=? WHERE id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, st.getName());
            ps.setInt(2, st.getAge());
            ps.setString(3, st.getCourse());
            ps.setDouble(4, st.getMarks());
            ps.setInt(5, st.getId());
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Updated!" : "Student not found.");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Deleted!" : "Student not found.");
        } catch (SQLException e) { e.printStackTrace(); }
    }
}