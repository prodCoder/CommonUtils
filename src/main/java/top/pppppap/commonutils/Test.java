package top.pppppap.commonutils;


import org.apache.commons.beanutils.BeanUtils;
import top.pppppap.commonutils.DbUtils.DbUtils;
import top.pppppap.commonutils.DbUtils.QueryRunner;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * TODO
 *
 * @author pppppap
 * @since 2018-05-19 下午 13:26
 */

public class Test {
    public static void main(String[] args) {
        test();
    }


    public static void test() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from userinfo where id=?";
        try {
            Map<String, Object> map = queryRunner.getMap(DbUtils.getConnection(), sql, "10");
            Student student = new Student();
            BeanUtils.populate(student, map);
            System.out.println(student);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


}
