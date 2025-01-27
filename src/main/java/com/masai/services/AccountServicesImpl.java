package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.masai.exceptions.InvalidAccountException;
import com.masai.exceptions.UserAlreadyExistWithMobileNumber;
import com.masai.model.BankAccount;
import com.masai.model.Wallet;
import com.masai.repository.BankAccountDao;
import com.masai.repository.WalletDao;
import com.masai.util.GetCurrentLoginUserSessionDetailsIntr;

@Component
public class AccountServicesImpl implements AccountServicesIntr {

	@Autowired
	BankAccountDao bDao;
	
	@Autowired
	WalletDao wDao;
	
	
	@Autowired
    private GetCurrentLoginUserSessionDetailsIntr getCurrentLoginUser;
	
	
	@Override
	public BankAccount addAccount(BankAccount bank, String key) {
		
		boolean flag = 	bDao.existsById(bank.getAccountNo());
		
		
		if(flag) {
			throw new UserAlreadyExistWithMobileNumber("This bank account is already added..");
		}
		
		 Wallet wallet = getCurrentLoginUser.getCurrentUserWallet(key);

		  
		   wallet.getBankaccounts().add(bank);
			wDao.save(wallet);
			
			return bank;
		
	}
     
	@Override
	public List<BankAccount> getAllBankAccounts(String key){
		
		 Wallet wallet =	getCurrentLoginUser.getCurrentUserWallet(key);
		 
		 if(wallet.getBankaccounts().size()==0) {
			 throw new InvalidAccountException("No Bank Accounts Added");
		 }
		  
		  return  wallet.getBankaccounts();
		   
	}
	
	
	public BankAccount removeBankAccount(String key,long accountNo) {
		
		 Wallet wallet =	getCurrentLoginUser.getCurrentUserWallet(key);
		 
		List<BankAccount> banks = wallet.getBankaccounts();
		
		for(BankAccount bank:banks) {
			if(bank.getAccountNo()==accountNo) {
				
				banks.remove(bank);
				bDao.delete(bank);
				wDao.save(wallet);
				return bank;	
			}
		}
		
		throw new InvalidAccountException("Check Account Number");
	}
	
	
	
	
	  
}
