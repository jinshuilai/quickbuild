package com.lai.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lai.domain.UserAccount;
import com.lai.domain.UserAccountView;
import com.lai.framework.base.BaseRepository;
import com.lai.framework.util.ObjectAnalyzer;
import com.lai.repository.UserAccountRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private BaseRepository b;
	@Autowired
	private UserAccountRepository c;

	@Test
	public <S> void test() throws Exception {
		String fileds = ObjectAnalyzer.toSqlString(UserAccount.class, "p");
		String sql = "select "+fileds+",c.COMPANY_NAME companyName from USER_ACCOUNT p "+
				"left join COMPANY_INFO c ON p.COMPANY_SEQID = c.SEQID ";
		List<UserAccount> list = b.findBySQLWithPage(sql, null, 1, 10,UserAccount.class);
		System.out.println(list);
		
		PageRequest page = PageRequest.of(0, 10);
		Page<UserAccountView> list2 = c.findViewByName(page);
		System.out.println(list2.getContent().get(0).getCompanyName());
	}

}
