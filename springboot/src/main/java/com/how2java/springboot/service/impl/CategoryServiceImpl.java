package com.how2java.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.service.CategoryService;
import com.how2java.springboot.util.Page4Navigator;


@Service
//表示分类数据在redis中都放在category这个分组里
@CacheConfig(cacheNames="category")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDAO categoryDAO;

	@Override
	//#p0是指在加有@Cacheable注解的方法中的第一个参数   这里就是pageable  
	//假如是第一页，即offset=0， pagesize=5  机会创建一个key：“ category 0-5”
	//首先根据这个key到redis查询数据，第一次是不会有数据的，那么就会从数据库取到这5条数据，然后以“category 0-5”这个key保存到redis数据中
	//下一次再访问的时候，根据这个key，就可以从redis里取到数据了
	@Cacheable(key="'category '+ #p0.offset + '-' + #p0.pageSize")
	public Page4Navigator<Category> list(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Category> pageFromJPA = categoryDAO.findAll(pageable);
		Page4Navigator<Category> page = new Page4Navigator<Category>(pageFromJPA, 5);
		return page;
	}
	
	@Override
	//假如是获取id=71的数据
	//那么就会以“key="category71"”到redis中去获取，如果没有就会从数据中拿到，然后再以“key="category71"”这个值存放到redis当中
	//下一次访问的时候，根据这个key，就可以从redis里取到数据了
	@Cacheable(key="'category ' + #p0")
	public Category get(int id) {
		// TODO Auto-generated method stub
		Category c = categoryDAO.findOne(id);
		return c;
	}

	@Override
	//@CacheEvict这个注解表示清除掉缓存
	//allEntries=true是表示清除掉category分组下所有的keys
	@CacheEvict(allEntries=true)
	//用CachePut  在增加数据之后在Redis中以“key="category 71"”缓存一条数据
	//如果用这个   list对应的数据在缓存中对应的数据并没有发生变化  会导致数据不同步
	//即  虽然增加了  并且也增加到缓存中了，但是因为key不一样，通过查询拿到的数据是不会包含新的这一条的
//	@CachePut(key = "'category'+#p0")
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDAO.save(category);
	}

	@Override
	//这个道理和add是一样的
	//比如仅仅删除“key="category 71"”,没有什么意义
	//“key:"category 0-5"”里面的数据没有影响
	//所以还是通过CacheEvict删除掉所有的缓存就好了
	@CacheEvict(allEntries = true)
	public void delete(int id) {
		// TODO Auto-generated method stub
		categoryDAO.delete(id);
	}

	

}
