package com.portal.deals.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotEmpty
	@Length(min = 4)
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@NotEmpty
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@NotEmpty
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@NotEmpty
	@Email
	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@NotEmpty
	@Pattern(regexp = "(^$|[0-9]{10})")
	@Column(name = "MOBILE", nullable = false)
	private String mobile;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private VerificationToken token;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private PasswordResetToken passwordResettoken;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLE_MAP", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "USER_ROLE_ID") })
	private Set<UserRole> userRoles = new HashSet<UserRole>();

	@Column(name = "ENABLED")
	private boolean enabled;

	public User() {
		super();
		this.enabled = false;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", mobile=" + mobile + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + "]";
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the token
	 */
	public VerificationToken getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(VerificationToken token) {
		this.token = token;
	}

	/**
	 * @return the passwordResettoken
	 */
	public PasswordResetToken getPasswordResettoken() {
		return passwordResettoken;
	}

	/**
	 * @param passwordResettoken
	 *            the passwordResettoken to set
	 */
	public void setPasswordResettoken(PasswordResetToken passwordResettoken) {
		this.passwordResettoken = passwordResettoken;
	}

	/**
	 * @return the userRoles
	 */
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles
	 *            the userRoles to set
	 */
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
