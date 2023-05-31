import java.sql.*;

public class Application {
    final String user = "postgres";
    final String password = "3452Ivan";
    final String url = "jdbc:postgresql://localhost:5432/skypro";
    public void add(Employee employee) {
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO  employee (first_name, last_name, gender, age, city_id) VALUES ((?),(?), (?), (?), (?))");){
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCity_id());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "3452Ivan";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM employee WHERE id = (?)")){
    statement.setInt(1,2);
    final ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()){
        String name = "Name: " + resultSet.getString("first_name");
        String surname = "Surname: " + resultSet.getString("last_name");
        String gender = "Gender: " + resultSet.getString(4);
        int age = resultSet.getInt(5);
        System.out.println(name);
        System.out.println(surname);
        System.out.println(gender);
        System.out.println("Age " + age);
            }
        }
    }
}