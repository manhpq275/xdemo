package vn.mvv.xconnect.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 6/15/16.
 */
public class CommentView extends BaseView {
    private String parentId;
    private String consumerName;
    private String consumerAvatarUrl;
    private String contents;
    private Date commentDate;
    private ArrayList<CommentView> childs;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerAvatarUrl() {
        return consumerAvatarUrl;
    }

    public void setConsumerAvatarUrl(String consumerAvatarUrl) {
        this.consumerAvatarUrl = consumerAvatarUrl;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public ArrayList<CommentView> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<CommentView> childs) {
        this.childs = childs;
    }

}
