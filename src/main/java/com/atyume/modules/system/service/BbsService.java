package com.atyume.modules.system.service;

import com.atyume.modules.base.service.IService;
import com.atyume.modules.system.po.Bbs;
import com.atyume.modules.system.vo.BbsVO;

import java.util.List;

public interface BbsService extends IService<Bbs> {

    List<BbsVO> queryBbs();

}