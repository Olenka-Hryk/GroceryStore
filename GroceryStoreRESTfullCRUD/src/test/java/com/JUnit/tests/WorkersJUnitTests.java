package com.JUnit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.store.GroceryStore.GroceryStoreResTfullCrudApplication;
import com.store.components.entity.Workers;
import com.store.components.service.WorkersDao;
import com.store.service.WorkersService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { GroceryStoreResTfullCrudApplication.class })
public class WorkersJUnitTests {

	@Autowired
	protected WorkersDao workersDao;

	@Autowired
	protected WorkersService workersService;

	@Test
	public void testAddWorkers_success() {
		System.out.println("Creating a new worker");

		Workers newWorker = new Workers();
		newWorker.setSurName("Ania");
		newWorker.setName("Li");
		newWorker.setMiddleName("Foo");
		newWorker.setDateBirth("24-08-1997");

		assertNotNull(newWorker);

		workersService.addWorker(newWorker);
		Workers findWorker = workersDao.searchByIdWorker(10002);
		assertEquals("Ania", findWorker.getSurName());
	}

	@Test
	public void testFindAndReadExistingWorkersById_success() throws Exception {
		Workers worker = new Workers();
		worker = workersDao.searchByIdWorker(10001);

		assertEquals("Urbanska", worker.getSurName());
		assertEquals("Nadia", worker.getName());
	}

	@Test
	public void TestReturnNullWhenWorkerCannotBeFoundById_success() throws Exception {
		Workers nullWorker = new Workers();
		nullWorker = workersDao.searchByIdWorker(10);
		assertNull(nullWorker);
	}
}
