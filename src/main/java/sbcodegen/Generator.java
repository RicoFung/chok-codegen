package sbcodegen;

import cn.org.rapid_framework.generator.GeneratorFacade;

public class Generator
{
	public static void main(String[] args) throws Exception
	{
		// 模板地址
//		String templatePath = "C:\\Dev\\projects\\jee\\chok-codegen\\src\\main\\resources\\template_v0";
//		String templatePath = "C:\\Dev\\projects\\jee\\chok-codegen\\src\\main\\resources\\template_v1";
//		String templatePath = "C:\\Dev\\projects\\jee\\chok-codegen\\src\\main\\resources\\template_v2";
//		String templatePath = "C:\\Dev\\projects\\jee\\chok-codegen\\src\\main\\resources\\template_plus_v0";
//		String templatePath = "C:\\Dev\\projects\\jee\\chok-codegen\\src\\main\\resources\\template_plus_v1";
//		String templatePath = "C:\\Dev\\projects\\jee\\chok-codegen\\src\\main\\resources\\template_plus_v2";
//		String templatePath = "C:\\Dev\\projects\\jee\\chok-codegen\\src\\main\\resources\\template_plus_v3";
		String templatePath = "C:\\Dev\\projects\\jee\\chok-codegen\\src\\main\\resources\\template_plus_v4";
//		String templatePath = "C:\\Dev\\projects\\jee\\chok-codegen\\src\\main\\resources\\template_plus_epoapi_v1";
		
		GeneratorFacade g = new GeneratorFacade();
		g.getGenerator().addTemplateRootDir(templatePath);
		
		// 通过数据库表生成文件
		g.generateByTable("BUSINESS_CHANNEL");

	}
}
//public static void main(String[] args) throws Exception
//{
//	// 模板地址
////		String templatePath = "C:\\Dev\\projects\\jee\\sbcodegen\\src\\main\\resources\\template_v1";
//	String templatePath = "C:\\Dev\\projects\\jee\\sbcodegen\\src\\main\\resources\\template_v2";
////		String templatePath = "/Users/ole/Dev/JAVA/Projects/Mine/sbcodegen/src/main/resources/template";
//	GeneratorFacade g = new GeneratorFacade();
//	g.getGenerator().addTemplateRootDir(templatePath);
//	// 删除生成器的输出目录//
//	// g.deleteOutRootDir();
//	// 通过数据库表生成文件
//	g.generateByTable("TB_DEMO");
//	
//	// 自动搜索数据库中的所有表并生成文件,template为模板的根目录
//	// g.generateByAllTable();
//	// 按table名字删除文件
//	// g.deleteByTable("table_name", "template");
//}
