package com.Url.Deeplink.Converter.Repository;

import org.springframework.data.repository.CrudRepository;

import com.Url.Deeplink.Converter.Models.Transaction;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
	Transaction findTransactionByContentId(int contentId);

}
