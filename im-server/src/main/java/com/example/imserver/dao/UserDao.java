package com.example.imserver.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.utils.DataUtils;
import com.example.imserver.entity.UserDO;
import com.example.imserver.dao.mapper.UserMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDao extends ServiceImpl<UserMapper, UserDO> {


    public List<UserDO> queryUsers(List<Long>ids){
        if(DataUtils.isEmpty(ids))return List.of();
        return getBaseMapper().selectBatchIds(ids).stream()
                .filter(u -> u.getIsDelete() == 0)
                .collect(Collectors.toList());
    }

}
