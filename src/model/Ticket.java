package model;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime resolvedDate;

    public Ticket(int ticketId, String title, String description, String status, LocalDateTime createdDate, LocalDateTime resolvedDate) {
        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.resolvedDate = resolvedDate;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(LocalDateTime resolvedDate) {
        this.resolvedDate = resolvedDate;
    }
}
