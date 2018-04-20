package harmanassignment2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * password generator class
 * @author Harmandeep
 */
public class passwordCheck {
    public static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom encryptedSalt = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        encryptedSalt.nextBytes(salt);
        return salt;
    }
     //generate encrypted password
    public static String getPW(String normalPw, byte[] salt)
    {
        
        String encryptedPassword = null;
        try
        {
            //configure the hashing algorithm
            MessageDigest gen = MessageDigest.getInstance("SHA-512");
            gen.update(salt);
            
            byte[] hashed = gen.digest(normalPw.getBytes());
            
            StringBuilder sb = new StringBuilder();
                  
            for (int i=0; i<hashed.length; i++)
            {
                sb.append(Integer.toString((hashed[i] & 0xff)+0x100,16).substring(1));
            }
   
            encryptedPassword = sb.toString();
          
        }
        catch (NoSuchAlgorithmException e)
        {
           System.err.println(e);
        }
        
        return encryptedPassword;
    }
}
