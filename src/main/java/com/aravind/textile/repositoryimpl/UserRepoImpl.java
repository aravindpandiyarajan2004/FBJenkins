package com.aravind.textile.repositoryimpl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.aravind.textile.model.User;
import com.aravind.textile.repository.UserRepo;

import jakarta.persistence.EntityManager;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepoImpl implements UserRepo {

	EntityManager em;

	public UserRepoImpl(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public String save(User user) {
		if (user != null) {
			em.merge(user);
			return "Success";
		}
		return "Failure";

	}



	@Override
	public Optional<User> findByUsernameAndPassword(String username, String password) {
		TypedQuery<User> query = em.createQuery(
				"SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		User user = query.getResultStream().findFirst().orElse(null);
		return Optional.ofNullable(user);
	}

}
