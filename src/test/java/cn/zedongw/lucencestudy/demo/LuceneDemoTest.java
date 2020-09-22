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

    @Test
    public void createIndexDbByUtils() throws Exception{
//        Article article = new Article(3, "笔记", "化学笔记");
//        Article article = new Article(4, "笔记", "生物笔记");
        Article article = new Article(5, "笔记", "物理笔记");
        luceneDemo.createIndexDbByUtils(article);
    }

    @Test
    public void findIndexDbByUtils() throws Exception{
        System.out.println(luceneDemo.findIndexDbByUtils("物理"));
    }
}