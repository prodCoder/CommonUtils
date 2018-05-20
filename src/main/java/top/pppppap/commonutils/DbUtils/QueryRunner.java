package top.pppppap.commonutils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * sql语句执行类
 *
 * @author pppppap
 * @since 2018-05-19 下午 23:53
 */

public class QueryRunner extends AbstractQueryRunner {
    /**
     * 查询方法，返回List
     *
     * @param connection
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     **/
    public List<Map<String, Object>> getList(Connection connection, String sql, Object... params) throws SQLException {
        if (connection == null) {
            throw new SQLException("Null connection");
        }
        if (sql == null || "".equals(sql)) {
            DbUtils.close(connection);
            throw new SQLException("Null SQL statement");
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Map<String, Object>> list = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement = fillStatement(preparedStatement, params);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            list = new ArrayList<Map<String, Object>>();
            while (resultSet.next()) {
                Map<String, Object> m = new HashMap<String, Object>();
                for (int i = 1; i < resultSetMetaData.getColumnCount(); i++) {
                    m.put(resultSetMetaData.getColumnName(i), resultSet.getString(i));
                }
                list.add(m);
            }
        } catch (SQLException e) {
            this.rethrow(e, sql, params);
        } finally {
            DbUtils.close(resultSet, preparedStatement, connection);
        }
        return list;
    }

    /**
     * 查询方法，返回List
     *
     * @param connection
     * @param sql
     * @return
     **/
    public List<Map<String, Object>> getList(Connection connection, String sql) throws SQLException {
        return this.getList(connection, sql, (Object[]) null);
    }

    /**
     * 查询方法，得到一条数据Map
     *
     * @param connection
     * @param sql
     * @param params
     * @return
     **/
    public Map<String, Object> getMap(Connection connection, String sql, Object... params) throws SQLException {
        if (connection == null) {
            throw new SQLException("Null connection");
        }

        if (sql == null || "".equals(sql)) {
            DbUtils.close(connection);
            throw new SQLException("Null SQL statement");
        }

        List<Map<String, Object>> list = this.getList(connection, sql, params);
        if (list != null && !list.isEmpty()) {
            if (list.size() > 1) {
                throw new SQLException("the result is not unique");
            }
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询方法，得到一条数据Map
     *
     * @param connection
     * @param sql
     * @return
     * @throws SQLException
     **/
    public Map<String, Object> getMap(Connection connection, String sql) throws SQLException {
        return this.getMap(connection, sql, (Object[]) null);
    }

    /**
     * 插入语句
     *
     * @param connection
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     **/
    public ResultSet insert(Connection connection, String sql, Object... params) throws SQLException {
        if (connection == null) {
            throw new SQLException("Null connection");
        }
        if (sql == null || "".equals(sql)) {
            DbUtils.close(connection);
            throw new SQLException("Null SQL statement");
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rows = 0;

        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            this.fillStatement(preparedStatement, sql, params);
            rows = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException e) {
            this.rethrow(e, sql, params);
        } finally {
            DbUtils.close(preparedStatement, connection);
        }
        return resultSet;
    }

    /**
     * 插入语句
     *
     * @param connection
     * @param sql
     * @return
     **/
    public ResultSet insert(Connection connection, String sql) throws SQLException {
        return this.insert(connection, sql, (Object[]) null);
    }

    /**
     * 可执行SQL INSERT, UPDATE, or DELETE 语句
     *
     * @param connection
     * @param sql
     * @param params
     * @return The number of rows updated.
     * @throws SQLException
     */
    private int update(Connection connection, String sql, Object... params) throws SQLException {
        if (connection == null) {
            throw new SQLException("Null connection");
        }

        if (sql == null) {
            DbUtils.close(connection);
            throw new SQLException("Null SQL statement");
        }

        PreparedStatement preparedStatement = null;
        int rows = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement = this.fillStatement(preparedStatement, sql, params);
            rows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            this.rethrow(e, sql, params);

        } finally {
            try {
                DbUtils.close(preparedStatement);
            } finally {
                DbUtils.close(connection);
            }
        }

        return rows;
    }

    /**
     * 可执行SQL INSERT, UPDATE, or DELETE 语句
     *
     * @param conn
     * @param sql
     * @param param
     * @return The number of rows updated.
     * @throws SQLException
     */
    public int update(Connection conn, String sql, Object param) throws SQLException {
        return this.update(conn, sql, new Object[]{param});
    }
}
