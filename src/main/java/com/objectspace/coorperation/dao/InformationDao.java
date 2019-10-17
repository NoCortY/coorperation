package com.objectspace.coorperation.dao;

import com.objectspace.coorperation.entity.Information;
import org.apache.ibatis.annotations.Mapper;

/**
* @Description: 供需信息DAO类
* @Author: NoCortY
* @Date: 2019/10/17
*/
@Mapper
public interface InformationDao {
    /**
     * @Description:  插入供需信息数据
     * @Param: [information]
     * @return: 影响的行数
     * @Author: NoCortY
     * @Date: 2019/10/17
     */
    public Integer insertInformation(Information information);
}
