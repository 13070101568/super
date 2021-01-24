package com.baidu.base;


public class DbContextHolder {
	public static final String DATA_SOURCE_A = "dataSourceA";  
    
    public static final String DATA_SOURCE_B = "dataSourceB";  
	 private static final ThreadLocal contextHolder = new ThreadLocal();   
	   
	    public static void setDbType(String dbType) {   
	        contextHolder.set(dbType);   
	    }   
	  
	    public static String getDbType() {   
	        return (String) contextHolder.get();   
	    }   
	  
	    public static void clearDbType() {   
	        contextHolder.remove();   
	    } 
//	    ����Ҫ�л�����Դʱ��ֻҪ����һ�д���Ϳ�����,����Ŀǰ��������������޷���ͬһ�������ڲ�ͬʱ����ͬ�����ݿ��в���ֵ����Ҫ����дһ���������ü��ɡ�
		
//		public void saveOtherDB(){
//			DbContextHolder.setDbType(DbContextHolder.DATA_SOURCE_B);
//			MainTable maintable = new MainTable();
//			maintable = mainTableService.saveMainTable(maintable);
//		}
//
	}
