package com.jx.tdmp.service.Interface;

import com.jx.tdmp.entity.Organization;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiejie Xu
 * @since 2020-12-29
 */
public interface OrganizationService extends IService<Organization> {
    List<Organization> listOrganizations();
    Organization getOrganizationById(String id);
    Organization createOrganization(Organization Organization);
    Organization updateOrganization(Organization Organization);
    boolean deleteOrganization(String id);

}
