/**
 * ************************************************************************
 * * The contents of this file are subject to the MRPL 1.2
 * * (the  "License"),  being   the  Mozilla   Public  License
 * * Version 1.1  with a permitted attribution clause; you may not  use this
 * * file except in compliance with the License. You  may  obtain  a copy of
 * * the License at http://www.floreantpos.org/license.html
 * * Software distributed under the License  is  distributed  on  an "AS IS"
 * * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * * License for the specific  language  governing  rights  and  limitations
 * * under the License.
 * * The Original Code is FLOREANT POS.
 * * The Initial Developer of the Original Code is OROCUBE LLC
 * * All portions are Copyright (C) 2015 OROCUBE LLC
 * * All Rights Reserved.
 * ************************************************************************
 */
/*
 * PasswordScreen.java
 *
 * Created on August 14, 2006, 11:01 PM
 */

package com.floreantpos.ui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.logging.LogFactory;

import com.floreantpos.Messages;
import com.floreantpos.actions.ClockInOutAction;
import com.floreantpos.config.TerminalConfig;
import com.floreantpos.config.ui.DatabaseConfigurationDialog;
import com.floreantpos.main.Application;
import com.floreantpos.model.User;
import com.floreantpos.model.dao.BaseUserDAO;
import com.floreantpos.model.dao.UserDAO;
import com.floreantpos.swing.MessageDialog;
import com.floreantpos.swing.PosButton;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.ui.dialog.PasswordEntryDialog;
import com.floreantpos.util.ShiftException;
import com.floreantpos.util.UserNotFoundException;

/**
 * 
 * @author MShahriar
 */
class LoginPasswordEntryView extends JPanel {

	/** Creates new form PasswordScreen */
	LoginPasswordEntryView() {
		//setMinimumSize(new Dimension(320, 10));
		initComponents();

		applyComponentOrientation(ComponentOrientation.getOrientation(Locale.getDefault()));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		buttonPanel = new javax.swing.JPanel();
		bottomPanel = new javax.swing.JPanel();

		btnShutdown = new com.floreantpos.swing.PosButton();

		setPreferredSize(new Dimension(320, 593));
		setLayout(new BorderLayout());

		buttonPanel.setOpaque(false);
		buttonPanel.setPreferredSize(new java.awt.Dimension(200, 180));
		buttonPanel.setLayout(new MigLayout("", "[111px][111px][111px,grow]", "[60px][60px][60px][60px]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		lblTerminalId = new JLabel("TERMINAL ID:"); //$NON-NLS-1$
		lblTerminalId.setForeground(Color.BLACK);
		lblTerminalId.setFont(new Font("Dialog", Font.BOLD, 18)); //$NON-NLS-1$
		lblTerminalId.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTerminalId, BorderLayout.NORTH);

		bottomPanel.setLayout(new MigLayout("hidemode 3, fill")); //$NON-NLS-1$
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(btnRegularMode);
		//buttonGroup.add(btnCashierMode);
		buttonGroup.add(btnKitchenMode);
		
		btnRegularMode.setFocusable(false);
//		btnCashierMode.setFocusable(false);
		btnKitchenMode.setFocusable(false);
		
		ModeSelectionListener l = new ModeSelectionListener();
		btnRegularMode.addActionListener(l);
//		btnCashierMode.addActionListener(l);
		btnKitchenMode.addActionListener(l);
		
		btnRegularMode.setSelected(TerminalConfig.isRegularMode());
//		btnCashierMode.setSelected(TerminalConfig.isCashierMode());
		btnKitchenMode.setSelected(TerminalConfig.isKitchenMode());
		
		if(!btnRegularMode.isSelected() && !btnKitchenMode.isSelected()) {
			btnRegularMode.setSelected(true);
		}
		
		JPanel modePanel = new JPanel(new GridLayout(1, 0, 2, 2));
		modePanel.add(btnRegularMode);
//		modePanel.add(btnCashierMode);
		modePanel.add(btnKitchenMode);
		
		bottomPanel.add(modePanel, "h 60!, grow, wrap"); //$NON-NLS-1$

		psbtnLogin = new PosButton();
		psbtnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		psbtnLogin.setText("LOGIN"); //$NON-NLS-1$
		bottomPanel.add(psbtnLogin, "grow, wrap, gapbottom 20px"); //$NON-NLS-1$
		
		PosButton btnClockOUt = new PosButton(new ClockInOutAction(false, true));
		bottomPanel.add(btnClockOUt, "grow, wrap, h 60!"); //$NON-NLS-1$
		
		if (TerminalConfig.isShowDbConfigureButton()) {
			btnConfigureDatabase = new com.floreantpos.swing.PosButton();
			btnConfigureDatabase.setAction(goAction);
			btnConfigureDatabase.setText(com.floreantpos.POSConstants.CONFIGURE_DATABASE);
			btnConfigureDatabase.setFocusable(false);
			btnConfigureDatabase.setActionCommand("DBCONFIG"); //$NON-NLS-1$
			bottomPanel.add(btnConfigureDatabase, "grow, wrap, h 60!"); //$NON-NLS-1$
		}

		btnShutdown.setAction(goAction);
		btnShutdown.setText(com.floreantpos.POSConstants.SHUTDOWN);
		btnShutdown.setFocusable(false);

		if (TerminalConfig.isFullscreenMode()) {
			if(btnConfigureDatabase != null) {
				btnConfigureDatabase.setVisible(false);
			}
			if(btnShutdown != null) {
				btnShutdown.setVisible(false);
			}
		}
		
		bottomPanel.add(btnShutdown, "grow, wrap, h 60!"); //$NON-NLS-1$
		add(bottomPanel, BorderLayout.SOUTH);

		lblTerminalId.setText(""); //$NON-NLS-1$
	}// </editor-fold>//GEN-END:initComponents

	public synchronized void doLogin() {
		try {
                        final User user;
                        if (TerminalConfig.isDisablePIN()) user = UserDAO.getInstance().findUserBySecretKey("1111");
                        else user = PasswordEntryDialog.getUser(Application.getPosWindow(),Messages.getString("LoginView.1"), Messages.getString("LoginView.2"));
                        if (user == null) {
				return;
			}
			Application application = Application.getInstance();
			application.doLogin(user);

		} catch (UserNotFoundException e) {
			LogFactory.getLog(Application.class).error(e);
			POSMessageDialog.showError(Application.getPosWindow(), Messages.getString("LoginPasswordEntryView.15")); //$NON-NLS-1$
		} catch (ShiftException e) {
			LogFactory.getLog(Application.class).error(e);
			MessageDialog.showError(e.getMessage());
		} catch (Exception e1) {
			LogFactory.getLog(Application.class).error(e1);
			String message = e1.getMessage();

			if (message != null && message.contains("Cannot open connection")) { //$NON-NLS-1$
				MessageDialog.showError(Messages.getString("LoginPasswordEntryView.17"), e1); //$NON-NLS-1$
				DatabaseConfigurationDialog.show(Application.getPosWindow());
			}
			else {
				MessageDialog.showError(Messages.getString("LoginPasswordEntryView.18"), e1); //$NON-NLS-1$
			}
		}
	}

	

	public void setTerminalId(int terminalId) {
		lblTerminalId.setText(Messages.getString("LoginPasswordEntryView.19") + terminalId); //$NON-NLS-1$
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private com.floreantpos.swing.PosButton btnConfigureDatabase;
	private com.floreantpos.swing.PosButton btnShutdown;
	private javax.swing.JPanel buttonPanel;
	private javax.swing.JPanel bottomPanel;
	
	// End of variables declaration//GEN-END:variables

	Action goAction = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {

			String command = e.getActionCommand();
			if (com.floreantpos.POSConstants.LOGIN.equals(command)) {
				doLogin();
			}
			else if (com.floreantpos.POSConstants.SHUTDOWN.equals(command)) {
				Application.getInstance().shutdownPOS();
			}
			else if ("DBCONFIG".equalsIgnoreCase(command)) { //$NON-NLS-1$
				DatabaseConfigurationDialog.show(Application.getPosWindow());
			}
			
		}
	};
	private PosButton psbtnLogin;
	private JLabel lblTerminalId;
	
	private JToggleButton btnRegularMode = new JToggleButton("<html><center>REGULAR<br/>MODE</center></html>"); //$NON-NLS-1$
	//private JToggleButton btnCashierMode = new JToggleButton("<html><center>CASHIER<br/>MODE</center></html>");
	private JToggleButton btnKitchenMode = new JToggleButton("<html><center>KITCHEN<br/>MODE</center></html>"); //$NON-NLS-1$

	
	class ModeSelectionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			TerminalConfig.setRegularMode(btnRegularMode.isSelected());
//			TerminalConfig.setCashierMode(btnCashierMode.isSelected());
			TerminalConfig.setKitchenMode(btnKitchenMode.isSelected());
		}
		
	}
}
