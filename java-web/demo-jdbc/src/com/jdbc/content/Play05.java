package com.jdbc.content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jdbc.datasource.DataSource;
/**
 * 
 * @author JiangYu
 * JDBC������demo
 *
 */
public class Play05 {
	public static void main(String[] args) throws SQLException {
		DataSource ds = DataSource.getInstance();
		Connection conn = ds.getConnection();
		String sql = "insert into aax values(?)";
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < 20011; i++) {
			pstmt.setInt(1, i);
			// ��������
			pstmt.addBatch();
			if (i % 2000 == 0) {// �����2000������֮��
				pstmt.executeBatch();// ����ִ��
				conn.commit();
			}
			// ��ѭ����ִ�������֮��һ��Ҫ�������䲻����Ļ����´�ִ�е�ʱ����
			pstmt.clearBatch();
		}
		// ִ������� i<20011 ��11����������2000�ģ����ԣ���ѭ��֮�⻹Ҫִ��
		pstmt.executeBatch();// conn.commit();
	}
}
