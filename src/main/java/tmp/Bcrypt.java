package tmp;

import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {

	public static void main(String[] args) {
		
		String tmp=BCrypt.hashpw("1234", BCrypt.gensalt());
		System.out.println("PW : "+tmp);
		System.out.println("PWCH : " + BCrypt.checkpw("1234", tmp));
	}

}
