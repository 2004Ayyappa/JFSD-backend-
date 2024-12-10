package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.repository.AdminRepository;


@Service
public class AdminManager {

	@Autowired
	AdminRepository AR;
	
	public String saveAdmin(Admin A)
	{
		try
		{
			if(AR.validateUsername(A.username) > 0)
				throw new Exception("Username already exist!");		
			
			AR.save(A);
			return "New Admin has been added";
		}catch (Exception e)
		{
			return e.getMessage();
		}
	}
	public String loginAdmin(String uname, String pwd)
	{
		try
		{
			if(AR.validateCredentials(uname, pwd) == 0)
				throw new Exception("401");
			
			return "200";				
		}catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
}
