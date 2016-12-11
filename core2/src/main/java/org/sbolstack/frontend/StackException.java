package org.sbolstack.frontend;

/**
 * Thrown when the SBOL Stack returns an error code.
 * @author James McLaughlin
 *
 */
public class StackException extends Exception
{
      static final long serialVersionUID = 1;
    
      StackException()
      {
          super();
      }
      
      StackException(String message)
      {
          super(message);
      }
      
      StackException(String message, Throwable cause)
      {
          super(message, cause);
      }
      
      StackException(Throwable cause)
      {
          super(cause);
      }
}
