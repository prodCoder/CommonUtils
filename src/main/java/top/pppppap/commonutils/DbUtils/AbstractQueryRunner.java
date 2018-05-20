package top.pppppap.commonutils.DbUtils;

import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * TODO
 *
 * @author pppppap
 * @since 2018-05-19 下午 19:29
 */

public abstract class AbstractQueryRunner {
    /**
     * 给preparedStatement设置参数
     *
     * @param preparedStatement
     * @param params
     * @return
     * @throws SQLException
     **/
    public PreparedStatement fillStatement(PreparedStatement preparedStatement, Object... params) throws SQLException {
        try {
            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
            int stmtCount = parameterMetaData.getParameterCount();
            int paramsCount = params == null ? 0 : params.length;
            if (stmtCount != paramsCount) {
                throw new SQLException("Wrong number of parameters: expected "
                        + stmtCount + ", was given " + paramsCount);
            }


            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    if (params[i] != null) {
                        preparedStatement.setObject(i + 1, params[i]);
                    } else {
                        int sqlType = parameterMetaData.getParameterType(i + 1);
                        preparedStatement.setNull(i + 1, sqlType);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * 抛出自定义异常信息
     *
     * @param
     * @return
     **/
    protected void rethrow(SQLException cause, String sql, Object... params)
            throws SQLException {
        String causeMessage = cause.getMessage();
        if (causeMessage == null) {
            causeMessage = "";
        }
        StringBuffer msg = new StringBuffer(causeMessage);

        msg.append(" Query: ");
        msg.append(sql);
        msg.append(" Parameters: ");

        if (params == null) {
            msg.append("[]");
        } else {
            msg.append(Arrays.deepToString(params));
        }

        SQLException e = new SQLException(msg.toString(), cause.getSQLState(),
                cause.getErrorCode());
        e.setNextException(cause);

        throw e;
    }
}
