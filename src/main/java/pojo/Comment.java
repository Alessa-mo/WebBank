package pojo;

import java.lang.reflect.Array;
import java.sql.Time;


public class Comment
{
    //TODO 评论
    int ID;
    String Contents;
    float[] CommentSequence;
    Time CommentTime;
    Entity Target;//评论谁
    String Orgin; //由谁评论
}
