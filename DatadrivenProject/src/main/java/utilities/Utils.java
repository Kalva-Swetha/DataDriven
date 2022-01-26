package utilities;

import java.util.ArrayList;

public class Utils {
	static ExcelUtils xlReader;
	public static ArrayList<Object[]> getDataFromExcel()
	
	{
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		try
		{
			xlReader=new ExcelUtils(Constants.Path_TestData);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		int rowCount=xlReader.getRowCount("STUDENT_DATA");
		for(int rowNum=2;rowNum<=rowCount;rowNum++)
		{
			String firstname = xlReader.getCellData("STUDENT_DATA", "firstname", rowNum);
			System.out.println(firstname);
			String lastname = xlReader.getCellData("STUDENT_DATA", "lastname", rowNum);
			System.out.println(lastname);
			String email = xlReader.getCellData("STUDENT_DATA", "email", rowNum);
			System.out.println(email);
			String mobile = xlReader.getCellData("STUDENT_DATA", "mobile", rowNum);
			System.out.println(mobile);
			String address = xlReader.getCellData("STUDENT_DATA", "address", rowNum);
			System.out.println(address);
			String gender = xlReader.getCellData("STUDENT_DATA", "gender", rowNum);
			System.out.println(gender);
			String hobbies = xlReader.getCellData("STUDENT_DATA", "hobbies", rowNum);
			System.out.println(hobbies);
			Object[] ob= {firstname,lastname,email,mobile,address,gender,hobbies};
			myData.add(ob);
		}
		return myData;
	}

}
