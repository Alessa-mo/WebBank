package pojo;

import java.lang.reflect.Array;



public class Comment
{
    //TODO 评论
    public enum CommentBody
    {
        Business,
        ShopItem
    }

    int ID;
    String Contents;
    float[] CommentSequence;
    CommentBody MainBody;
}
