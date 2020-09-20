package cn.zedongw.lucencestudy.demo;

import cn.zedongw.lucencestudy.entity.Article;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LuceneDemo
 * @Description: Lucene演示类
 * @Author: ZeDongW
 * @Date: 2020/9/21 0021 5:21
 * @Version: v1.0
 * @Modified By:
 * @Modified Time:
 **/
public class LuceneDemo {

    /**
     * Description: 创建lucene索引库
     * @methodName: createIndexDb
     * @param article 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/9/21 0021 5:27
     */
    public void createIndexDb(Article article) throws Exception{
        //创建document对象
        Document document = new Document();

        //将article对象属性封装到document对象中
        document.add(new Field("id", String.valueOf(article.getId()), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("tittle", article.getTittle(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("content", article.getContent(), Field.Store.YES, Field.Index.ANALYZED));

        //创建directory对象
        Directory directory = FSDirectory.open(new File("./IndexDB"));

        //设置Lucene版本
        Version version = Version.LUCENE_30;

        //创建analyzer对象
        Analyzer analyzer = new StandardAnalyzer(version);

        //文本拆分词汇量
        IndexWriter.MaxFieldLength maxFieldLength = IndexWriter.MaxFieldLength.LIMITED;

        //创建IndexWriter对象
        IndexWriter indexWriter = new IndexWriter(directory, analyzer, maxFieldLength);

        //将document对象写入Lucene索引库
        indexWriter.addDocument(document);

        //关闭IndexWriter
        indexWriter.close();
    }

    /**
     * Description: 根据关键词搜索Lucene
     * @methodName: findIndexDb
     * @param keyWords 1
     * @throws
     * @return: java.util.List<cn.zedongw.lucencestudy.entity.Article>
     * @author: ZeDongW
     * @date: 2020/9/21 0021 5:54
     */
    public List<Article> findIndexDb(String keyWords) throws Exception{
        //创建Article集合对象
        ArrayList<Article> articleList = new ArrayList<>();

        //创建directory对象
        Directory directory = FSDirectory.open(new File("./IndexDB"));

        //创建IndexSearch对象
        IndexSearcher indexSearcher = new IndexSearcher(directory);

        //设置Lucene版本
        Version version = Version.LUCENE_30;

        //创建analyzer对象
        Analyzer analyzer = new StandardAnalyzer(version);

        //创建QueryParser对象
        QueryParser queryParser = new QueryParser(version, "content", analyzer);

        //QuryParser对象转换关键词为Query对象
        Query query = queryParser.parse(keyWords);

        //搜索条数
        int maxRecord = 100;

        //搜素结果集合
        TopDocs docs = indexSearcher.search(query, maxRecord);

        //迭代词汇表中符合条件的编号
        for (int i = 0; i < docs.scoreDocs.length; i++) {
            //创建article对象
            Article article = new Article();

            //取出封装编号和分数的ScoreDoc对象
            ScoreDoc scoreDoc = docs.scoreDocs[i];

            //获取scoreDoc编号
            int no = scoreDoc.doc;

            //根据scoreDoc编号获取Docunment对象
            Document document = indexSearcher.doc(no);

            //封装Article属性
            article.setId(Integer.parseInt(document.get("id")));
            article.setTittle(document.get("tittle"));
            article.setContent(document.get("content"));

            //将article放入集合中
            articleList.add(article);
        }

        //关闭IndexSearch
        indexSearcher.close();

        //返回集合
        return articleList;
    }
}
