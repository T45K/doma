/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.internal.jdbc.command;

import static org.seasar.doma.internal.util.AssertionUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.seasar.doma.internal.jdbc.query.ModifyQuery;
import org.seasar.doma.internal.jdbc.sql.PreparedSql;
import org.seasar.doma.internal.jdbc.util.JdbcUtil;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.seasar.doma.jdbc.SqlExecutionException;
import org.seasar.doma.jdbc.UniqueConstraintException;
import org.seasar.doma.jdbc.dialect.Dialect;

/**
 * @author taedium
 * 
 */
public abstract class ModifyCommand<Q extends ModifyQuery> implements
        Command<Integer, Q> {

    protected final Q query;

    protected final PreparedSql sql;

    public ModifyCommand(Q query) {
        assertNotNull(query);
        this.query = query;
        this.sql = query.getSql();
    }

    public Integer execute() {
        if (!query.isExecutable()) {
            JdbcLogger logger = query.getConfig().getJdbcLogger();
            logger.logSqlExecutionSkipping(query.getClassName(), query
                    .getMethodName(), query.getSqlExecutionSkipCause());
            return 0;
        }
        Connection connection = JdbcUtil.getConnection(query.getConfig()
                .getDataSource());
        try {
            PreparedStatement preparedStatement = JdbcUtil.prepareStatement(
                    connection, sql.getRawSql(), query
                            .isAutoGeneratedKeysSupported());
            try {
                log();
                setupOptions(preparedStatement);
                bindValues(preparedStatement);
                return executeInternal(preparedStatement);
            } catch (SQLException e) {
                Dialect dialect = query.getConfig().getDialect();
                throw new SqlExecutionException(sql, e, dialect.getRootCause(e));
            } finally {
                JdbcUtil.close(preparedStatement, query.getConfig()
                        .getJdbcLogger());
            }
        } finally {
            JdbcUtil.close(connection, query.getConfig().getJdbcLogger());
        }
    }

    protected abstract int executeInternal(PreparedStatement preparedStatement)
            throws SQLException;

    protected void log() {
        JdbcLogger logger = query.getConfig().getJdbcLogger();
        logger.logSql(query.getClassName(), query.getMethodName(), sql);
    }

    protected void setupOptions(PreparedStatement preparedStatement)
            throws SQLException {
        if (query.getQueryTimeout() > 0) {
            preparedStatement.setQueryTimeout(query.getQueryTimeout());
        }
    }

    protected void bindValues(PreparedStatement preparedStatement)
            throws SQLException {
        PreparedSqlParameterBinder binder = new PreparedSqlParameterBinder(
                query);
        binder.bind(preparedStatement, sql.getParameters());
    }

    protected int executeUpdate(PreparedStatement preparedStatement)
            throws SQLException {
        try {
            int updatedRows = preparedStatement.executeUpdate();
            validateRows(updatedRows);
            return updatedRows;
        } catch (SQLException e) {
            Dialect dialect = query.getConfig().getDialect();
            if (dialect.isUniqueConstraintViolated(e)) {
                throw new UniqueConstraintException(sql, e);
            }
            throw e;
        }
    }

    protected void validateRows(int rows) {
        if (query.isOptimisticLockCheckRequired() && rows == 0) {
            throw new OptimisticLockException(sql);
        }
    }
}
