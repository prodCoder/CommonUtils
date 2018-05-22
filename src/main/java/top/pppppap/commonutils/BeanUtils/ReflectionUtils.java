package top.pppppap.commonutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author pppppap
 * @since 2018-05-20 上午 11:20
 */

public class ReflectionUtils {
    /**
     * 从子类向父类中遍历获取指定的属性
     *
     * @param
     * @return
     **/
    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                //这里什么都不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会进入
            }
        }
        return null;
    }

    /**
     * 从子类向父类中遍历获取指定的方法
     *
     * @param
     * @return
     **/
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Method method = null;

        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
                //这里甚么都不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会进入
            }
        }

        return null;
    }

    /**
     * 得到Bean的所有属性，包括父类
     *
     * @param
     * @return
     **/
    public static List<Field> getDeclaredFields(Object bean) {
        List<Field> fieldList = new ArrayList<Field>();
        for (Class<?> clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        return fieldList;
    }
}
