package org.xy.cloud.system.server.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xy.cloud.system.server.domain.user.vo.User;

/**
 * @author lch
 * @date 2025/7/9
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
