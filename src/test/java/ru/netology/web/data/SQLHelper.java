package ru.netology.web.data;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
        public static Connection getConnection() {
                try {
                        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return null;
        }

        private final QueryRunner runner = new QueryRunner();
        private final Connection conn = getConnection();

        public static void cleanData() {
                val runner = new QueryRunner();
                val creditPayment = "DELETE FROM credit_request_entity;";
                val order = "DELETE FROM order_entity;";
                val debitPayment = "DELETE FROM payment_entity;";

                try {
                        val connection = DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
                        runner.update(connection, creditPayment);
                        runner.update(connection, order);
                        runner.update(connection, debitPayment);

                } catch (SQLException ex) {
                        System.out.println("SQLException message:" + ex.getMessage());
                }
        }

        @SneakyThrows
        public String getPaymentStatus() {
                val status = "SELECT status FROM payment_entity ORDER BY created DESC";
                return runner.query(conn, status, new ScalarHandler<>());
        }

        @SneakyThrows
        public String getPaymentId() {
                val id = "SELECT transaction_id FROM payment_entity ORDER BY created DESC";
                return runner.query(conn, id, new ScalarHandler<>());
        }

        @SneakyThrows
        public Integer getPaymentAmount() {
                val amount = "SELECT amount FROM payment_entity ORDER BY created DESC";
                return runner.query(conn, amount, new ScalarHandler<>());
        }

        @SneakyThrows
        public String getCreditRequestStatus() {
                val status = "SELECT status FROM credit_request_entity ORDER BY created DESC";
                return runner.query(conn, status, new ScalarHandler<>());
        }

        @SneakyThrows
        public String getCreditId() {
                val id = "SELECT credit_id FROM order_entity ORDER BY created DESC";
                return runner.query(conn, id, new ScalarHandler<>());
        }
        }
