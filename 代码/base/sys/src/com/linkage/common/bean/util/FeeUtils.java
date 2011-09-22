package com.linkage.common.bean.util;

import java.text.DecimalFormat;

import com.linkage.appframework.common.Common;

public class FeeUtils {
	private static Common common = Common.getInstance();

	/** ���ĵ�λ�� �� ת���� Ԫ */
	public static String Fen2Yuan(String fen) throws Exception {
		return common.formatDecimal("0.00", Double.parseDouble(fen) / 100);
	}

	/** ������תRMB��д */
	public static String doubleToRMB(double money) {
		double absMoney = Math.abs(money);
		String StrTemp = null;
		
		String Number[] = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };
		String MonetaryUnit[] = { "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��", "ʰ", "Ԫ", "��", "��" };
		
		DecimalFormat deciformat = (DecimalFormat) DecimalFormat.getInstance();
		deciformat.applyPattern("#######");
		String m = String.valueOf(deciformat.format(absMoney * 100));
		int i;
		if ((i = m.indexOf('.')) != -1) {
			m = m.substring(0, i);
		}
		char[] p = new char[m.length()];	
		m.getChars(0, m.length(), p, 0);

		if (absMoney > 100000000000.00) {
			StrTemp = "";
			return StrTemp;
		}
		if (absMoney < 0.01) {
			StrTemp = "��";
			return StrTemp;
		}
		if (money < 0) {
			StrTemp = "��";
		} else {
			StrTemp = "";
		}
		int flag = 1;
		int len = p.length;
		for (int idx = (15 - len); idx < 15; idx++) {
			if (p[idx - 15 + len] != '0') {
				StrTemp = StrTemp + Number[Integer.parseInt(String.valueOf(p[idx - 15 + len]))];
				StrTemp = StrTemp + MonetaryUnit[idx];
			} else {
				if (idx == 5) {
					if ((p[idx - 14 + len] != '0') || (p[idx - 13 + len] != '0')) {
						StrTemp = StrTemp + MonetaryUnit[idx + 3];
						flag = 0;
					}

				} else {
					if ((idx == 12) || ((idx == 8) && (flag == 1)) || (idx == 4)) {
						StrTemp = StrTemp + MonetaryUnit[idx];
					}
					if ((p[idx - 15 + len] != '0') && (idx != 14)) {
						StrTemp = StrTemp + Number[Integer.parseInt(String.valueOf(p[idx - 15 + len]))];
						;
					}
				}
			}
		}
		if (p[m.length() - 1] == '0') {
			StrTemp = StrTemp + "��";
		}
		return StrTemp;
	}
	
	public static void main(String[] args) {
		System.out.println(doubleToRMB(99000000.00));
	}
}
