package view;

import controller.TicketController;
import model.Ticket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerView extends JFrame{
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JButton submitButton;
    private JButton viewTicketButton;
    private JButton deleteTicketButton;
    private TicketController controller;
    private int userId;

    public CustomerView(TicketController controller, int userId) {
        this.controller = controller;
        this.userId = userId;
        initUI();
    }

    private void initUI() {
        setTitle("Customer Ticket Management");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        titleField = new JTextField(20);
        descriptionArea = new JTextArea(5, 20);
        submitButton = new JButton("Submit Ticket");
        viewTicketButton = new JButton("View My Ticket");
        deleteTicketButton = new JButton("Delete Ticket");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(descriptionArea));
        panel.add(submitButton);
        panel.add(viewTicketButton);
        panel.add(deleteTicketButton);
        add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionArea.getText();
                controller.createTicket(title, description, "Open", userId);
                JOptionPane.showMessageDialog(null, "Ticket Created!");

            }
        });
    }

    public static void main(String[] args){
        TicketController controller1 = new TicketController();
        SwingUtilities.invokeLater(() -> {
            CustomerView view = new CustomerView(controller1, 1); //Assumimg userId = 1 from demo
            view.setVisible(true);
        });
    }
}
