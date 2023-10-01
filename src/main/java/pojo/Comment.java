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

public class Comment {
    protected Integer commentID;
    protected String commentName;
    protected Integer commentGoods;
    protected String commentTags;
    protected String commentText;

    public Comment(Integer commentID, String commentName, Integer commentGoods, String commentTags, String commentText) {
        this.commentID = commentID;
        this.commentName = commentName;
        this.commentGoods = commentGoods;
        this.commentTags = commentTags;
        this.commentText = commentText;
    }

    public Comment(String commentName, Integer commentGoods, String commentTags, String commentText) {
        this.commentName = commentName;
        this.commentGoods = commentGoods;
        this.commentTags = commentTags;
        this.commentText = commentText;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public Integer getCommentGoods() {
        return commentGoods;
    }

    public void setCommentGoods(Integer commentGoods) {
        this.commentGoods = commentGoods;
    }

    public String getCommentTags() {
        return commentTags;
    }

    public void setCommentTags(String commentTags) {
        this.commentTags = commentTags;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", commentName='" + commentName + '\'' +
                ", commentGoods=" + commentGoods +
                ", commentTags=" + commentTags +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
