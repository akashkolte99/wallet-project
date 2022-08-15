package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@NotEmpty(message = "Name could not be null")
	private String name;
  
	@Pattern(regexp = "[789]{1}[0-9]{9}")
	private String mobileNumber;
	
	@NotNull(message = "Password  could not be null")
	@Size(min=4,max=10,message ="Password size min 4 and max 10 required")
	private String password;
	
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
	
	
	

}
