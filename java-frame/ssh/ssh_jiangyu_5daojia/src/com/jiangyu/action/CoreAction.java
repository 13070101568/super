package com.jiangyu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangyu.pojo.Area;
import com.jiangyu.pojo.Category;
import com.jiangyu.pojo.Food;
import com.jiangyu.pojo.Shop;
import com.jiangyu.service.ICoreService;

@Controller
@RequestMapping("core")
public class CoreAction {

	@Autowired
	private ICoreService coreService;
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�ת����Ӳ�Ʒ��ҳ��
	 * ʱ�䣺2015��11��25��
	 * ���ߣ�1405javab ����
	 * @param model
	 * @return
	 */
	@RequestMapping("toSaveFood")
	public String toSaveFood(Model model){
		List<Shop> shoplist = coreService.findAllShop();
		List<Category> categorylist = coreService.findAllCategory();
		model.addAttribute("shoplist", shoplist);
		model.addAttribute("categorylist", categorylist);
		return "add_food";
	}
	/**
	 * 
	 * @author ����è��
	 * ���ܣ���Ӳ�Ʒ
	 * ʱ�䣺2015��11��25��
	 * ���ߣ�1405javab ����
	 * @return
	 */
	@RequestMapping("addFood")
	public String addFood(Food food){
		
		coreService.addFood(food);
		
		return "search";
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�ģ����ѯ�����͸õ����Ĳ�����
	 * ʱ�䣺2015��11��25��
	 * ���ߣ�1405javab ����
	 * @return
	 */
	@RequestMapping("searchShops")
	@ResponseBody
	public Map<String,Object> searchShops(String name){
		Map<String,Object> map = new HashMap<String, Object>();
		//ͨ����ַ����õ�ַ���ڵĵ���
		Area aa = coreService.findAreasByCondition(name);
		//ͨ����������õ����ж��ټҲ���
		if(aa!=null){
			int count = coreService.findShopCount(aa.getId());
			map.put("area", aa.getName());
			map.put("shops", count);	
		}
		return map;	
	}
	/**
	 * 
	 * @author ����è��
	 * ���ܣ���ѯ���в���
	 * ʱ�䣺2015��11��26��
	 * ���ߣ�1405javab ����
	 * @param model
	 * @return
	 */
	@RequestMapping("findShops")
	public String findShops(Model model){
		List<Shop> shoplist = coreService.findAllShop();
		model.addAttribute("shoplist", shoplist);
		return "shop_list";
	}
	
	@RequestMapping("showOneshopFoods")
	public String showOneshopFoods(Integer id,Model model){
		//���ݲ���id����ò��������в�Ʒ
		List<Category> list = coreService.findCategorysByShopId(id);
		Map<String,List<Food>> map = new HashMap<>();
		for (Category cat : list) {
			//��øò����� �ò�Ʒ��������в�Ʒ
			List<Food> flist = coreService.findFoodsByCondition(id,cat.getId());
			map.put(cat.getName(), flist);
		}
		
		model.addAttribute("categorylist", list);
		model.addAttribute("foodmap", map);
		
		return "food_list";
	}
	
	
	
	
}
