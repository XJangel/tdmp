package com.jx.tdmp.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户对应实体表 user
 * </p>
 *
 * @since 2020-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "user_id", type = IdType.UUID)
    private String userId;

    private String userName;

    private String passwd;

    private String userType;

    private String created_time;

}
