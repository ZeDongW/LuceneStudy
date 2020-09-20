package cn.zedongw.lucencestudy.utils;

import cn.zedongw.lucencestudy.entity.Article;
import org.apache.lucene.document.Document;
import org.junit.Test;

import static org.junit.Assert.*;

public class LuceneUtilsTest {

    @Test
    public void testUtils() throws Exception{
        Article article = new Article(2, "笔记", "勤做笔记能更快的掌握新的知识");
        Document document = LuceneUtils.javaBean2Document(article);
        Article article1 = LuceneUtils.document2JavaBean(document, Article.class);
        System.out.println(article1);
    }

}