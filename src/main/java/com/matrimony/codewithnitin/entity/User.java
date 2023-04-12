package com.matrimony.codewithnitin.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Integer userId;

	@NotNull
	@Size(min = 2)
	private String firstName;

	@NotNull
	@Size(min = 2)
	private String middleName;

	@NotNull
	@Size(min = 2)
	private String lastName;

	@Email(message = "Email address is not valid !!")
	@Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String emailId;

	@NotNull
	private String password;

	@Size(min = 0, max = 10, message = "mobile number at least 10 numbers")
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String mobileNo;

	@NotNull
	private String displayName;

	@CreationTimestamp
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	private LocalDateTime dateUpdated;

//	@Enumerated(EnumType.STRING)
//	private Role role = Role.USER;
	
	 @Enumerated(EnumType.STRING)
	  private Role role;
	


	
	@OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
	private Profile profile;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Token token;
	
	//usermember mapping
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserMember> userMemberships;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	
	
	
//	 @Override
//	    public Collection<? extends GrantedAuthority> getAuthorities() {
//	        if (role != null) {
//	            return List.of(new SimpleGrantedAuthority(role.name()));
//	        } else {
//	            return Collections.emptyList();
//	        }
//	    }

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return emailId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// mapping of user-membership 
		 @OneToOne(cascade = CascadeType.MERGE)
		@JoinColumn(name = "MembershipPlan_Id", referencedColumnName = "MemberPlanId")
		// @JsonIgnore
		private MembershipPlan membershipPlan;

		

		

		 
}
