package com.atyume.modules.system.mapper;

import com.atyume.core.utils.MyMapper;
import com.atyume.modules.system.po.Bbs;
import com.atyume.modules.system.vo.BbsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BbsMapper extends MyMapper<Bbs> {
    List<BbsVO> queryAllBbs();
}