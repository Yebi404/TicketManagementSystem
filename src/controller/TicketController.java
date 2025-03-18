package controller;

import dao.TicketDAO;

import dao.TicketDAO;
import model.Ticket;
import model.Comment;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TicketController {
    private TicketDAO ticketDAO;

    public TicketController(){
        this.ticketDAO = new TicketDAO();
    }

    //Customer can create a ticket
    public void createTicket(String title, String description, String status, int createdBy){
        Ticket ticket = new Ticket(0, title, description, status, LocalDateTime.now(), null, createdBy);
        try {
            ticketDAO.insertTicket(ticket);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Custommers and Agent can view all tickets they have access to
    public List<Ticket> getTicketsByUser(int userId){
        try{
            return ticketDAO.getTicketsByUser(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Agent can update ticket statis(Resolved, Rejected
    public void updateTicketStatus(int ticketId, String newStatus){
        try{
            ticketDAO.updateTicketStatus(ticketId, newStatus);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Customer can delete their tickets
    public void deleteTicket(int ticketId) {
        try {
            ticketDAO.deleteTicket(ticketId);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Agent and customer can add comments to tickets
    public void addComment(int ticketId, int userId, String commentText){
        Comment comment = new Comment(0, ticketId, userId, commentText, LocalDateTime.now());
        try {
            ticketDAO.addComment(comment);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Comment> getCommentsByTicket(int ticketId){
        try {
            return ticketDAO.getCommentsByTicket(ticketId);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
