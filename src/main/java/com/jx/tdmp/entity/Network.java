package com.jx.tdmp.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiejie Xu
 * @since 2020-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Network implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String userId;

    private Integer consensusMode;

    private LocalDateTime createTs;

    private LocalDateTime applyTs;

    private LocalDateTime releaseTs;

    private Integer size;

    private Boolean health;


}
