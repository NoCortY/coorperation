package com.objectspace.coorperation.service.impl;

import com.objectspace.coorperation.dao.InformationDao;
import com.objectspace.coorperation.dao.InformationImgDao;
import com.objectspace.coorperation.dto.InformationImgExecution;
import com.objectspace.coorperation.entity.InformationImg;
import com.objectspace.coorperation.enums.InformationImgStateEnum;
import com.objectspace.coorperation.service.InformationImgService;
import com.objectspace.coorperation.util.ImageUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.ibatis.transaction.Transaction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
    @Autowired
    InformationImgDao informationImgDao;


    Logger logger = LoggerFactory.getLogger(InformationImgServiceImpl.class);

    /**
     * @Description: 新增详情图
     * @Param: [informationImg, imgFileList]
     * @return: com.objectspace.coorperation.dto.InformationImgExecution
     * @Author: NoCortY
     * @Date: 2019/11/7
     */
    @Override
    @Transactional
    public InformationImgExecution addInformationImg(List<InformationImg> informationImgList, List<CommonsMultipartFile> imgFileList) {
        //获取当前Session
        Session session = SecurityUtils.getSubject().getSession();
        InformationImgExecution informationImgExecution = null;
        if(informationImgList==null||informationImgList.size()==0||imgFileList==null||imgFileList.size()==0){
            informationImgExecution = new InformationImgExecution(InformationImgStateEnum.INFORMATIONIMG_ISNULL);
            return informationImgExecution;
        }
        for(InformationImg informationImg:informationImgList){
            if(informationImg.getInformationImgOwner().getInformationOwner().getUserId()==session.getAttribute("userId")){
                try{
                    informationImgDao.insertInformationImg(informationImg);
                }catch(Exception e){
                    logger.error("往数据库中插入图片信息错误，回滚事务");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    informationImgExecution = new InformationImgExecution(InformationImgStateEnum.SYSTEM_ISNULL);
                    return informationImgExecution;
                }
            }else{
                informationImgExecution = new InformationImgExecution(InformationImgStateEnum.INFORMATIONIMG_OPERATORERROR);
                logger.error("用户:"+informationImg.getInformationImgOwner().getInformationOwner().getUserName()+"试图修改"+session.getAttribute("userId")+"的数据！已被定义为非法操作，事务回滚!");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return informationImgExecution;
            }
        }
        for(CommonsMultipartFile imgFile:imgFileList){
            try{
                ImageUtil.generateInfomationDescImg(imgFile,informationImgList.get(0).getInformationImgOwner().getInformationId()+"/");
            }catch(Exception e){
                logger.error("转存详情图失败，回滚事务。");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                informationImgExecution = new InformationImgExecution(InformationImgStateEnum.INFORMATIONIMG_SAVEFAILURE);
                return informationImgExecution;
            }
        }
        informationImgExecution = new InformationImgExecution(InformationImgStateEnum.SAVEINFORMATIONIMG_SUCCESS);
        return informationImgExecution;
    }
}
