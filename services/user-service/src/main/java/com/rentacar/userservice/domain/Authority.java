package com.rentacar.userservice.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority extends BaseEntity implements GrantedAuthority {

    private String name;

    public Authority() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Authority authority = (Authority) obj;
        return Objects.equals(name, authority.getName());
	}
    
    
}
