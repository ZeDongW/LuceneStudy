package cn.zedongw.lucencestudy.entity;

/**
 * @ClassName: Article
 * @Description: 文本实体类
 * @Author: ZeDongW
 * @Date: 2020/9/21 0021 5:22
 * @Version: v1.0
 * @Modified By:
 * @Modified Time:
 **/
public class Article {

    /**
     * 文章ID
     */
    private int id;

    /**
     * 文章标题
     */
    private String tittle;

    /**
     * 文章内容
     */
    private String content;

    public Article() {
    }

    public Article(int id, String tittle, String content) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
