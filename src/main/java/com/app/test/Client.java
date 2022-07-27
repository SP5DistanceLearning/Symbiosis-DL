package com.app.test;

import java.util.List;
import java.util.Scanner;

import com.app.dao.USerDao;
import com.app.factory.UserFactory;
import com.app.model.User;
import com.app.util.HibernateUtil;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner sn=new Scanner(System.in);
USerDao dao=UserFactory.getUser();
String ch="";
do {
	System.out.println("Welcome To My Project");
	System.out.println("Press 1: for Register");
	System.out.println("Press 2: for Login");
	System.out.println("Press 3: for Display Users");
	System.out.println("Press 4: for Display User on id");
	System.out.println("-------------------------------");
	System.out.println("Enter Your Choice:");
	int choice=sn.nextInt();
	switch (choice) {
	case 1:
		int i=dao.register();
		if(i==1) {
			System.out.println("Successfully Register");
		}else {
			System.out.println("Something went wrong...!");
		}
		break;
	case 2:
		User user=dao.login();
		if(user!=null &&user.getRole().equalsIgnoreCase("admin")) {
			System.out.println("welcome to admin portal.");
		}else if(user!=null &&user.getRole().equalsIgnoreCase("user")){
			System.out.println("Welcome to User Portal..");
		}else {
			System.out.println("Invallid username and password");
		}
		break;
	case 3:
		List<User>list=dao.listOfUsers();
		list.stream().forEach(s->System.out.println(s.getId()+"\t"+s.getFisrtName()+" "+s.getLastName()+"\t"+s.getAddress()+"\t"+s.getMobile()));
	break;
	case 4:
		System.out.println("Enter your id for find user:");
		int id=sn.nextInt();
		User u1=dao.findById(id);
		System.out.println(u1);
		break;
	default:
		System.out.println("Invalid Request...!");
		break;
	}
	System.out.println("Do you want to continue...(y)");
	ch=sn.next();
}while(ch.equalsIgnoreCase("y"));
System.out.println("Thank you");
	}

}
