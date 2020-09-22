package cn.zedongw.lucencestudy.json;

import net.sf.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: JsonDemo
 * @Description: JSON演示
 * @Author: ZeDongW
 * @Date: 2020/9/22 0022 22:37
 * @Version: v1.0
 * @Modified By:
 * @Modified Time:
 **/
public class JsonDemo {

    /**
     * Description: 将list集合转化为JSON字符串
     * @methodName: list2Json
     * @param list 1
     * @throws
     * @return: java.lang.String
     * @author: ZeDongW
     * @date: 2020/9/22 0022 22:44
     */
    public String list2Json(List list){
        JSONArray jsonArray = JSONArray.fromObject(list);
	    return jsonArray.toString();
    }

    /**
     * Description: 将javaBeanList转化为JSON字符串
     * @methodName: javaBeanList2Json
     * @param list 1
     * @throws
     * @return: java.lang.String
     * @author: ZeDongW
     * @date: 2020/9/22 0022 22:46
     */
    public String javaBeanList2Json(List list){
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    /**
     * Description: 将javaBean转化为JSON字符串
     * @methodName: javaBean2Json
     * @param obj 1
     * @throws
     * @return: java.lang.String
     * @author: ZeDongW
     * @date: 2020/9/22 0022 22:41
     */
    public String javaBean2Json(Object obj){
        JSONArray jsonArray = JSONArray.fromObject(obj);
        return jsonArray.toString();
    }

    /**
     * Description: 将Map转化为JSON字符串
     * @methodName: map2Json
     * @param map 1
     * @throws
     * @return: java.lang.String
     * @author: ZeDongW
     * @date: 2020/9/22 0022 22:50
     */
    public String map2Json(Map map){
        JSONArray jsonArray = JSONArray.fromObject(map);
        return jsonArray.toString();
    }
}
