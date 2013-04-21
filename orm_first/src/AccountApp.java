import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.db.DatabaseType;

import java.sql.SQLException;
import java.util.*;
import java.lang.reflect.*;

public class AccountApp {
	private ConnectionSource connectionSource;
	private Map<String, Method> command = new HashMap<String, Method>();
	
	public Dao<Account, String> connect() throws SQLException{
		String databaseUrl = "jdbc:h2:baza";
	        connectionSource = new JdbcConnectionSource(databaseUrl,"sa", "");
		Dao<Account, String> accDao = DaoManager.createDao(connectionSource, Account.class);
		return accDao;
	}
	public void write() throws SQLException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Text");
		String text = sc.nextLine();

		System.out.println("Prioritet");
		int prioritet = sc.nextInt();
		Account account = new Account();
		account.setText(text);
	        account.setPrioritet(prioritet);
	        Dao<Account, String> accountDao = connect();
	        accountDao.create(account);
	        System.out.println("Enter some string to continue");
	        sc.next();
	        connectionSource.close();
	}
	public void show() throws SQLException{
		Dao<Account, String> accountDao = connect();
		List<Account> list = accountDao.queryForAll();
		
        for (Account ball : list)
        	System.out.println(ball.getText() + " " + ball.getPrioritet());
        
        connectionSource.close();
	}
	public void remove() throws SQLException{
		Scanner sc = new Scanner(System.in);
		String id = sc.next();
		Dao<Account, String> accountDao = connect();

                accountDao.delete(accountDao.queryForId(id));
                System.out.println("Enter some string to continue");
                sc.next();
                connectionSource.close();
	}
	public void fill_command() throws NoSuchMethodException, SecurityException{
		command.put("1", AccountApp.class.getMethod("write"));
		command.put("2", AccountApp.class.getMethod("show"));
		command.put("3", AccountApp.class.getMethod("remove"));
	}
	private boolean select(String str) throws SQLException{
		boolean detect = false;
		if(command.get(str).equals(null))
			detect = true;
		return detect;
	}
        public static void main(String[] args) throws Exception {
    	        AccountApp a = new AccountApp();
    	         Scanner sc = new Scanner(System.in);
    	
    	  	while(true){
	    		System.out.println("1 - Write\n2 - Show\n3 - Remove");
		    	String stroka = sc.nextLine();
		    	if(a.select(stroka)){
		    		System.out.println("Enter any key");
		    		sc.nextLine();
		    		continue;
	    		}
    		}
        
    	}
}
