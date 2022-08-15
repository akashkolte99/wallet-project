package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BillPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billid;
	
	@NotEmpty(message = "Bill type required")
	private String billtype;
	
	@NotEmpty(message = "Please enter amount")
	private Double amount;
    private LocalDate billdate;
  
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Wallet wallet;
}
