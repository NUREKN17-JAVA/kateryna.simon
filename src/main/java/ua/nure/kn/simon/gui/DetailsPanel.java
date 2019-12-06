package ua.nure.kn.simon.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn.simon.domain.User;
import ua.nure.kn.simon.util.Messages;

public class DetailsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
    private static final int ROWS = 2;
    private static final int COLS = 2;
   

    private MainFrame parent;
    private JButton cancelButton;
    private JPanel fieldPanel;
    private JTextField fullNameField;
    private JTextField ageField;

    public DetailsPanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        parent.showBrowsePanel();
    }

    public void showUserDetails(User user) {
        fullNameField.setText(user.getFullName());
        ageField.setText(String.valueOf(user.getAge()));
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setName("detailsPanel"); //$NON-NLS-1$
        add(getFieldPanel(), BorderLayout.NORTH);
        add(getCancelButton(), BorderLayout.SOUTH);
    }

    private JButton getCancelButton() {
        if (cancelButton == null) {
        	cancelButton = new JButton();
        	cancelButton.setText(Messages.getString("DetailsPanel.close")); 
        	cancelButton.setName("cancel"); 
        	cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(ROWS, COLS));
            addLabeledImmutableField(fieldPanel, Messages.getString("DetailsPanel.full_name"), getFullNameField()); 
            addLabeledImmutableField(fieldPanel, Messages.getString("DetailsPanel.age"), getAgeField()); 
        }
        return fieldPanel;
    }

    private void addLabeledImmutableField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    private JTextField getFullNameField() {
        if (fullNameField == null) {
            fullNameField = new JTextField();
            fullNameField.setEditable(false);
            fullNameField.setName("fullNameField"); 
        }
        return fullNameField;
    }

    private JTextField getAgeField() {
        if (ageField == null) {
            ageField = new JTextField();
            ageField.setEditable(false);
            ageField.setName("ageField"); 
        }
        return ageField;
    }
}
