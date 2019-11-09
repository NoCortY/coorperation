package com.objectspace.coorperation.service.impl;

import com.objectspace.coorperation.dao.InformationDao;
import com.objectspace.coorperation.dto.InformationExecution;
import com.objectspace.coorperation.entity.Information;
import com.objectspace.coorperation.service.InformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    InformationDao informationDao;

    @Override
    public InformationExecution insertInformation(Information information, CommonsMultipartFile informationImg) {
        Session session = SecurityUtils.getSubject().getSession();
        return null;
    }
}
