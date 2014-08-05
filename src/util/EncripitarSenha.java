package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public class EncripitarSenha {
	
	public static String encriptar(String senha) {
		try {
			/* Digester criado com o algoritimo MD5
			 * Al�m desse, o Java suporta ainda MD2, SHA, SHA-1, SHAg-256, SHA-384 e outros*/
			MessageDigest digest = MessageDigest.getInstance("MD5");
			//O metodo update adiciona bytes a mensagem a ser criptografada
			digest.update(senha.getBytes());
			/* Cria-se um encoder para impedir que a senha encriptada apare�a
			 * com  caracteres que costumam causar problemas em xml e alguns bancoa de dados*/
			BASE64Encoder encoder = new BASE64Encoder();
			/* O m�todo digest.digest() retorna a mensagem encriptada
			 * O m�todo encoder.encode() retorna os caracteres problem�ricos
             */
			return encoder.encode(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return senha;
		}
	}
	
}
