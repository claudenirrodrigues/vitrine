package com.vitrine.utils;

public class Logger {

	public static final int INFO = 1;
	public static final int WARNING = 2;
	public static final int ERROR = 3;

	public static void log(String msg, int log) {

		switch (log) {
		case INFO:
			System.out.println("Vitrine INFO: " + msg);
			break;
		case WARNING:
			System.out.println("Vitrine WARNING: " + msg);
			break;
		case ERROR:
			System.err.println("Vitrine ERROR: " + msg);
			break;
		}
	}

	public static void log(String msgFriendly, String className, String msgError, int log) {
		StringBuffer sb = new StringBuffer();
		sb.append(msgFriendly);
		sb.append(" Entidade: ");
		sb.append(className);
		sb.append(" - Erro: ");
		sb.append(msgError);
		log(sb.toString(),log);
	}

}
