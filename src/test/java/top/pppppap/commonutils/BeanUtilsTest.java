package top.pppppap.commonutils;

import top.pppppap.commonutils.BeanUtils.BeanUtils;
import top.pppppap.commonutils.DbUtils.DbUtils;
import top.pppppap.commonutils.DbUtils.QueryRunner;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Map;

public class BeanUtilsTest {

    public static void mapToBeanTest() throws NoSuchFieldException, IllegalAccessException{
        Student student=new Student();
        QueryRunner runner=new QueryRunner();
        String sql="SELECT * FROM userinfo WHERE id=?";
        Map info=null;
        try {
            info=runner.getMap(DbUtils.getConnection(),sql,10);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Object obj=new Object();
        BeanUtils.mapToBean(student,info);
        System.out.println(student);
    }

    public static void beanToMapTest() throws NoSuchFieldException, IllegalAccessException {
        Student student=new Student();
        student.setUser_id("1");
        student.setUserinfo_birth("2000-01-01");
        student.setUserinfo_email("855555@163.com");
        student.setUserinfo_name("陈关系");
        student.setUserinfo_sex("男");
        student.setUserinfo_tel("13558589732");
        student.setId("10");

        Map<String,Object> info=null;
        BeanUtils.beanToMap(student,info);
        System.out.println(info.get("userinfo_name"));
        System.out.println(info.get("userinfo_sex"));
        System.out.println(info.get("userinfo_birth"));
        System.out.println(info.get("userinfo_tel"));
        System.out.println(info.get("userinfo_email"));
        System.out.println(info.get("user_id"));


    }
    public static void main(String args[]) throws NoSuchFieldException, IllegalAccessException {
        mapToBeanTest();
    }

}
