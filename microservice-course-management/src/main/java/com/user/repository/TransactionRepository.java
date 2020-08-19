package com.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	   	
		List<Transaction> findAllByUserId(Long userId);

	    List<Transaction> findAllByCourseId(Long courseId);

}
