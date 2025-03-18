package view;

import controller.TicketController;
import model.Ticket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AgentView extends JFrame {
    private JTextField ticketIdField;
    private JTextArea commentArea;
    private JComboBox<String> statusComboBox;
    private JButton updateStatusButton;
    private JButton viewTicketsButton;
    private JButton addCommentButton;
    private TicketController controller;

    public AgentView(TicketController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Agent Ticket Management");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ticketIdField = new JTextField(5);
        commentArea = new JTextArea(5, 20);
        String[] statuses = {"Open", "In Progress", "Resolved", "Rejected"};
        statusComboBox = new JComboBox<>(statuses);
        updateStatusButton = new JButton("Update Status");
        viewTicketsButton = new JButton("View All Tickets");
        addCommentButton = new JButton("Add Comment");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Ticket ID:"));
        panel.add(ticketIdField);
        panel.add(new JLabel("Status:"));
        panel.add(statusComboBox);
        panel.add(new JLabel("Comment:"));
        panel.add(new JScrollPane(commentArea));
        panel.add(updateStatusButton);
        panel.add(viewTicketsButton);
        panel.add(addCommentButton);
        add(panel);

        viewTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Ticket> tickets = controller.getAllTickets();
                JOptionPane.showMessageDialog(null, tickets.toString());
            }
        });

        updateStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticketIdStr = ticketIdField.getText();
                int ticketId = Integer.parseInt(ticketIdStr);
                String newStatus = statusComboBox.getSelectedItem().toString();
                controller.updateTicketStatus(ticketId, newStatus);
                JOptionPane.showMessageDialog(null, "Ticket Status Updated!");
            }
        });

        addCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ticketIdStr = ticketIdField.getText();
                int ticketId = Integer.parseInt(ticketIdStr);
                String comment = commentArea.getText();
                controller.addComment(ticketId, 2, comment);  // Assuming agentId = 2 for demo
                JOptionPane.showMessageDialog(null, "Comment Added!");
            }
        });
    }

    public static void main(String[] args) {
        TicketController controller = new TicketController();
        SwingUtilities.invokeLater(() -> {
            AgentView view = new AgentView(controller);
            view.setVisible(true);
        });
    }
}