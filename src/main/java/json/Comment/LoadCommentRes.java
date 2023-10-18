package json.Comment;

import json.AbstractRes;
import pojo.Comment;
import java.util.List;


public class LoadCommentRes extends AbstractRes
{
    //TODO JSON格式问题
    List<Comment> CommentsList;

    public List<Comment> getCommentsList() { return this.CommentsList;}

    public void FillCommentsList(List<Comment> commentList) { this.CommentsList = commentList; }

}
