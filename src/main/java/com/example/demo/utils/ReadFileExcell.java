package com.example.demo.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constants.importTraneeTitle;
import com.example.demo.form.TraineeForm;

public class ReadFileExcell {

	/**
	 * checkAttributeExcell
	 * 
	 * @param fileExcell
	 * @param index          of attribute Name
	 * @param numberOfcolumn
	 * @param Attribute      Name
	 * @param Enter          All attribute need to compare
	 * 
	 *                       Check end Read File Excell If(Not Conrect Format ) turn
	 *                       false Conrect format
	 * 
	 */
	public static Pattern DATE_PATTERN = Pattern.compile("^\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}$");
	//định dạng email
	public static String EMAIL_PATTERN = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
	public static String PHONENUMBER_PATTERN = "[^a-z_-]{10,12}$";

	
	public static List<?> readFileExcell(MultipartFile fileExcell) {
			boolean flag = true;
			List<TraineeForm> list = new ArrayList<TraineeForm>();
			List<String> listError = new ArrayList<String>();
			
			//check �?uôi của file 
		if (checkFormatFileExcell(fileExcell) != true) {
			System.out.println("File Không đúng dạng xls hoặc xlsx");
			flag = false;
		}
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(fileExcell.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			int index = 0;
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = sheet.getRow(i);

				
					
//						//Kiểm tra tên title của bảng
						if(i == 0) {
							//kiểm tra tên cell 1 EMpl Id
					      if(row.getCell(1).equals("Empl_ID")){
					    	  System.out.println(row.getCell(1).toString());
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+1);
					    	  listError.add("Wrong tile format column "+i +" row "+1+" value: "+row.getCell(1));
					    	  flag = false;
					    	  break;
					      }
					      //record Name
					      else if(row.getCell(2).equals(importTraneeTitle.Name)) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+2);
					    	  listError.add("Wrong tile format column "+i +" row "+2+" value: "+row.getCell(2));
					    	  flag = false;
					    	  break;
					      }
					      //record dob
					      else if(row.getCell(3).equals(importTraneeTitle.DOB)) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+3);
					    	  listError.add("Wrong tile format column "+i +" row "+3+" value: "+row.getCell(3));
					    	  flag = false;
					    	  break;
					      }
					      //record Gender
					      else if(row.getCell(4).equals(importTraneeTitle.Gender)) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+4);
					    	  listError.add("Wrong tile format column "+i +" row "+4+" value: "+row.getCell(4));
					    	  flag = false;
					    	  break;
					      }
					      //record University
					      else if(row.getCell(5).equals(importTraneeTitle.University)) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+5);
					    	  listError.add("Wrong tile format column "+i +" row "+5+" value: "+row.getCell(5));
					    	  flag = false;
					    	  break;
					      }
					      //record Faculty
					      else if(row.getCell(6).equals(importTraneeTitle.Faculty)) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+6);
					    	  listError.add("Wrong tile format column "+i +" row "+6 +" value: "+row.getCell(6));
					    	  flag = false;
					    	  break;
					      }
					      //record phone
					      else if(row.getCell(7).equals(importTraneeTitle.Phone)) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+7);
					    	  listError.add("Wrong tile format column "+i +" row "+7 +" value: "+row.getCell(7));
					    	  flag = false;
					    	  break;
					      }
					      //record Email
					      else if(row.getCell(8).equals(importTraneeTitle.Email)) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+8);
					    	  listError.add("Wrong tile format column "+i +" row "+8 +" value: "+row.getCell(8));
					    	  flag = false;
					    	  break;
					      }
					      //record status Status
					      else if(row.getCell(9).equals(importTraneeTitle.Status)) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+9);
					    	  listError.add("Wrong tile format column "+i +" row "+9+" value: "+row.getCell(9));
					    	  flag = false;
					    	  break;
					      }
						}
					      
					
//						//kiểm tra dữ liệu đầu vào
						if(i > 0) {
							//kiểm tra tên cell 1 EMpl Id
					      if(row.getCell(1).toString().equalsIgnoreCase("Empl ID")){
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+1);
					    	  System.out.println(row.getCell(2));
					    	  listError.add("Wrong input column "+i +" row "+1+" value: "+row.getCell(1));


					    	  flag = false;
					    	  break;
					      }
					      //record Name
					      else if(row.getCell(2) == null) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+2);
					    	  System.out.println(row.getCell(2));
					    	  listError.add("Wrong input column "+i +" row "+2+" value: "+row.getCell(2));

					    	  flag = false;
					    	  break;
					      }
					      //record dob
					      else if(DATE_PATTERN.matcher(row.getCell(3).toString()).matches() == false) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+3);
					    	  System.out.println(row.getCell(3));
					    	  listError.add("Wrong input column "+i +" row "+3+" value: "+row.getCell(3));

					    	  flag = false;
					    	  break;
					      }
					      //record Gender
					      else if(((row.getCell(4).toString()).equalsIgnoreCase("Male"))== false 
					    		  && ((row.getCell(4).toString()).equalsIgnoreCase("Female"))  == false ) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+4);
					    	  System.out.println(row.getCell(4));
					    	  listError.add("Wrong input column "+i +" row "+4+" value: "+row.getCell(4));

					    	  flag = false;
					    	  break;
					      }
					      //record University
					      else if(row.getCell(5) == null) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+5);
					    	  System.out.println(row.getCell(5));
					    	  listError.add("Wrong input column "+i +" row "+5+" value: "+row.getCell(5));

					    	  flag = false;
					    	  break;
					      }
					      //record Faculty
					      else if(row.getCell(6) == null) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+6);
					    	  System.out.println(row.getCell(6));
					    	  listError.add("Wrong input column "+i +" row "+6+" value: "+row.getCell(6));

					    	  flag = false;
					    	  break;
					      }
					      //record phone
					      else if(row.getCell(7).toString().matches(PHONENUMBER_PATTERN) == false) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+7);
					    	  System.out.println(row.getCell(7));
					    	  listError.add("Wrong input column "+i +" row "+7+" value: "+row.getCell(7));

					    	  flag = false;
					    	  break;
					      }
					      //record Email
					      else if(row.getCell(8).toString().matches(EMAIL_PATTERN) == false) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+8);
					    	  System.out.println(row.getCell(8));
					    	  listError.add("Wrong input column "+i +" row "+8+" value: "+row.getCell(8));

					    	  flag = false;
					    	  break;
					      }
					      //record status Status
					      else if(row.getCell(9).equals("Enrolled")) {
					    	  System.err.println("Sai hàng số: " +i +" cột số: "+9);
					    	  System.out.println(row.getCell(9));
					    	  listError.add("Wrong input column "+i +" row "+9 +" value: "+row.getCell(9));

					    	  flag = false;
					    	  break;
					      }
						}
					


				
			}

			if(flag == true) {
				for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
					XSSFRow row = sheet.getRow(i);
//					for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
//						System.out.println(row.getCell(j));
//					}
					
					System.out.print(row.getCell(1) +"| ");
					System.out.print(row.getCell(2) +"| ");
					System.out.print(row.getCell(3) +"| ");
					System.out.print(row.getCell(4) +"| ");
					System.out.print(row.getCell(5) +"| ");
					System.out.print(row.getCell(6) +"| ");
					System.out.print(row.getCell(7) +"| ");
					System.out.print(row.getCell(8) +"| ");
					System.out.print(row.getCell(9) +"| ");
					System.out.println();
					
					if(i>0) {
					TraineeForm traineeForm = new TraineeForm(
							row.getCell(1).toString()
							, row.getCell(2).toString()
							, row.getCell(3).toString() 
							, row.getCell(4).toString()
							, row.getCell(5).toString()
							, row.getCell(6).toString()
							, row.getCell(7).toString()
							, row.getCell(8).toString()
							, row.getCell(9).toString());
					
					list.add(traineeForm);
					}
				}
			}
			return flag == true ? list : listError;
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return listError;
		}

	}

	

	// checkformat file
	public static Boolean checkFormatFileExcell(MultipartFile fileExcell) {
		String fileName = StringUtils.cleanPath(fileExcell.getOriginalFilename());
		boolean check1 = fileName.endsWith(".xlsx");
		boolean check2 = fileName.endsWith(".xls");

		
		if (check1 == true || check2 == true) {
			System.out.println("�?uôi của file excell: �?ạt yêu cầu");
			return true;
		} else {
			System.out.println("�?uôi của file excell: Fail");

			return false;
		}

	}
	
	
	public void checkValidateForm() {
		
	}
}
