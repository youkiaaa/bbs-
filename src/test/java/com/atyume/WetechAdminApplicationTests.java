package com.atyume;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.atyume.modules.system.po.User;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WetechAdminApplicationTests {

	@Test
	public void contextLoads() {
		EntityHelper.getOrderByClause(User.class);
	}

}
