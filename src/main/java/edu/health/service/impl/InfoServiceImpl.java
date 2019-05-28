package edu.health.service.impl;

import edu.health.core.base.service.BaseServiceImpl;
import edu.health.domain.Info;
import edu.health.mapper.InfoMapper;
import edu.health.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 执笔
 * @date 2019/5/28 18:07
 */
@Service
public class InfoServiceImpl extends BaseServiceImpl<InfoMapper, Info> implements InfoService {


    @Autowired
    private InfoMapper infoMapper;

}
