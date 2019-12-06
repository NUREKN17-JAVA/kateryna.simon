package ua.nure.kn.simon.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ua.nure.kn.simon.db.DatabaseException;
import ua.nure.kn.simon.domain.User;
import ua.nure.kn.simon.util.Messages;

public class BrowsePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JButton addButton;
	private JButton detailsButton;
	private JButton deleteButton;
	private JButton editButton;
	private JScrollPane tablePanel;
	private JTable userTable;

	public BrowsePanel(MainFrame frame) {
		parent = frame;
		initialize();
	}

	private void initialize() {
		this.setName("browsePanel"); 
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(),BorderLayout.CENTER);
		this.add(getButtonsPanel(),BorderLayout.SOUTH);
	}

	private JPanel getButtonsPanel() {
		if (buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(getAddButton(),null);
			buttonPanel.add(getEditButton(),null);
			buttonPanel.add(getDeleteButton(),null);
			buttonPanel.add(getDetailsButton(),null);
		}
		
		return buttonPanel;
	}

	private JButton getDetailsButton() {
		if (detailsButton == null){
			detailsButton = new JButton();
			detailsButton.setText(Messages.getString("BrowsePanel.details"));
			detailsButton.setName("detailsButton"); 
			detailsButton.setActionCommand("details"); 
			detailsButton.addActionListener(this);
		}
		return detailsButton;
	}

	private JButton getDeleteButton() {
		if (deleteButton == null){
			deleteButton = new JButton();
			deleteButton.setText(Messages.getString("BrowsePanel.delete")); 
			deleteButton.setName("deleteButton"); 
			deleteButton.setActionCommand("delete"); 
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton getEditButton() {
		if (editButton == null){
			editButton = new JButton();
			editButton.setText(Messages.getString("BrowsePanel.edit"));
			editButton.setName("editButton"); 
			editButton.setActionCommand("edit"); 
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton getAddButton() {
		if(addButton == null){
			addButton = new JButton();
			addButton.setText(Messages.getString("BrowsePanel.add")); 
			addButton.setName("addButton"); 
			addButton.setActionCommand("add"); 
			addButton.addActionListener(this);
		}
		return addButton;
	}

	private JScrollPane getTablePanel() {
		if(tablePanel == null){
			tablePanel = new JScrollPane(getUserTable());
		}
		return tablePanel;
	}

	private JTable getUserTable() {
		if(userTable == null){
			userTable = new JTable();
			userTable.setName("userTable"); //$NON-NLS-1$
			
		}
		return userTable;
	}
	public User getSelectedUser(){
		int selectedRow = getUserTable().getSelectedRow();
		int idColumn = 0;
		
		Long userId = null;
		User user = null;
		if(selectedRow==-1){
			JOptionPane.showMessageDialog(this, Messages.getString("BrowsePanel.choosing_user2"),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
		userId = (Long) userTable.getValueAt(userTable.getSelectedRow(),idColumn);
		try{
			user = parent.getDao().find(userId);
		}catch(DatabaseException e){
			JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); 
		}
		}
		return user;
	}
	public void initTable() {
		UserTableModel model;
		try {
			model = new UserTableModel(parent.getDao().findAll());
		} catch (DatabaseException e) {
			model = new UserTableModel(new ArrayList());
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE); 
		}
		getUserTable().setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if("add".equalsIgnoreCase(actionCommand)){ //$NON-NLS-1$
			this.setVisible(false);
			parent.showAddPanel();
		}
		if("edit".equalsIgnoreCase(actionCommand)){ //$NON-NLS-1$
			int selectedRow = userTable.getSelectedRow();
			int selectedColumn = userTable.getSelectedColumn();
			if (selectedColumn !=-1 || selectedRow!=-1){
				this.setVisible(false);
				parent.showEditPanel();
			}else{
				JOptionPane.showMessageDialog(this,Messages.getString("BrowsePanel.choosing_user1"),"Error", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
        if ("delete".equalsIgnoreCase(actionCommand)) { 
            User selectedUser = getSelectedUser();
            if (selectedUser != null) {
                int result = JOptionPane.showConfirmDialog(this, Messages.getString("BrowsePanel.accept_deleting"), 
                        "Confirm deleting", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); 
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        parent.getDao().delete(selectedUser);
                        getUserTable().setModel(new UserTableModel(parent.getDao().findAll()));
                    } catch (DatabaseException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if ("details".equalsIgnoreCase(actionCommand)) { 
        	User user = getSelectedUser();
			this.setVisible(false);
			parent.showDetailsPanel(user);
        }
	}

}
