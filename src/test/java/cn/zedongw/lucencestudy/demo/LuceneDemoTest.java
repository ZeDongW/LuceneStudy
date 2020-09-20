package cn.zedongw.lucencestudy.demo;

import cn.zedongw.lucencestudy.entity.Article;
import org.junit.Test;

import static org.junit.Assert.*;

public class LuceneDemoTest {

    LuceneDemo luceneDemo = new LuceneDemo();

    @Test
    public void createIndexDb() throws Exception{
        Article article = new Article(1, "日记", "今天天气不错，适合出去晒晒太阳");
        luceneDemo.createIndexDb(article);
    }

    @Test
    public void findIndexDb() throws Exception{
        System.out.println(luceneDemo.findIndexDb("太阳"));
    }
}