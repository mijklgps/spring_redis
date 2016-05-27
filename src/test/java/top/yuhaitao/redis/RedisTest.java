package top.yuhaitao.redis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import top.yuhaitao.core.bean.User;
import top.yuhaitao.core.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class RedisTest {
	
	@Resource
	private UserDao userDao;
	
	
	//add
	@Test
	public void testAddUser() {
		User user = new User();
		user.setId("user1");
		user.setName("java2000_wl");
		boolean result = userDao.add(user);
		Assert.assertTrue(result);
	}
	
	//get
	@Test  
    public void testGetUser() {  
        String id = "user1";  
        User user = userDao.get(id);  
        Assert.assertNotNull(user);  
        //Assert.assertEquals(user.getName(), "java2000_wl");  
        System.out.println(user.getName());
    }  
	
	//
	/** 
     * 批量新增 pipeline方式 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testAddUsers2() {  
        List<User> list = new ArrayList<User>();  
        for (int i = 10; i < 100; i++) {  
            User user = new User();  
            user.setId("user" + i);  
            user.setName("java2000_wl" + i);  
            list.add(user);  
        }  
        long begin = System.currentTimeMillis();  
        boolean result = userDao.add(list);  
        System.out.println(System.currentTimeMillis() - begin);  
        Assert.assertTrue(result);  
    } 
    
    /** 
     * 修改 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testUpdate() {  
        User user = new User();  
        user.setId("user1");  
        user.setName("new_password");  
        boolean result = userDao.update(user);  
        Assert.assertTrue(result);  
    }

    /** 
     * 通过key删除单个 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testDelete() {  
        String key = "user1";  
        userDao.delete(key);  
    }  
      
    /** 
     * 批量删除 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testDeletes() {  
        List<String> list = new ArrayList<String>();  
        for (int i = 0; i < 10; i++) {  
            list.add("user" + i);  
        }  
        userDao.delete(list);  
    }  
}
