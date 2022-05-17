package ru.netology.web.data;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
        static String URL = "jdbc:mysql://localhost:3306/app";
                static String user = "app";
                static String password = "pass";

        public static void cleanData() {
                val runner = new QueryRunner();
                val creditPayment = "DELETE FROM credit_request_entity;";
                val order = "DELETE FROM order_entity;";
                val debitPayment = "DELETE FROM payment_entity;";

                try {
                        val connection = DriverManager.getConnection(
                                URL, user, password);
                        runner.update(connection, creditPayment);
                        runner.update(connection, order);
                        runner.update(connection, debitPayment);

                } catch (SQLException ex) {
                        System.out.println("SQLException message:" + ex.getMessage());
                }
        }
        }
