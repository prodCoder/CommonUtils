package top.pppppap.commonutils;


import top.pppppap.commonutils.BeanUtils.BeanUtils;
import top.pppppap.commonutils.BeanUtils.ReflectionUtils;
import top.pppppap.commonutils.DbUtils.DbUtils;
import top.pppppap.commonutils.DbUtils.QueryRunner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
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

    public static void test2() {
        Student student = new Student();
        Map map = new HashMap();
//        BeanUtils.mapToBean(student, map);
        for (Field f : ReflectionUtils.getDeclaredFields(student))
            System.out.println(f);
    }

    public static void test() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from userinfo where id=?";
        try {
            Map<String, Object> map = queryRunner.getMap(DbUtils.getConnection(), sql, "10");
            Student student = new Student();
//            BeanUtils.populate(student, map);
            System.out.println(map.get(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
