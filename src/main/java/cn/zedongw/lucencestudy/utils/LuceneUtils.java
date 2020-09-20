package cn.zedongw.lucencestudy.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.document.Field;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName: LuceneUtils
 * @Description: Lucene工具类
 * @Author: ZeDongW
 * @Date: 2020/9/21 0021 6:25
 * @Version: v1.0
 * @Modified By:
 * @Modified Time:
 **/
public class LuceneUtils <T>{

    /**
     * lucene版本号
     */
    private static Version version;

    /**
     * lucene索引库目录
     */
    private static Directory directory;

    /**
     * lucene分词器
     */
    private static Analyzer analyzer;

    /**
     * lucene分词最大词汇量
     */
    private static IndexWriter.MaxFieldLength maxFieldLength;

    //静态代码块初始化成员变量
    static {
        try {
            version = Version.LUCENE_30;
            directory = FSDirectory.open(new File("./IndexDB"));
            analyzer = new StandardAnalyzer(version);
            maxFieldLength = IndexWriter.MaxFieldLength.LIMITED;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 私有化构造函数
     * @methodName: LuceneUtils
     * @param
     * @throws
     * @return:
     * @author: ZeDongW
     * @date: 2020/9/21 0021 7:23
     */
    private LuceneUtils(){};

    /**
     * Description: 将JavaBean对象封装为Document对象
     * @methodName: javaBean2Document
     * @param t 1
     * @throws
     * @return: org.apache.lucene.document.Document
     * @author: ZeDongW
     * @date: 2020/9/21 0021 7:05
     */
    public static <T> Document javaBean2Document(T t) throws Exception{
        //创建Document对象
        Document document = new Document();

        //获取字节码对象
        Class<?> clazz = t.getClass();

        //获取javaBean的对象字节码
        java.lang.reflect.Field[] declaredFields = clazz.getDeclaredFields();

        //get方法名拼接
        StringBuilder sb = new StringBuilder();

        //遍历属性
        for (java.lang.reflect.Field declaredField : declaredFields) {
            sb.setLength(0);
            sb.append("get");

            //强制使用私有属性
            declaredField.setAccessible(true);

            //获取属性名
            String name = declaredField.getName();

            //根据属性名拼接GET方法
            String methodName = sb.append(name.substring(0,1).toUpperCase()).append(name.substring(1)).toString();

            //根据方法名获取方法
            Method method = clazz.getMethod(methodName);

            //通过反射调用方法
            String value = method.invoke(t).toString();

            //将对象属性封装道document对象中
            document.add(new Field(name, value, Field.Store.YES, Field.Index.ANALYZED));
        }

        //返回document对象
        return document;
    }

    public static <T> T document2JavaBean(Document document, Class<T> clazz) throws Exception{
        //根据字节码创建对象
        T t = clazz.newInstance();

        //获取对象属性
        java.lang.reflect.Field[] declaredFields = clazz.getDeclaredFields();

        //遍历属性
        for (java.lang.reflect.Field declaredField : declaredFields) {
            //强制使用私有属性
            declaredField.setAccessible(true);
            //获取属性名
            String name = declaredField.getName();
            //根据属性名获取值
            String value = document.get(name);
            //将属性值拷贝到对象中
            BeanUtils.setProperty(t,name,value);
        }
        //返回对象
        return t;
    }
}
