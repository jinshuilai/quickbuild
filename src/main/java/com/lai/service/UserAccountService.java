package com.lai.service;
import com.lai.domain.UserAccount;
import com.lai.framework.base.BaseService;


/**
 * Created by Mao on 2019/03/15.
 */
public interface UserAccountService extends BaseService<UserAccount> {

	UserAccount login(String userName, String password);

	void regist(UserAccount user);

}
