package com.objectspace.coorperation.dto;

import com.objectspace.coorperation.entity.Information;
import com.objectspace.coorperation.enums.InformationStateEnum;

import java.util.List;

/**
* @Description: 信息 DTO
* @Author: NoCortY
* @Date: 2019/11/9
*/
public class InformationExecution {
    private InformationStateEnum informationStateEnum;
    private Information information;
    private List<Information> informationList;

    /**
     * @Description:  失败的构造器
     * @Param: [informationStateEnum]
     * @return:
     * @Author: NoCortY
     * @Date: 2019/11/9
     */
    public InformationExecution(InformationStateEnum informationStateEnum){
        this.informationStateEnum = informationStateEnum;
    }
    /**
     * @Description:  成功的构造器
     * @Param: [information, informationStateEnum]
     * @return:
     * @Author: NoCortY
     * @Date: 2019/11/9
     */
    public InformationExecution(Information information,InformationStateEnum informationStateEnum){
        this.information = information;
        this.informationStateEnum = informationStateEnum;
    }

    /**
     * @Description:  成功的构造器
     * @Param: [informationList, informationStateEnum]
     * @return:
     * @Author: NoCortY
     * @Date: 2019/11/9
     */
    public InformationExecution(List<Information> informationList,InformationStateEnum informationStateEnum){
        this.informationList = informationList;
        this.informationStateEnum = informationStateEnum;
    }

    public InformationStateEnum getInformationStateEnum() {
        return informationStateEnum;
    }

    public void setInformationStateEnum(InformationStateEnum informationStateEnum) {
        this.informationStateEnum = informationStateEnum;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public List<Information> getInformationList() {
        return informationList;
    }

    public void setInformationList(List<Information> informationList) {
        this.informationList = informationList;
    }
}
