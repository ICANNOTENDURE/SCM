package com.dhcc.framework.common.codegen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * 
 * 根据模板生成原始代码工具类
 * @author ZangJH
 * @date 2013-9-9
 * @version V1.0
 */
public  abstract class CodeGenUtils {
	
	//模板文件根目录
	//private String TEMPLATE_BASE_DIR="src\\main\\baseResources\\codeTemplate\\";\
	//private static String TEMPLATE_BASE_DIR="src\\main\\baseResources\\codeTemplate\\";
	//action模板文件
	private static String ACTION_TEMPLATE="WebActionTemplate.dhcctemplate";
	//blh模板文件
	private static String BLH_TEMPLATE="BlhTemplate.dhcctemplate";
	//service模板文件
	private static String SERVICE_TEMPLATE="ServiceTemplate.dhcctemplate";
	//dao模板文件
	private static String DAO_TEMPLATE="DaoTemplate.dhcctemplate";
	
	private static String DTO_TEMPLATE="DtoTemplate.dhcctemplate";
	
	private static String SERVICEIMPL_TEMPLATE="ServiceImplTemplate.dhcctemplate";
	
	private static String JS_TEMPLATE="JsTemplate.dhcctemplate";
	
	private static String JSP_TEMPLATE="JspTemplate.dhcctemplate";
	
	private static String createTemplateType(String type) {
		
		String templateName="";
		switch (type.trim().toUpperCase()) {
		case "ACTION":
			templateName = ACTION_TEMPLATE;
			break;
		case "BLH":
			templateName = BLH_TEMPLATE;
			break;
		case "SERVICE":
			templateName = SERVICE_TEMPLATE;
			break;
		case "SERVICEIMPL":
			templateName = SERVICEIMPL_TEMPLATE;
			break;
		case "DAO":
			templateName = DAO_TEMPLATE;
			break;
		case "DTO":
			templateName = DTO_TEMPLATE;
			break;
		case "JS":
			templateName = JS_TEMPLATE;
			break;
		case "JSP":
			templateName = JSP_TEMPLATE;
			break;	
		default:
			break;
		}

		return templateName;

	}
	
	/**
	 * 方法名:          to1Upper
	 * 方法功能描述:    传入的字符串转成首字母大写
	 * @param:         
	 * @return:        String
	 * @Author:        ZangJH
	 * @Create Date:   2013-9-9 下午1:18:29
	 */
	private static String to1Upper(String str){
		return (char)((char)str.charAt(0)-'a'+'A')+ str.substring(1);
	}
	
	private static String to1Lower(String str){
		return (char)((char)str.charAt(0)+'a'-'A')+ str.substring(1);
	}
	
	private  static void readTemplateFile2Code(ArrayList<String> parmsList, String type){

		//String templateFileName = File.separator+"codeTemplate" +File.separator+ createTemplateType(type);
		String templateFileName ="/codeTemplate/"+ createTemplateType(type);
		//System.out.println(templateFileName);
		String dirSeparator = File.separator;
		//首字母转换大写
		String postfix =to1Upper(type);
		
		//通过packageName得到路径
		String innerDir = ((String)parmsList.get(0)).replaceAll("\\.", "\\\\");
		BufferedReader reader = null;
		BufferedWriter output = null;
		try {
			
			InputStreamReader fr = new InputStreamReader(CodeGenUtils.class.getResourceAsStream(templateFileName));
            //reader = new BufferedReader(new FileReader(templateFile));
			
            reader = new BufferedReader(fr);
            String tempString = null;
            File outFile = null;
           
			if ("serviceimpl".equals(type)) {
				// 创建输出文件
				outFile = new File("src" + dirSeparator + "main"
						+ dirSeparator + "java" + dirSeparator + innerDir
						+ dirSeparator + "service" + dirSeparator + parmsList.get(3).toLowerCase()
						+ dirSeparator + "impl"
						+ dirSeparator + parmsList.get(1) + "ServiceImpl" + ".java");
			}else if("action".equals(type)){
				// 创建输出文件
				outFile = new File("src" + dirSeparator + "main"
						+ dirSeparator + "java" + dirSeparator + innerDir
						+ dirSeparator + "web" + dirSeparator + parmsList.get(3).toLowerCase()
						+ dirSeparator + type
						+ dirSeparator + parmsList.get(1) + postfix + ".java");
			}else  if("dao".equals(type)){
				// 创建输出文件
				outFile = new File("src" + dirSeparator + "main"
						+ dirSeparator + "java" + dirSeparator + innerDir
						+ dirSeparator + type + dirSeparator + parmsList.get(3).toLowerCase()
						+ dirSeparator + parmsList.get(2) + postfix + ".java");
			}else  if("jsp".equals(type)){
				// 创建输出文件
				outFile = new File("webContent" + dirSeparator + "WEB-INF"
						+ dirSeparator + "jsp" 
						+ dirSeparator  + parmsList.get(3).toLowerCase()
						+ dirSeparator + parmsList.get(2)  + ".jsp");
				System.out.println("jsp:"+outFile);
			}else  if("js".equals(type)){
				// 创建输出文件
				outFile = new File("webContent" + dirSeparator + "js"
						+ dirSeparator + "dhcc" + dirSeparator + "pms" 
						+ dirSeparator  + parmsList.get(3).toLowerCase()
						+ dirSeparator + parmsList.get(2)  + ".js");
				System.out.println("JS:"+outFile);
			}else{
				if("dto".equals(type)){
				}else if("blh".equals(type)){
				}else if("service".equals(type)){
				}
				// 创建输出文件
				outFile = new File("src" + dirSeparator + "main"
						+ dirSeparator + "java" + dirSeparator + innerDir
						+ dirSeparator + type + dirSeparator + parmsList.get(3).toLowerCase()
						+ dirSeparator + parmsList.get(1) + postfix + ".java");
				
				
			}
                        
            
            try{
	            if(outFile.exists() && true || outFile.createNewFile()){
	            	output = new BufferedWriter(new FileWriter(outFile));
	            }
            }catch(IOException ex){
            	//通过是用捕获异常创建缺少路径（只有第一次试用模板的时候还会抛出该异常）
            	outFile.getParentFile().mkdirs(); 
            	output = new BufferedWriter(new FileWriter(outFile));
            }

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
//            	if(templateFileName.indexOf("DaoTemplate.dhcctemplate")>=0){
//            		System.out.println(tempString);
//            	}
				String replaceString = tempString.replace("{$packageName}", parmsList.get(0))
						.replace("{$businessName}", parmsList.get(1))
						.replace("{$businessName_lower}", parmsList.get(1).toLowerCase())
						.replace("{$businessName_1lower}", to1Lower(parmsList.get(1)))
						.replace("{$entityName}", parmsList.get(2))
						.replace("{$entityName_1lower}", to1Lower(parmsList.get(2)))
						.replace("{$modulesName}", parmsList.get(3));
                output.write(replaceString);
                output.newLine();
            }
            reader.close();
            output.close();
            System.out.println("ok");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e1) {
                }
            }
        }
	}
	
	/**
	 * 
	 * @param packageName:系统包前缀（包名） 如com.dhcc.ehr
	 * @param businessName:业务名 如  UserManager (首字母大字，每个单词首字母大写)
	 * @param entityName:实体类名 如   User
	 */
	public static void createCode(String packageName,String businessName,String entityName,String modulesName){
		
		ArrayList<String> parmsList = new ArrayList<String>();
		parmsList.add(packageName);
		parmsList.add(businessName);
		parmsList.add(entityName);
		parmsList.add(modulesName);
		//分别产生目前需要的文件 —— 四层
		for (String type : new String[]{"action","blh","serviceimpl","service","dao","dto","jsp","js"}){
			//System.out.println(type);
			readTemplateFile2Code(parmsList,type);
		}
	}
	
//	public static void main(String[] args){
//		
//		CodeGenUtils.createCode("com.dhcc.pms", "VenManager", "Vendor");
//	}
}
