package com.thinkme.sys.user.service.impl;

import com.thinkme.framework.base.service.BaseServiceImpl;
import com.thinkme.sys.user.entity.User;
import com.thinkme.sys.user.entity.UserStatusHistory;
import com.thinkme.sys.user.service.UserStatusHistoryService;
import com.thinkme.utils.base.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/09 下午5:52
 */
@Service
@Slf4j
public class UserStatusHistoryServiceImpl extends BaseServiceImpl<UserStatusHistory> implements UserStatusHistoryService {


    @Override
    public String getLastReason(User user) {
        List<UserStatusHistory> list = query(Cnd.where("user_id", "=", user.getId()).desc("op_date"));
        if (BeanUtils.isEmpty(list)) {
            return "";
        }
        return list.get(0).getReason();
    }
}
