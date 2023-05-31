import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "3452Ivan";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("" +
                     "SELECT * FROM employee WHERE id = (?)")) {
            statement.setInt(1, 2);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = "Name: " + resultSet.getString("first_name");
                String surname = "Surname: " + resultSet.getString("last_name");
                String gender = "Gender: " + resultSet.getString(4);
                int age = resultSet.getInt(5);
                System.out.println(name);
                System.out.println(surname);
                System.out.println(gender);
                System.out.println("Age " + age);
            }

            EmployeeDao employeeDao = new RealizationEmployeeDao();
            System.out.println(employeeDao.getAllEmployee());
            Employee employee = new Employee(2, "Rob", "Lukin", "m", 31, new City(2, "London"));
            employeeDao.add(employee);
            System.out.println(employeeDao.getAllEmployee());
            System.out.println();
            employee.setLast_name("Kuzin");
            employeeDao.updateEmployee(8, employee);
            System.out.println(employeeDao.getById(8));
            employeeDao.deleteEmployee(4);
            System.out.println();
            System.out.println(employeeDao.getAllEmployee());
        }
    }
}