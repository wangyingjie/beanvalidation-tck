/*
* JBoss, Home of Professional Open Source
* Copyright 2009, Red Hat, Inc. and/or its affiliates, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.hibernate.jsr303.tck.tests.xmlconfiguration;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecAssertions;
import org.testng.annotations.Test;

import org.hibernate.jsr303.tck.util.TestUtil;
import org.hibernate.jsr303.tck.util.shrinkwrap.WebArchiveBuilder;

import static org.hibernate.jsr303.tck.util.TestUtil.assertCorrectNumberOfViolations;

/**
 * @author Hardy Ferentschik
 */
public class OptionalValidationXmlTest extends Arquillian {

	@Deployment
	public static WebArchive createTestArchive() {
		return new WebArchiveBuilder()
				.withTestClass( OptionalValidationXmlTest.class )
				.withClasses( Order.class, OrderLine.class )
				.build();
	}

	@Test
	@SpecAssertions({
			@SpecAssertion(section = "4.4.6", id = "c")
	})
	public void testIgnoreValidationXml() {
		Validator validator = TestUtil.getValidatorUnderTest();

		Order order = new Order();
		Set<ConstraintViolation<Order>> constraintViolations = validator.validate( order );
		assertCorrectNumberOfViolations( constraintViolations, 0 );
	}
}
