package top.pppppap.commonutils.BeanUtils;

import java.lang.reflect.Field;
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
    public static void mapToBean(Object bean, Map<String, ? extends Object> map) {
        Field[] fileds=bean.getClass().getDeclaredFields();
        for(Field f:fileds)
            System.out.println(f);
    }

    /**
     * 把JavaBean转换为Map
     *
     * @param bean JavaBean
     * @param map  Map保存了属性值
     **/
    public static void beanToMap(Object bean, Map<String, ? extends Object> map) {

    }

    /**
     * 把JavaBean集合转换为Map集合
     *
     * @param beanList
     * @param mapList
     **/
    public static void mapListToBeanList(List<Object> beanList, List<Map<String, ? extends Object>> mapList) {

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
