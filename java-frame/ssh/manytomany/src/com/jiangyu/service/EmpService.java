package com.jiangyu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangyu.dao.IEmpDao;
import com.jiangyu.dao.IRoleDao;
import com.jiangyu.pojo.CEmp;
import com.jiangyu.pojo.CRole;
@Service
public class EmpService implements IEmpService {

	@Autowired
	private IEmpDao empDao;
	
	@Autowired
	private IRoleDao roleDao;
	
	public List<CEmp> findAll() {
		// TODO Auto-generated method stub
		return empDao.findAll(" FROM CEmp ");
	}

	public void modifyEmpRole(Integer id, Integer[] roleid) {
		// TODO Auto-generated method stub
		CEmp emp = empDao.get(CEmp.class, id);
		//��������û�ԭ���Ľ�ɫ
		emp.setCRoles(null);//emp.getCRoles().clear();
		Set<CRole> set = new HashSet<CRole>();
		for(Integer i : roleid){
			CRole r =roleDao.get(CRole.class, i);
			set.add(r);//emp.getCRoles().add(r);
		}
		emp.setCRoles(set);
		//��� ��emp.hbm.xml�д���inverse=true ��ô����ĸ���empDao.modify(emp);��
		//���ᱻִ�У���Ϊempû��ά��������ϵ Ҳ����˵emp��Զ�����м��
		//ִ��role�����ʱ��Ż���Ч
		empDao.modify(emp);
		
		/*********��һ��**********/
//		CEmp emp = empDao.get(CEmp.class, id);
//		emp.getCRoles().clear();
//		for(Integer i : roleid){ //ֻҪ˫��û�м������µĹ�ϵ �Ͳ����޸Ľ�ɫ��
//			CRole r = new CRole();
//			emp.getCRoles().add(r);
//		}
//		empDao.modify(emp);
		
	}

	public void remove(Integer id) {
		// TODO Auto-generated method stub
		CEmp emp = empDao.get(CEmp.class, id);
		empDao.removeOne(emp);
	}

}
