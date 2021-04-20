package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;

import java.nio.charset.UnsupportedCharsetException;
import java.sql.Date;
import java.util.Properties;

import javax.mail.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEmail {

	private static final String[] TEST_EMAILS = {"e@gm.com", "hg_ch@j.co", "web@bh", "abcd@efg"};
	
	private EmailConcrete email;
	
	@Before
	public void setUp() throws Exception {
		
		email = new EmailConcrete();
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	
	// testing addBcc function with TEST_EMAILS
	@Test
	public void addBcctest() throws Exception {
		
		email.addBcc(TEST_EMAILS); // add bcc emails
		int numberOfEmails = TEST_EMAILS.length;
		
		assertEquals(numberOfEmails, email.getBccAddresses().size());
	}


	// testing addCc function with 2 email
	@Test
	public void addCctest() throws Exception {
		
		email.addCc(TEST_EMAILS[0]); // add Cc email
		email.addCc(TEST_EMAILS[1]); // ad Cc email
		
		assertEquals(2, email.getCcAddresses().size());
	}
	
	
	// testing addHeader function with 1 header
	@Test
	public void addHeadertest1() throws Exception {
		
		email.addHeader("name", "value"); // adding header with name and value
		
		assertEquals(1, email.getHeaders().size());
	}
	
	// testing addHeader function with empty name
	@Test(expected = IllegalArgumentException.class)
	public void addHeadertest2() throws Exception {
		
		email.addHeader("" , "value");
		
		assertEquals(0, email.getHeaders().size());
	}


	// testing AddReplyTofunction
	@Test
	public void addReplyTotest() throws Exception {
		
		email.addReplyTo("email@gm.com", "testname");
		
		assertEquals(1, email.getReplyToAddresses().size());
	}
	

	// testing getHostName
	@Test
	public void getHostNametest1() {
		
		// set and get host name
		email.setHostName("local host");
		String hostname = email.getHostName();
		
		assertEquals("local host", hostname);
	}
	
	
	//testing getHostName with session
	@Test()
	public void getHostNametest3() {
		
		Properties properties = new Properties();
		Session session = Session.getDefaultInstance(properties,null);
		properties.put(EmailConstants.MAIL_HOST, "1ocal host");
		email.setMailSession(session);
		
		assertEquals("local host", email.getHostName());
	}



	// test getSentDate
	@Test
	public void getSentDateTest1() {
		
		email.setSentDate(new Date(0));
		
		assertEquals(new Date(0), email.getSentDate());
	}
	
	// test getSentDate with null date
	@Test
	public void getSentDateTest2() {
		// null date
		email.getSentDate();
	}




	// getSocketConnectionTimeout tests
	@Test
	public void testGetSocketConnectionTimeOut() {
		
		email.setSocketConnectionTimeout(0);
		
		assertEquals(0, email.getSocketConnectionTimeout());
	}





	 // testing setFrom
		@Test
		public void SetFromTest() throws Exception {
			
			email.setFrom("from@f.com");
			
		}





	// testing BuildMimeMessage
	@Test
	public void buildMimeMsgTest1() throws EmailException {
		
		email.setHostName("testhost");
		email.setSmtpPort(54545);
		email.setFrom(".domain@s");
		email.addTo("to@a.com");
		email.setSubject("subject");
		
		try {
		email.setCharset("ISO-8859-1");
		} catch(UnsupportedCharsetException ex) {
			
		}
     	email.setContent("content", "r/s");
     	
     	email.addCc("al@a.com");
     	email.addBcc("ac@ac.com");
     	email.addHeader("name", "val");
     
     	email.addReplyTo("s@k.com");
     	
		email.buildMimeMessage();
	}

	// testing BuildMimeMessage with null content
	@Test
	public void buildMimeMsgTest2() throws EmailException {
	
		email.setHostName("local host");
		email.setSmtpPort(46465);
		email.setFrom("testing@b.com");
		email.addTo("testtest@k.com");
 		
     	email.setContent(null);
     	
     	email.buildMimeMessage();
     	
	}
	
	// testing BuildMimeMessage with session
	@Test(expected = IllegalStateException.class)
	public void BuildMimeMsgTest3() throws EmailException {
		
		Properties properties = new Properties();
		Session session = Session.getDefaultInstance(properties,null);
		properties.put(EmailConstants.MAIL_HOST, "local host");
		
		email.setHostName("local host");
		
	    email.message=email.createMimeMessage(session);
		
		email.buildMimeMessage();
	}







	// testing getMailSession
	@Test
	public void GetMailSessionTest() throws EmailException {
		
		email.setHostName("host");
		email.setAuthentication("u", "p");
		email.setBounceAddress("jhwbe@.com");
		
		email.getMailSession();
	}








}