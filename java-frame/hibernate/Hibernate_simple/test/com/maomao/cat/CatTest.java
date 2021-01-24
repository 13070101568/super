package com.maomao.cat;

import static org.junit.Assert.fail;


import org.junit.Test;

import com.maomao.pojo.Cat;
import com.maomao.pojo.KindOfCat;
import com.maomao.utils.HibernateDao;

public class CatTest {

	HibernateDao<KindOfCat> dao = new HibernateDao<KindOfCat>();
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void add() {
		KindOfCat k = new KindOfCat();
		
		k.setKind("�۷�è");
		//KindOfCat kc = (KindOfCat)dao.findOneByGet(KindOfCat.class, 4);
		Cat cat = new Cat();
		
		cat.setName("�۷���1");
		cat.setSex("Ů");
		cat.setHobby("˯��,��ԡ");
		cat.setBirthday("1990-08-25");
		
		k.getCats().add(cat);
		cat.setKindOfCat(k);
		
		dao.addOne(k);
		
	}

	
}
