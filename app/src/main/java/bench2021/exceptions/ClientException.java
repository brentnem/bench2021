package bench2021.exceptions;

/**
 * Simple exception wrapper to clean up the code a bit. Real application should
 * use more granular exception handling.
 */
public class ClientException extends Exception {

  public ClientException(String errorMessage) {
    super(errorMessage);
  }
}
