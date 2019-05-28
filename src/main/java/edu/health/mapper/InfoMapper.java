package edu.health.mapper;

import edu.health.domain.Info;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface InfoMapper extends Mapper<Info> {
    List<Info> selectPoByExample(Example example);
}
