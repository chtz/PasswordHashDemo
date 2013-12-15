package ch.furthermore.demo.passwordhash;

import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Base64;

public class PasswordHasher {
	public String randomSalt() {
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			return Base64.encodeBase64String(salt);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String passwordHash(String passwordSalt, String password) {
		try {
			int iterations = 20000; //FIXME how many iterations are appropriate?
			char[] chars = password.toCharArray();
			byte[] salt = passwordSalt.getBytes();
	
			PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skf.generateSecret(spec).getEncoded();
			return Base64.encodeBase64String(hash);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
