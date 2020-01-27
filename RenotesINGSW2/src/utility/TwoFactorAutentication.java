package utility;

import dao.DBManager;
import model.User;

public class TwoFactorAutentication 
{
	public static boolean checkCode(String code, User user)
	{
		boolean check = false;
		
		if(DBManager.getInstance().getUserDao().getVerificationCode(user).equals(code))
		{
			check = true;
		}
		
		return check;
	}
	
	public static boolean getTwoFactorAutenticationActivated(User user)
	{
		return DBManager.getInstance().getUserDao().getTwoFactorAutenticationActivated(user);
	}
}
