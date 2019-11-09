package com.objectspace.coorperation.service;

import com.objectspace.coorperation.dto.InformationExecution;
import com.objectspace.coorperation.entity.Information;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface InformationService {
    public InformationExecution insertInformation(Information information, CommonsMultipartFile informationImg);
}
