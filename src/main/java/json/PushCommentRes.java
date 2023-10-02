package json;

import pojo.Comment;

public class PushCommentRes extends AbstractRes
{
    Comment comment;
    public void SetComment(Comment c) { this.comment =c;}
}
