package com.objectspace.coorperation.service;

import com.objectspace.coorperation.dto.InformationImgExecution;
import com.objectspace.coorperation.entity.InformationImg;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
* @Description: 信息详情图服务类接口
* @Author: NoCortY
* @Date: 2019/10/31
*/
public interface InformationImgService {
    /**
     * @Description: 添加信息详情图
     * @Param: [InformationImg,CommonsMultipartFile]
     * @return: InformationImgExecution
     * @Author: NoCortY
     * @Date: 2019/11/7
     */
    public InformationImgExecution addInformationImg(List<InformationImg> informationImgList, List<CommonsMultipartFile> imgFileList);
}
