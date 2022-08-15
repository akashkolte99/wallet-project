package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.BillPayment;
import com.masai.model.Transaction;
import com.masai.services.BillPaymentImpl;
import com.masai.services.TransactionSevices;

@RestController
public class BillPaymentController {

	@Autowired
	private TransactionSevices trServices;
	
	@Autowired
	private BillPaymentImpl billservice;
	
	@PostMapping("/billpay")
	public BillPayment billpayment(@RequestParam String key ,@RequestBody BillPayment bill) {
		return  billservice.addBill(key, bill);
	}
	
	@GetMapping("/billlist")
	public List<BillPayment> getallbills(@RequestParam String key){
		return billservice.billList(key);
	}
	

	@GetMapping("/transactionlist")
	public List<Transaction> getTransactionList(@RequestParam String key) {
		
		return trServices.viewAllTransaction(key);
	}
	
	
	
}
