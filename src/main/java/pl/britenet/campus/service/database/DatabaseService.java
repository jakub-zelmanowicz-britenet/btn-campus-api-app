package pl.britenet.campus.service.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {

    private final HikariDataSource dataSource;

    public DatabaseService() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc://localhost:3306/shop");
        config.setUsername("root");
        config.setPassword("");
        this.dataSource = new HikariDataSource(config);
    }

    public void performDML(String dmlQuery) {
        try (Connection connection = this.dataSource.getConnection() ;
             PreparedStatement statement = connection.prepareStatement(dmlQuery)) {

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public<T> T performQuery(String sqlQuery, ResultParser<T> parser) {
        try (Connection connection = this.dataSource.getConnection() ;
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            ResultSet resultSet = statement.executeQuery();
            return parser.parse(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
