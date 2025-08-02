package org.xy.cloud.system.server.domain.user.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lch
 * @date 2025/7/9
 */
@Data
@TableName("system_user")
public class User {

    @TableId
    private Long id;

    private String username;

    private String password;

    private String name;

    private int age;

    private String post;

    private LocalDateTime createTime;
}
