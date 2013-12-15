package ch.furthermore.demo.passwordhash;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordHasherTest {
	@Test
	public void ratherMeaninglessTest() {
		String password = "password123";
		
		PasswordHasher hasher = new PasswordHasher();
		
		String salt1 = hasher.randomSalt();
		String hash1a = hasher.passwordHash(salt1, password);
		String hash1b = hasher.passwordHash(salt1, password);
		
		assertEquals(hash1a, hash1b);
		
		String salt2 = hasher.randomSalt();
		String hash2 = hasher.passwordHash(salt2, password);
		
		assertNotEquals(hash1a, hash2);
	}
}
