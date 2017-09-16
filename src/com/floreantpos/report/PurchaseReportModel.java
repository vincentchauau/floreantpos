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
package com.floreantpos.report;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import com.floreantpos.model.InventoryItem;
import com.floreantpos.swing.ListTableModel;
public class PurchaseReportModel extends ListTableModel {
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy, h:m a"); //$NON-NLS-1$
	DecimalFormat decimalFormat = new DecimalFormat("0.00"); //$NON-NLS-1$
	public PurchaseReportModel() {
		super(new String[] { "item", "description", "quantity", "price", "total" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		InventoryItem data = (InventoryItem) rows.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return String.valueOf(data.getName());
			case 1:
				return data.getDescription();
			case 2:
				return String.valueOf(data.getPackageReplenishLevel());
			case 3:
				return String.valueOf(data.getUnitPurchasePrice());
			case 4:
				String.valueOf(data.getAveragePackagePrice());
		}
		return null;
	}
}
