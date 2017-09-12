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
 * CashDropDialog.java
 *
 * Created on September 2, 2006, 12:22 AM
 */

package com.floreantpos.ui.dialog;

import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import net.miginfocom.swing.MigLayout;

import com.floreantpos.IconFactory;
import com.floreantpos.Messages;
import com.floreantpos.main.Application;
import com.floreantpos.model.CashDropTransaction;
import com.floreantpos.model.PaymentType;
import com.floreantpos.model.Terminal;
import com.floreantpos.model.dao.CashDropTransactionDAO;
import com.floreantpos.swing.PosUIManager;
import com.floreantpos.util.CurrencyUtil;
import com.floreantpos.util.NumberUtil;

/**
 *
 * @author  MShahriar
 */
public class CashDropDialog extends POSDialog {
	private List<CashDropTransaction> cashDropList;
	private DefaultListSelectionModel selectionModel;
	private CashDropTableModel tableModel;
	private String currencySymbol;

	/** Creates new form CashDropDialog */
	public CashDropDialog() {
		super(Application.getPosWindow(), true);
		
		initComponents();

		terminal = Application.getInstance().getTerminal();
		currencySymbol = CurrencyUtil.getCurrencySymbol();

		lblActiveCashDrop.setText(""); //$NON-NLS-1$
		TitledBorder titledBorder = new TitledBorder(Messages.getString("CashDropDialog.1") + terminal.getName()); //$NON-NLS-1$
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		midPanel.setBorder(titledBorder);

		selectionModel = new DefaultListSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCashDrops.setSelectionModel(selectionModel);

		tableCashDrops.setTableHeader(null);
		tableCashDrops.setDefaultRenderer(Object.class, new TableRenderer());
		tableModel = new CashDropTableModel();
		tableCashDrops.setModel(tableModel);
		
		setTitle(Messages.getString("CashDropDialog.2")); //$NON-NLS-1$
	}

	public void initDate() throws Exception {
		CashDropTransactionDAO dao = new CashDropTransactionDAO();

		cashDropList = dao.findUnsettled(terminal);
		tableModel.fireTableDataChanged();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        titlePanel1 = new com.floreantpos.ui.TitlePanel();
        transparentPanel1 = new com.floreantpos.swing.TransparentPanel();
        jSeparator1 = new javax.swing.JSeparator();
        transparentPanel3 = new com.floreantpos.swing.TransparentPanel(new MigLayout("al center", "sg, fill",""));
        btnNewCashDrop = new com.floreantpos.swing.PosButton();
        btnDeleteSelected = new com.floreantpos.swing.PosButton();
        btnClose = new com.floreantpos.swing.PosButton();
        midPanel = new com.floreantpos.swing.TransparentPanel();
        transparentPanel2 = new com.floreantpos.swing.TransparentPanel();
        btnUp = new com.floreantpos.swing.PosButton();
        btnDown = new com.floreantpos.swing.PosButton();
        lblActiveCashDrop = new com.floreantpos.swing.POSLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCashDrops = new javax.swing.JTable();
        tableCashDrops.setRowHeight(PosUIManager.getSize(40));

        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        titlePanel1.setTitle(Messages.getString("CashDropDialog.3")); //$NON-NLS-1$
        getContentPane().add(titlePanel1, java.awt.BorderLayout.NORTH);

        transparentPanel1.setLayout(new java.awt.BorderLayout(5, 5));
        transparentPanel1.add(jSeparator1, java.awt.BorderLayout.NORTH);

        btnNewCashDrop.setText(Messages.getString("CashDropDialog.4")); //$NON-NLS-1$
        btnNewCashDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCashDropActionPerformed(evt);
            }
        });

        transparentPanel3.add(btnNewCashDrop);

        btnDeleteSelected.setText(Messages.getString("CashDropDialog.5")); //$NON-NLS-1$
        btnDeleteSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSelectedActionPerformed(evt);
            }
        });

        transparentPanel3.add(btnDeleteSelected);

        btnClose.setText(Messages.getString("CashDropDialog.6")); //$NON-NLS-1$
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        transparentPanel3.add(btnClose);

        transparentPanel1.add(transparentPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(transparentPanel1, java.awt.BorderLayout.SOUTH);

        midPanel.setLayout(new java.awt.BorderLayout(1, 5));

        transparentPanel2.setLayout(new java.awt.GridLayout(0, 1, 2, 2));

        transparentPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 1, 10, 5));
        btnUp.setIcon(IconFactory.getIcon("/ui_icons/", "up.png")); //$NON-NLS-1$ //$NON-NLS-2$
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doScrollUp(evt);
            }
        });

        transparentPanel2.add(btnUp);

        btnDown.setIcon(IconFactory.getIcon("/ui_icons/", "down.png")); //$NON-NLS-1$ //$NON-NLS-2$
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doScrollDown(evt);
            }
        });

        transparentPanel2.add(btnDown);

        midPanel.add(transparentPanel2, java.awt.BorderLayout.EAST);

        lblActiveCashDrop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblActiveCashDrop.setText(Messages.getString("CashDropDialog.11")); //$NON-NLS-1$
        midPanel.add(lblActiveCashDrop, java.awt.BorderLayout.NORTH);

        tableCashDrops.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            }
        ));
        jScrollPane1.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 10, 10),jScrollPane1.getBorder())); 
        jScrollPane1.setViewportView(tableCashDrops);

        midPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(midPanel, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width=PosUIManager.getSize(606); 
        int height=PosUIManager.getSize(472); 
        setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
    }

	private void doScrollDown(java.awt.event.ActionEvent evt) {                              
		if (cashDropList == null)
			return;

		int selectedRow = tableCashDrops.getSelectedRow();
		if (selectedRow < 0) {
			selectedRow = 0;
		}
		else if (selectedRow >= cashDropList.size() - 1) {
			selectedRow = 0;
		}
		else {
			++selectedRow;
		}

		selectionModel.setLeadSelectionIndex(selectedRow);
		Rectangle cellRect = tableCashDrops.getCellRect(selectedRow, 0, false);
		tableCashDrops.scrollRectToVisible(cellRect);
	}

	private void doScrollUp(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doScrollUp
		if (cashDropList == null)
			return;

		int selectedRow = tableCashDrops.getSelectedRow();
		if (selectedRow <= 0) {
			selectedRow = cashDropList.size() - 1;
		}
		else if (selectedRow > (cashDropList.size() - 1)) {
			selectedRow = cashDropList.size() - 1;
		}
		else {
			--selectedRow;
		}

		selectionModel.setLeadSelectionIndex(selectedRow);
		Rectangle cellRect = tableCashDrops.getCellRect(selectedRow, 0, false);
		tableCashDrops.scrollRectToVisible(cellRect);
	}//GEN-LAST:event_doScrollUp

	private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
		dispose();
	}//GEN-LAST:event_btnCloseActionPerformed

	private void btnDeleteSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSelectedActionPerformed
		try {
			int index = tableCashDrops.getSelectedRow();
			if (index >= 0) {
				CashDropTransaction transaction = cashDropList.get(index);
				CashDropTransactionDAO dao = new CashDropTransactionDAO();
				dao.deleteCashDrop(transaction, Application.getInstance().refreshAndGetTerminal());
				tableModel.removeCashDrop(transaction);
			}
		} catch (Exception e) {
			POSMessageDialog.showError(Application.getPosWindow(),Messages.getString("CashDropDialog.16"), e); //$NON-NLS-1$
		}
	}//GEN-LAST:event_btnDeleteSelectedActionPerformed

	private void btnNewCashDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCashDropActionPerformed
		try {
			NumberSelectionDialog2 dialog = new NumberSelectionDialog2();
			dialog.setTitle(Messages.getString("CashDropDialog.17")); //$NON-NLS-1$
			dialog.setFloatingPoint(true);
			dialog.pack(); 
			dialog.open();

			if (!dialog.isCanceled()) {
				double amount = dialog.getValue();
				CashDropTransaction transaction = new CashDropTransaction();
				transaction.setDrawerResetted(false);
				transaction.setTerminal(Application.getInstance().getTerminal());
				transaction.setUser(Application.getCurrentUser());
				transaction.setTransactionTime(new Date());
				transaction.setAmount(amount);
				transaction.setPaymentType(PaymentType.CASH.toString());

				CashDropTransactionDAO dao = new CashDropTransactionDAO();
				dao.saveNewCashDrop(transaction, Application.getInstance().getTerminal());

				tableModel.addCashDrop(transaction);
			}
		} catch (Exception e) {
			POSMessageDialog.showError(Application.getPosWindow(),Messages.getString("CashDropDialog.18"), e); //$NON-NLS-1$
		}
	}//GEN-LAST:event_btnNewCashDropActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.floreantpos.swing.PosButton btnClose;
    private com.floreantpos.swing.PosButton btnDeleteSelected;
    private com.floreantpos.swing.PosButton btnDown;
    private com.floreantpos.swing.PosButton btnNewCashDrop;
    private com.floreantpos.swing.PosButton btnUp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.floreantpos.swing.POSLabel lblActiveCashDrop;
    private com.floreantpos.swing.TransparentPanel midPanel;
    private javax.swing.JTable tableCashDrops;
    private com.floreantpos.ui.TitlePanel titlePanel1;
    private com.floreantpos.swing.TransparentPanel transparentPanel1;
    private com.floreantpos.swing.TransparentPanel transparentPanel2;
    private com.floreantpos.swing.TransparentPanel transparentPanel3;
    // End of variables declaration//GEN-END:variables
	private Terminal terminal;

	class CashDropTableModel extends AbstractTableModel {

		public int getRowCount() {
			if (cashDropList == null)
				return 0;

			int size = cashDropList.size();
			return size;
		}

		public int getColumnCount() {
			return 2;
		}

		public void addCashDrop(int index, CashDropTransaction t) {
			if (cashDropList == null)
				return;

			cashDropList.add(index, t);
			fireTableRowsInserted(index, index);

			Rectangle cellRect = tableCashDrops.getCellRect(index, 0, false);
			tableCashDrops.scrollRectToVisible(cellRect);
			selectionModel.setLeadSelectionIndex(index);
		}

		public void addCashDrop(CashDropTransaction t) {
			int size = cashDropList.size();
			cashDropList.add(t);
			fireTableRowsInserted(size, size);

			Rectangle cellRect = tableCashDrops.getCellRect(size, 0, false);
			tableCashDrops.scrollRectToVisible(cellRect);
			selectionModel.setLeadSelectionIndex(size);
		}

		public void removeCashDrop(CashDropTransaction t) {
			int index = cashDropList.indexOf(t);
			if (index >= 0) {
				cashDropList.remove(index);
				fireTableRowsDeleted(index, index);
			}

		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			if (cashDropList == null)
				return ""; //$NON-NLS-1$

			CashDropTransaction t = cashDropList.get(rowIndex);

			switch (columnIndex) {
				case 0:
					return t.getTransactionTime();

				case 1:
					return Double.valueOf(t.getAmount());
			}
			return ""; //$NON-NLS-1$
		}

	}

	class TableRenderer extends DefaultTableCellRenderer {
		private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, h:m a"); //$NON-NLS-1$
		Font font = getFont().deriveFont(Font.BOLD, 14);
		/**
		 * 
		 */
		private JCheckBox checkBox = new JCheckBox();

		public TableRenderer() {
			checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (value instanceof Boolean) {
				checkBox.setSelected(((Boolean) value).booleanValue());
				if (isSelected) {
					checkBox.setBackground(table.getSelectionBackground());
				}
				else {
					checkBox.setBackground(table.getBackground());
				}
				return checkBox;
			}
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			label.setFont(font);
			if (value instanceof Date) {
				String string = dateFormat.format(value);
				label.setText(string);
				label.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			if (value instanceof Double) {
				String string = NumberUtil.formatNumber(((java.lang.Double) value).doubleValue());
				label.setText(currencySymbol + string);
				label.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			else {
				label.setHorizontalAlignment(SwingConstants.LEFT);
			}
			return label;
		}
	}
}
