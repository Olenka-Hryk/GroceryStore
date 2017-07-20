package com.connection;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class Connection implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private Logger  logger;

	/**
	 * Get the user transaction by JNDI
	 * 
	 * @return the user transaction
	 */
	public UserTransaction getUserTransaction() {
		UserTransaction userTransaction = null;
		try {
			Context c = new InitialContext();
			userTransaction = (UserTransaction) c.lookup("java:comp/UserTransaction");
		} catch (Exception exception) {
			logger.log(Level.SEVERE, exception.getMessage(), exception);
		}

		return userTransaction;
	}
	
	/**
	 * Get the EntityManager by JNDI
	 * 
	 * @return the entity manager
	 */
	public EntityManager getEntityManager() {
		EntityManager entityManager = null;

		try {
			Context initCtx = new InitialContext();
			entityManager = (EntityManager) initCtx.lookup("java:comp/env/JSFPU");
		} catch (Exception exception) {
			logger.log(Level.SEVERE, exception.getMessage(), exception);
		}

		return entityManager;
	}

	public void begin() throws NotSupportedException, SystemException {
		getUserTransaction().begin();
	}

	public void commit() throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		getUserTransaction().commit();
	}

	public int getStatus() throws SystemException {
		return getUserTransaction().getStatus();
	}

	public void rollback() throws IllegalStateException, SecurityException, SystemException {
		getUserTransaction().rollback();
	}

	public void setRollbackOnly() throws IllegalStateException, SystemException {
		getUserTransaction().setRollbackOnly();
	}

	public void setTransactionTimeout(int timeout) throws SystemException {
		getUserTransaction().setTransactionTimeout(timeout);
	}	
}
