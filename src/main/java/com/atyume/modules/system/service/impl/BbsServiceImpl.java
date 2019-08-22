package com.atyume.modules.system.service.impl;

import com.atyume.modules.base.service.impl.BaseService;
import com.atyume.modules.system.mapper.BbsMapper;
import com.atyume.modules.system.po.Bbs;
import com.atyume.modules.system.service.BbsService;
import com.atyume.modules.system.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsServiceImpl extends BaseService<Bbs> implements BbsService {
    @Autowired
    private BbsMapper bbsMapper;

    @Override
    public List<BbsVO> queryBbs() {
        return bbsMapper.queryAllBbs();
    }
}
