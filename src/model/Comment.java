package model;

import java.time.LocalDateTime;

public class Comment {
    private int commentId;
    private int ticketId;
    private int userId;
    private String comment;
    private LocalDateTime createdDate;

    public Comment(int commentId, int ticketId, int userId, String comment, LocalDateTime createdDate) {
        this.commentId = commentId;
        this.ticketId = ticketId;
        this.userId = userId;
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
