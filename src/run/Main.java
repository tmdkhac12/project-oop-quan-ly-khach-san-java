package run;

import java.text.ParseException;
import view.menu;
import view.login;

public class Main {

	public static void main(String[] args) {
		login login1 = new login();
		menu menu_1 = new menu();

		try {
			/* menu_1.show(); */
			login1.show();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("Cảm ơn đã xài dịch vụ");
	}
}
