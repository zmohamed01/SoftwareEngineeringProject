package org.javaproject;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DBConnectionTest.class, EmployeeTableTest.class, PaymentTableTest.class, CustomerOrdersTableTest.class })
public class AllTests {

}
