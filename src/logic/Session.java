/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic;

public class Session {

	private static String username;
	private static double startDeposit;
	private static int userId;

	private Session() {
		throw new IllegalStateException("Utility class");
	}

	public static String getUsername() {
		return username;
	}

	public double getStartDeposit() {
		return startDeposit;
	}

	public static int getUserId() {
		return userId;
	}

	public static void setUsername(String username) {

		Session.username = username;
	}

	public static void setStartDeposit(Double startDeposit) {

		Session.startDeposit = startDeposit;
	}

	public static void setUserId(int userId) {

		Session.userId = userId;
	}

	public static void empty() {
		username = null;
		startDeposit = 0;
		userId=0;
	}
}
