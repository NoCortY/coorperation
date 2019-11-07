package com.objectspace.coorperation.service.impl;

import com.objectspace.coorperation.dto.InformationImgExecution;
import com.objectspace.coorperation.entity.InformationImg;
import com.objectspace.coorperation.enums.InformationImgStateEnum;
import com.objectspace.coorperation.service.InformationImgService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;
/**
* @Description: 信息详情图业务逻辑类
* @Author: NoCortY
* @Date: 2019/11/7
*/
@Service
public class InformationImgServiceImpl implements InformationImgService {
    /**
     * @Description: 新增详情图
     * @Param: [informationImg, imgFileList]
     * @return: com.objectspace.coorperation.dto.InformationImgExecution
     * @Author: NoCortY
     * @Date: 2019/11/7
     */
    @Override
    public InformationImgExecution addInformationImg(List<InformationImg> informationImgList, List<CommonsMultipartFile> imgFileList) {

        InformationImgExecution informationImgExecution = null;
        if(informationImgList==null||informationImgList.size()==0||imgFileList==null||imgFileList.size()==0){
            informationImgExecution = new InformationImgExecution(InformationImgStateEnum.INFORMATIONIMG_ISNULL);
            return informationImgExecution;
        }
        for(InformationImg informationImg:informationImgList){
            /*if(informationImg.getInformationImgOwner().getInformationOwner().getUserId())
            informationImg.setInformationImgCreateTime(new Date());
            informationImg.setInformationImgStatus(true);
            informationImg.setInformationImgDesc("商品:"+informationImg.getInformationImgOwner()+"详情图");*/
        }
        return null;

    }
}
