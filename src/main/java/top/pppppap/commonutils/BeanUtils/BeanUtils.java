package top.pppppap.commonutils.BeanUtils;

import top.pppppap.commonutils.Student;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author pppppap
 * @since 2018-05-20 上午 9:55
 */

public class BeanUtils {
    /**
     * 把Map转换为JavaBean
     *
     * @param bean JavaBean
     * @param map  Map保存了属性值
     **/

    public static void mapToBean(Object bean, Map<String, ? extends Object> map) throws IllegalAccessException {
        Field[] fields=bean.getClass().getDeclaredFields();
//        for(Field f:fileds){
//             System.out.println(f);
//        }
        for(int i=0;i<fields.length;i++){
            Field field=fields[i];
            autoAssignValueForField(field, (String) map.get(field.getName()),bean);
        }
    }

    /*
    * 根据Field对象的类型不同而调用不同的setXxx()方法赋值
    *
    *@param field  需要赋值的Field对象
    *
    *@param value 值
    *
    *@param object对象
    * */
    public static void autoAssignValueForField(Field field,String value,Object object) throws IllegalAccessException {
        String type=field.getGenericType().toString();
        field.setAccessible(true);
        switch(type){
            case "int":field.setInt(object,Integer.parseInt(value));break;
            case "float":field.setFloat(object,Float.parseFloat(value));break;
            case "double":field.setDouble(object,Double.parseDouble(value));break;
            case "byte":field.setByte(object,Byte.parseByte(value));break;
            case "short":field.setShort(object,Short.parseShort(value));break;
            case "long":field.setLong(object, Long.parseLong(value));break;
            case "boolean":field.setBoolean(object, Boolean.parseBoolean(value));break;
            case "char":field.setChar(object, value.charAt(0));break;
            default:field.set(object,value);break;
        }
    }

    /**
     * 把JavaBean转换为Map
     *
     * @param bean JavaBean
     * @param map  Map保存了属性值
     **/
    public static void beanToMap(Object bean, Map<String, Object> map) throws IllegalAccessException {
        Class<?> clazz=bean.getClass();
        Field[] fields=clazz.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            Field field=fields[i];
            map.put(field.getName(),field.get(bean));
        }
    }

    /**
     * 把Map集合转换为JavaBean集合
     *
     * @param beanList bean集合 空的
     * @param mapList map集合 里面有数据
     **/
    public static void mapListToBeanList(List<Object> beanList, List<Map<String, ? extends Object>> mapList) throws IllegalAccessException {

        //        for(Map map:mapList){
//            Object add=new Object();//怎么初始化
//            mapToBean(add,map);
//        }
    }
    /**
     * 把JavaBean集合转换为Map集合
     *
     * @param beanList
     * @param mapList
     **/
    public static void beanListToMapList(List<Object> beanList, List<Map<String, ? extends Object>> mapList) {

    }

}
