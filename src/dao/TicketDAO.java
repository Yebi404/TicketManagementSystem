package dao;

import model.Ticket;
import model.Comment;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    // Database connection details
    public static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=TicketManagementSystem";
    public static final String USER = "root";  // Consider using environment variables for security
    public static final String PASSWORD = "root"; // Consider using environment variables for security

    // Insert a new ticket
    public void insertTicket(Ticket ticket) throws SQLException {
        String query = "INSERT INTO tickets (title, description, status, created_date, created_by) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ticket.getTitle());
            stmt.setString(2, ticket.getDescription());
            stmt.setString(3, ticket.getStatus());
            stmt.setTimestamp(4, Timestamp.valueOf(ticket.getCreatedDate()));
            stmt.setInt(5, ticket.getCreatedBy());
            stmt.executeUpdate();
        }
    }

    // Retrieve tickets by user ID
    public List<Ticket> getTicketsByUser(int userId) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets WHERE created_by = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getTimestamp("created_date").toLocalDateTime(),
                        rs.getTimestamp("resolved_date") != null ? rs.getTimestamp("resolved_date").toLocalDateTime() : null,
                        rs.getInt("created_by")
                );
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    // Retrieve all tickets
    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getTimestamp("created_date").toLocalDateTime(),
                        rs.getTimestamp("resolved_date") != null ? rs.getTimestamp("resolved_date").toLocalDateTime() : null,
                        rs.getInt("created_by")
                );
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    // Update ticket status
    public void updateTicketStatus(int ticketId, String newStatus) throws SQLException {
        String query = "UPDATE tickets SET status = ?, resolved_date = ? WHERE ticket_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newStatus);
            if (newStatus.equals("Resolved")) {
                stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            } else {
                stmt.setNull(2, Types.TIMESTAMP);  // Safely set null if unresolved
            }
            stmt.setInt(3, ticketId);
            stmt.executeUpdate();
        }
    }

    // Delete a ticket
    public void deleteTicket(int ticketId) throws SQLException {
        String query = "DELETE FROM tickets WHERE ticket_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ticketId);
            stmt.executeUpdate();
        }
    }

    // Add a comment to a ticket
    public void addComment(Comment comment) throws SQLException {
        String query = "INSERT INTO comments (ticket_id, user_id, comment, created_date) VALUES(?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, comment.getTicketId());
            stmt.setInt(2, comment.getUserId());
            stmt.setString(3, comment.getComment());
            stmt.setTimestamp(4, Timestamp.valueOf(comment.getCreatedDate()));
            stmt.executeUpdate();
        }
    }

    // Retrieve comments by ticket ID
    public List<Comment> getCommentsByTicket(int ticketId) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT * FROM comments WHERE ticket_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment(
                        rs.getInt("comment_id"),
                        rs.getInt("ticket_id"),
                        rs.getInt("user_id"),
                        rs.getString("comment"),
                        rs.getTimestamp("created_date").toLocalDateTime()
                );
                comments.add(comment);
            }
        }
        return comments;
    }
}
