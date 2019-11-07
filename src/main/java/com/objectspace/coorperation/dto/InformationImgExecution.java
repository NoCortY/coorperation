package com.objectspace.coorperation.dto;

import com.objectspace.coorperation.entity.InformationImg;
import com.objectspace.coorperation.enums.InformationImgStateEnum;

import java.util.List;
/**
* @Description: InformationDto类，Service层和Web层的数据交互
* @Author: NoCortY
* @Date: 2019/11/7
*/
public class InformationImgExecution {
    private InformationImg informationImg;
    private List<InformationImg> informationImgList;
    private InformationImgStateEnum informationImgStateEnum;

    /**
     * @Description: 失败的构造器
     * @Param: [informationImgStateEnum]
     * @return:
     * @Author: NoCortY
     * @Date: 2019/11/7
     */
    public InformationImgExecution(InformationImgStateEnum informationImgStateEnum){

        this.informationImgStateEnum = informationImgStateEnum;
    }
    /**
     * @Description: 成功的构造器
     * @Param: [informationImg, informationImgStateEnum]
     * @return:
     * @Author: NoCortY
     * @Date: 2019/11/7
     */
    public InformationImgExecution(InformationImg informationImg,InformationImgStateEnum informationImgStateEnum){
        this.informationImg = informationImg;
        this.informationImgStateEnum = informationImgStateEnum;
    }
    /**
     * @Description: 成功的构造器
     * @Param: [informationImgList, informationImgStateEnum]
     * @return:
     * @Author: NoCortY
     * @Date: 2019/11/7
     */
    public InformationImgExecution(List<InformationImg> informationImgList,InformationImgStateEnum informationImgStateEnum){
        this.informationImgList = informationImgList;
        this.informationImgStateEnum = informationImgStateEnum;
    }

    public InformationImg getInformationImg() {
        return informationImg;
    }

    public void setInformationImg(InformationImg informationImg) {
        this.informationImg = informationImg;
    }

    public List<InformationImg> getInformationImgList() {
        return informationImgList;
    }

    public void setInformationImgList(List<InformationImg> informationImgList) {
        this.informationImgList = informationImgList;
    }

    public InformationImgStateEnum getInformationImgStateEnum() {
        return informationImgStateEnum;
    }

    public void setInformationImgStateEnum(InformationImgStateEnum informationImgStateEnum) {
        this.informationImgStateEnum = informationImgStateEnum;
    }
}
