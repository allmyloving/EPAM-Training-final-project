package ua.nure.serdyuk.SummaryTask4.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.exception.AppException;

public final class Util {

	private static final Logger LOG = Logger.getLogger(Util.class);

	public static String hash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] bytes = digest.digest(input.getBytes("utf-8"));

			// convert to hex
			for (int i = 0; i < bytes.length; i++) {
				hash.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			LOG.error(String.format("Error while creating hash -- %s",
					e.getMessage()));
			throw new AppException(e.getMessage(), e);
		}
		return hash.toString();
	}

}
