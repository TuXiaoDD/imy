package com.example.imserver.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imserver.entity.ContactDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @packageName: com.example.imserver.dao.mapper
 * @className: ContactMapper
 * @author: leMin
 * @date: 2023/3/6  23:21
 * @description:    好友信息数据交互层
 */
@Mapper
public interface ContactMapper extends BaseMapper<ContactDO> {

}
