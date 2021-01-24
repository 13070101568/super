package com.baidu.asset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ChineseLatter {

	public static void main(String[] args) throws ParseException {
		
		System.out.println(getNum("�����й���"));
		
	}
	public static String getNum(String string) throws ParseException{
		String s="";
		SimpleDateFormat smt=new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String format = smt.format(date);
		int random = (int)(Math.random()*9000+1000);
		s=getPYIndexStr(string, false)+format+random;
		return s;
	}
	/**
	 * ��������ĸ
	 */
	public static String getPYIndexStr(String str, boolean bUpCase) {
		try {
			StringBuffer buffer = new StringBuffer();

			byte b[] = str.getBytes("GBK");// ������ת����byte����

			for (int i = 0; i < b.length; i++) {

				if ((b[i] & 255) > 128) {

					int char1 = b[i++] & 255;

					char1 <<= 8;// ����������á�<<����ʾ���ǽ��������ߵĶ��������ƶ�������ұ�ָ����λ���������ڵ�λ���㡣��ʵ��������nλ�����൱�ڳ���2��n�η�

					int chart = char1 + (b[i] & 255);

					buffer.append(getPYIndexChar((char) chart, bUpCase));

					continue;

				}

				char c = (char) b[i];

				if (!Character.isJavaIdentifierPart(c))// ȷ��ָ���ַ��Ƿ������ Java
														// ��ʶ�������ַ�����Ĳ��֡�

					c = 'A';

				buffer.append(c);

			}

			return buffer.toString();

		} catch (Exception e) {

			System.out.println((new StringBuilder())
					.append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519")
					.append(e.getMessage()).toString());

		}

		return null;

	}

	/**
	 * 
	 * �õ�����ĸ
	 * 
	 * @param strChinese
	 * 
	 * @param bUpCase
	 * 
	 * @return
	 */

	private static char getPYIndexChar(char string, boolean bUpCase) {

		int charGBK = string;

		char result;

		if (charGBK >= 45217 && charGBK <= 45252)

			result = 'A';

		else

		if (charGBK >= 45253 && charGBK <= 45760)

			result = 'B';

		else

		if (charGBK >= 45761 && charGBK <= 46317)

			result = 'C';

		else

		if (charGBK >= 46318 && charGBK <= 46825)

			result = 'D';

		else

		if (charGBK >= 46826 && charGBK <= 47009)

			result = 'E';

		else

		if (charGBK >= 47010 && charGBK <= 47296)

			result = 'F';

		else

		if (charGBK >= 47297 && charGBK <= 47613)

			result = 'G';

		else

		if (charGBK >= 47614 && charGBK <= 48118)

			result = 'H';

		else

		if (charGBK >= 48119 && charGBK <= 49061)

			result = 'J';

		else

		if (charGBK >= 49062 && charGBK <= 49323)

			result = 'K';

		else

		if (charGBK >= 49324 && charGBK <= 49895)

			result = 'L';

		else

		if (charGBK >= 49896 && charGBK <= 50370)

			result = 'M';

		else

		if (charGBK >= 50371 && charGBK <= 50613)

			result = 'N';

		else

		if (charGBK >= 50614 && charGBK <= 50621)

			result = 'O';

		else

		if (charGBK >= 50622 && charGBK <= 50905)

			result = 'P';

		else

		if (charGBK >= 50906 && charGBK <= 51386)

			result = 'Q';

		else

		if (charGBK >= 51387 && charGBK <= 51445)

			result = 'R';

		else

		if (charGBK >= 51446 && charGBK <= 52217)

			result = 'S';

		else

		if (charGBK >= 52218 && charGBK <= 52697)

			result = 'T';

		else

		if (charGBK >= 52698 && charGBK <= 52979)

			result = 'W';

		else

		if (charGBK >= 52980 && charGBK <= 53688)

			result = 'X';

		else

		if (charGBK >= 53689 && charGBK <= 54480)

			result = 'Y';

		else

		if (charGBK >= 54481 && charGBK <= 55289)

			result = 'Z';

		else

			result = (char) (65 + (new Random()).nextInt(25));

		if (!bUpCase)

			result = Character.toLowerCase(result);

		return result;

	}

}