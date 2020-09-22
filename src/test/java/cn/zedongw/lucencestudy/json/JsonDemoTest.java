package cn.zedongw.lucencestudy.json;

import cn.zedongw.lucencestudy.entity.Article;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JsonDemoTest {

    JsonDemo jsonDemo = new JsonDemo();

    @Test
    public void arry2Json() {
        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("234");
        list.add("456");
        String json = jsonDemo.list2Json(list);
        System.out.println(json);
    }

    @Test
    public void javaBean2Json() {
        Article article = new Article(1, "sda", "awsdfsadf");
        String json = jsonDemo.javaBean2Json(article);
        System.out.println(json);
    }

    @Test
    public void javaBeanList2Json() {
        ArrayList<Article> list = new ArrayList<>();
        list.add(new Article(1, "sda", "awsdfsadf"));
        list.add(new Article(2, "sda", "awsdfsadf"));
        list.add(new Article(3, "sda", "awsdfsadf"));
//        String json = jsonDemo.javaBeanList2Json(list);
//        [{"id":1,"content":"awsdfsadf","tittle":"sda"},{"id":2,"content":"awsdfsadf","tittle":"sda"},{"id":3,"content":"awsdfsadf","tittle":"sda"}]
//        [{"id":1,"content":"awsdfsadf","tittle":"sda"},{"id":2,"content":"awsdfsadf","tittle":"sda"},{"id":3,"content":"awsdfsadf","tittle":"sda"}]
        String json = jsonDemo.list2Json(list);
        System.out.println(json);
    }

    @Test
    public void map2Json() {
        ArrayList<Article> list = new ArrayList<>();
        list.add(new Article(1, "sda", "awsdfsadf"));
        list.add(new Article(2, "sda", "awsdfsadf"));
        list.add(new Article(3, "sda", "awsdfsadf"));

        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("total",list.size());
        map.put("rows",list);
        String json = jsonDemo.map2Json(map);
        System.out.println(json);
        System.out.println(json.substring(1,json.length()-1));

    }
}