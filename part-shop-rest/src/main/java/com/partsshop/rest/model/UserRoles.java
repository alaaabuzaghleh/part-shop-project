package com.partsshop.rest.model;

public class UserRoles {
    private String roleName ; 
    
    public UserRoles(String roleName) {
		this.roleName = roleName ; 
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		return this.getRoleName() ; 
	}
    
    
    
}
