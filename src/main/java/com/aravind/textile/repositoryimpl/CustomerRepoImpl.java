package com.aravind.textile.repositoryimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aravind.textile.model.Customer;
import com.aravind.textile.repository.CustomerRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CustomerRepoImpl implements CustomerRepo {
	static final String SUCCESS = "Success";
	static final String FAILURE = "Failure";
	EntityManager em;

	public CustomerRepoImpl(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public String save(Customer customer) {
		if (customer != null) {
			em.merge(customer);
			return SUCCESS;
		} else {
			return FAILURE;
		}

	}

	@Override
	public List<Customer> findAllCustomers() {
		String hql = "from Customer";
		Query query = em.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public String update(Customer customer) {
		if(customer != null) {
			em.merge(customer);
			return SUCCESS;
		}
		return FAILURE;
		

	}

	@Override
	public Customer findById(int customerId) {
		return em.find(Customer.class, customerId);
	}

	@Override
	public String delete(int customerId) {
		Customer id = em.find(Customer.class, customerId);
		if(id != null) {
			em.remove(id);
			return SUCCESS;
		}
		return FAILURE;
		
	}

}
