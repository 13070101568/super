package com.baidu.base.dao;

import java.util.List;

public interface SelectDataDao {
	public List queryForList(String sql);
	
	public int queryForInt(String sql);

	public boolean deletebyid(String sql);

	public String[] getColumnName(String sql);

	public int update(String sql);


	public void updateClob(String clobColumnName, String data, String idName, String idValue, String tableName);

	public String getClob(String clobColumnName, String idName, String idValue, String tableName);
	
	/**����û�з���ֵ�Ĵ洢����
	 * @author wgzx_106    @param callName �洢��������
	 * @author wgzx_106    @param args List���ϣ����������ŵ��ַ���
	 *@version 5:16:39 PM
	 */
	public void callProceDureNoResult(String callName,List<String> args);
	
	/** ���÷�������������ݵĴ洢����
	 * @author wgzx_106    @param callName �洢��������
	 * @author wgzx_106    @param args List���ϣ����������ű����������͵��ַ��� ��������_����ֵ����input_1 �����������out_2  �������  
	 * @author wgzx_106    @return ���������������ֵ�ļ���
	 *@version 5:16:26 PM
	 */
	public  List<String> callProceDureByOut(String callName,List<String> args);
}
