package com.baidu.salary.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.baidu.asset.bean.Asset;
import com.baidu.base.service.SelectDataService;
import com.baidu.salary.bean.Salary;
import com.baidu.salary.service.SalaryService;
import com.baidu.user.bean.User;
import com.baidu.util.DateUtil;
import com.baidu.util.MailUtils;
import com.baidu.util.MessageUtil;
import com.baidu.util.Page;
import com.baidu.util.Response;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SalaryAction extends ActionSupport implements ModelDriven<Salary> {
	
	private Salary salary = new Salary();
	
	private SalaryService salaryService;
	private SelectDataService selectDataService;
	private String filepath;
	
	
	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public SalaryService getSalaryService() {
		return salaryService;
	}

	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	public SelectDataService getSelectDataService() {
		return selectDataService;
	}

	public void setSelectDataService(SelectDataService selectDataService) {
		this.selectDataService = selectDataService;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public Salary getModel() {
		// TODO Auto-generated method stub
		return salary;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����10:12:25
	 * ���ܣ������ʼ��������ʼ���
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		salaryService.save(salary);
		return SUCCESS;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����11:02:43
	 * ���ܣ�ת��list.jspҳ�� --->����ҳ��
	 */
	public String listPage(){
		return "list";
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-25����2:39:35
	 * ���ܣ���ѯ�����б�
	 */
	public void salaryList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Map map =selectDataService.getListForPage(request, "select * from t_salary");
		
		JSONObject json = new JSONObject();
		json.put("total", ((Page)map.get("p")).getTotal());
		json.put("rows", map.get("resoultList"));

		Response.write(json.toString());
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-28����8:55:36
	 * ���ܣ��ֻ����ʼ�Ⱥ��
	 */
	public void sendPhoneAndMail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] names = request.getParameterValues("names");
		for (String name : names) {
			//�˴��޷�ת��USER�� ֻ��ת��MAP
			List<Map> list = (List<Map>)selectDataService.queryForList("select u.mail,u.phone,s.* from t_user u,t_salary s where u.realname = s.workername and u.realname = '"+name+"'");
		
			Map user = list.get(0);
			String mail = (String)user.get("MAIL");
			MailUtils.sendMail(mail, salary.toString());
//			MessageUtil.sendMsg((String)user.get("PHONE"), salary.toString());
		}
		
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-25����3:35:54
	 * ���ܣ����빤�� excel���ʱ�
	 */
	public void importSalarys(){
		try {
			//��ȡ�ϴ���excel
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getSession().getServletContext().getRealPath("")+"\\poi\\";
			
			File file = new File(filepath);
			//����ļ�ͬ������
			filepath = UUID.randomUUID().toString().replace("-", "")+filepath.substring(filepath.lastIndexOf("."));
			//��������������ļ�
			File newFile = new File(path+filepath);
			//�ϴ�
			FileUtils.copyFile(file, newFile);
			
			//��ͷ�����ֶμ���
			List cnColList = new ArrayList();
			
			//��ȡ��ͷ��ϢTABLE_NAME, TABLE_TYPE, COMMENTS ÿһ��list��һ��Map ��һ������
			//			T_SALARY     ID         ID
			//			T_SALARY   WORKERNAME   Ա������
			//			T_SALARY   BASICSALARY  �������� ����
			List<Map> list =selectDataService.queryForList("select * from user_col_comments where table_name ='T_SALARY'");
			
			Map cn_enmap =new HashMap();//��Ӣ�ı�ͷ����
			
			for(Map map:list){
				cnColList.add(map.get("COMMENTS").toString());
				cn_enmap.put(map.get("COMMENTS").toString(), map.get("COLUMN_NAME").toString());
			}
			
			FileInputStream inputStream =new FileInputStream(path+filepath);
			
			HSSFWorkbook workbook =new HSSFWorkbook(inputStream);
			//ѭ������ÿһ��sheet
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				HSSFSheet sheet = workbook.getSheetAt(i);
				int rownum =sheet.getPhysicalNumberOfRows();
				//ѭ������ÿһ��
				String sql ="insert into t_salary (";
				String colSql="";
				for (int j = 1; j < rownum; j++) {
					String valueSql=" values ( ";
					HSSFRow row = sheet.getRow(j);
					int cellnum =	row.getPhysicalNumberOfCells();
					
					if(j==1){//������ͷһ��
						for (int k = 0; k < cellnum; k++) {
							HSSFCell cell = row.getCell(k);
							colSql+=cn_enmap.get(cell.getStringCellValue())+",";
						}
						colSql =colSql.substring(0, colSql.length()-1)+" ) ";
					}else{
						//ѭ������ÿһ������
						for (int k = 0; k < cellnum; k++) {
							HSSFCell srcCell = row.getCell(k);
							// ��ͬ�������ʹ���  
					        int srcCellType = srcCell.getCellType();  
					        Object value = "";
					        
					        if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {  
//					               if (HSSFDateUtil.isCellDateFormatted(srcCell)) {  
//					                    distCell.setCellValue(srcCell.getDateCellValue());  
//					                } else {  
//					                    distCell.setCellValue(srcCell.getNumericCellValue());  
//					                }  
					        	value = srcCell.getNumericCellValue();
					        } else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {  
//					                distCell.setCellValue(srcCell.getRichStringCellValue()); 
					            value =srcCell.getStringCellValue()==null?"":srcCell.getStringCellValue();
					        } else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) { 
					        	value = srcCell.getBooleanCellValue();
					        } else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {  
//					                distCell.setCellErrorValue(srcCell.getErrorCellValue());  
					        } else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {  
//					                distCell.setCellFormula(srcCell.getCellFormula());  
					        } else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {  
				                // nothing21  
					        } else { 
					        	
					        }  
							
							valueSql+="'"+value+"',";
							
						}
						valueSql =valueSql.substring(0, valueSql.length()-1)+" ) ";
						System.out.println(sql+colSql+valueSql);
						selectDataService.update(sql+colSql+valueSql);
					}
				}
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
