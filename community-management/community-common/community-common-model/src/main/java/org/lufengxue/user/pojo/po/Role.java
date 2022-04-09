package org.lufengxue.user.pojo.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： Role构建
 */
@Table(name="ele_role")
public class Role implements Serializable{

	@Id
    @Column(name = "role_id")
	private String roleId;//角色id

    @Column(name = "role_name")
	private String roleName;//角色姓名

    @Column(name = "role_code")
	private String roleCode;//角色编码



	//get方法
	public String getRoleId() {
		return roleId;
	}

	//set方法
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	//get方法
	public String getRoleName() {
		return roleName;
	}

	//set方法
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	//get方法
	public String getRoleCode() {
		return roleCode;
	}

	//set方法
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


}
