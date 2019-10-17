package com.objectspace.coorperation.dao;

import com.objectspace.coorperation.entity.InformationImg;
import org.apache.ibatis.annotations.Mapper;

/**
* @Description: 供需信息详情图DAO
* @Author: NoCortY
* @Date: 2019/10/17
*/
@Mapper
public interface InformationImgDao {
    /**
     * @Description:  插入供需信息图片数据
     * @Param: [information]
     * @return: Integer 影响的行数
     * @Author: NoCortY
     * @Date: 2019/10/17
     */
    public Integer insertInformationImg(InformationImg informationImg);
}
