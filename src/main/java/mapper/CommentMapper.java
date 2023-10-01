package mapper;

import pojo.Comment;

import java.util.List;

public interface CommentMapper {
    void createMapper(Comment comment);

    void deleteComment(Integer commentID);

    Comment getCommentByID(Integer commentID);

    void updateComment(Comment comment);

    List<Comment> getCommentsOfGoods(Integer goodsID);

    List<Comment> getCommentsOfUser(String userName);

}
