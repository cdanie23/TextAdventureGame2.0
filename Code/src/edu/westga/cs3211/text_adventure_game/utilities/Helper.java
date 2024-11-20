package edu.westga.cs3211.text_adventure_game.utilities;

/**
 * The helper class
 * @author Colby
 * @version Fall 2024
 */
public class Helper {
	
	/**
	 * Throws illegal argument exceptions for a empty or null string
	 * @param argument the argument to check for
	 * Precondition: argument != null && argument.IsEmpty()
	 */
	public static void throwExceptionForIllegalArgument(String argument) {
		if (argument == null) {
			throw new IllegalArgumentException("argument cannot be null");
		}
		if (argument.isEmpty()) {
			throw new IllegalArgumentException("argument cannot be empty");
		}
	}
}
