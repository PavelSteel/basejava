package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public <T> T execute(String sql, SqlExecutor<T> executor) {
        try (Connection con = connectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw ExeptionUtil.convertExeption(e);
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> executor) {
        try (Connection con = connectionFactory.getConnection()) {
            try {
                con.setAutoCommit(false);
                T res = executor.execute(con);
                con.commit();
                return res;
            } catch (SQLException e) {
                con.rollback();
                throw ExeptionUtil.convertExeption(e);
            }
        } catch (SQLException e){
            throw new StorageException(e);
        }

    }
}
