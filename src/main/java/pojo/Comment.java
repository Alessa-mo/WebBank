package pojo;

// 数据库表条目对象
//mysql构建:
//create table comment (
//        comment_id int not null primary key auto_increment,
//        comment_name varchar(20) not null,
//        comment_goods int not null,
//        comment_tags json not null,
//        comment_text varchar(1024) not null
//        );

import com.alibaba.fastjson.JSON;

public class Comment {
    protected Integer commentID;
    protected String commentName;
    protected Integer commentGoods;
    protected JSON commentTags;
    protected String commentText;

    public Comment(){}

    public Comment(Integer commentID, String commentName, Integer commentGoods, JSON commentTags, String commentText) {
        this.commentID = commentID;
        this.commentName = commentName;
        this.commentGoods = commentGoods;
        this.commentTags = commentTags;
        this.commentText = commentText;
    }

    public Comment(String commentName, Integer commentGoods, JSON commentTags, String commentText) {
        this.commentName = commentName;
        this.commentGoods = commentGoods;
        this.commentTags = commentTags;
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", commentName='" + commentName + '\'' +
                ", commentGoods=" + commentGoods +
                ", commentTags=" + commentTags.toString() +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
