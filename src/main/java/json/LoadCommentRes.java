package json;

import pojo.Comment;
import java.util.List;


public class LoadCommentRes extends AbstractRes
{
    //TODO JSON格式问题
    List<Comment> Comments;

    public List<Comment> GetComments() { return Comments;}

    public void FillComments(List<pojo.Comment> comments) { this.Comments = comments; }

}
