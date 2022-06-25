package com.wechat.payment;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyBatisPlusGenerator {

  //作者名
  private static final String AUTHOR = "black";
  //表table的前缀，不加到生成的类名中
//  private static final String PREFIX = "None";
  //功能模块名称，生成的文件会存放到模块下
//  private static final String MODULE_NAME = "None";
  //要生成的表名
  private static final String[] TABLES = {"EarlyWarning"};
  private static final String JDBC_URL = "jdbc:sqlserver://localhost:1434;database=ChinaCiticBankCreditRiskIntelligenceSystem";
  private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
  private static final String JDBC_USERNAME = "sa";
  private static final String JDBC_PASSWORD = "Ab12345$";
  private static final String BASE_PACKAGE = "com.wechat.payment";

  public static void main(String[] args) {
    //当前项目路径
    String projectPath = System.getProperty("user.dir");
    // 代码生成器
    AutoGenerator generator = new AutoGenerator();
    //数据库配置
    configDataSource(generator);
    //全局配置
    configGlobal(generator, projectPath);
    //包相关配置
    configPackage(generator);
    //策略配置
    configStrategy(generator);
    generator.execute();
  }

  /**
   * 进行数据库相关配置
   */
  private static void configDataSource(AutoGenerator generator) {
    //数据源配置
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setUrl(JDBC_URL);
    dataSourceConfig.setDbType(DbType.SQL_SERVER);
    // dataSourceConfig.setSchemaName("public");
    dataSourceConfig.setDriverName(JDBC_DRIVER);
    dataSourceConfig.setUsername(JDBC_USERNAME);
    dataSourceConfig.setPassword(JDBC_PASSWORD);
    generator.setDataSource(dataSourceConfig);
  }

  /**
   * 进行全局配置
   */
  private static void configGlobal(AutoGenerator generator, String projectPath) {
    // 全局配置
    GlobalConfig globalConfig = new GlobalConfig();
    //生成文件输出存放路径 = 当前项目路径 + 想存放到项目中的路径
    String fileOutputPatch = projectPath.concat("/src/main/java");
    globalConfig.setOutputDir(fileOutputPatch);

    //设置作者
    globalConfig.setAuthor(AUTHOR);
    //生成完后是否打开输出目录
    globalConfig.setOpen(false);
    //是否覆盖生成过的已有文件
    globalConfig.setFileOverride(true);
    //是否开启activeRecord模式
    globalConfig.setActiveRecord(false);
    // 是否在xml中添加二级缓存配置,默认false
    globalConfig.setEnableCache(false);
    // XML文件返回对象定义ResultMap
    globalConfig.setBaseResultMap(true);
    // XML返回对象字段列表columList
    globalConfig.setBaseColumnList(true);
    //设置主键字段类型
    globalConfig.setIdType(IdType.INPUT);
    //生成的文件名字定义，%s 会自动填充表实体属性
    globalConfig.setMapperName("%sMapper");
    globalConfig.setXmlName("%sMapper");
    globalConfig.setEntityName("%s");
    globalConfig.setServiceName("%sService");
    globalConfig.setServiceImplName("%sServiceImpl");
    globalConfig.setControllerName("%sController");
    //开启 swagger2 模式,实体属性 Swagger2 注解,默认false
    globalConfig.setSwagger2(false);
    generator.setGlobalConfig(globalConfig);
  }

  /**
   * 各个包配置
   */
  private static void configPackage(AutoGenerator generator) {
    PackageConfig packageConfig = new PackageConfig();
//    packageConfig.setModuleName(MODULE_NAME);
    packageConfig.setParent(BASE_PACKAGE);//包路径
    packageConfig.setController("controller");
    packageConfig.setService("service");
    packageConfig.setServiceImpl("service.impl");
    packageConfig.setEntity("entity");
    packageConfig.setMapper("mapper");
    packageConfig.setXml("mapper");
    generator.setPackageInfo(packageConfig);
  }

  /**
   * 策略配置
   */
  private static void configStrategy(AutoGenerator generator) {
    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    //全局大写命名
    //strategy.setCapitalMode(true)
    //全局下划线命名
    //strategy.setDbColumnUnderline(true)
    //表的前缀
    //strategy.setTablePrefix(PREFIX);
    //表名下划线转为驼峰
    strategy.setNaming(NamingStrategy.underline_to_camel);
    //字段名下划线转为驼峰
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    //生成哪些表
    strategy.setInclude(TABLES);
    strategy.setControllerMappingHyphenStyle(true);
    //设置模版引擎的类型 freemarker使用ftl文件，velocity使用vm文件
//    generator.setTemplateEngine(new FreemarkerTemplateEngine());
//        generator.setTemplateEngine(new VelocityTemplateEngine());
    //是否使用lombok
    strategy.setEntityLombokModel(true);
    //设置是否restful控制器
    strategy.setRestControllerStyle(true);
    //设置布尔类型字段是否去掉is前缀
    //  strategy.setEntityBooleanColumnRemoveIsPrefix(true);
    // 自定义实体父类
    strategy.setSuperEntityClass("AbstractEntity");
    // 自定义实体，公共字段
    strategy.setSuperEntityColumns("id", "created_at", "updated_at");
    // 自定义 mapper 父类
    strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
    // 自定义 service 父类
    // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
    // 自定义 service 实现类父类
    // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
    // 自定义 controller 父类
    // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
    // 【实体】是否生成字段常量（默认 false）
    // public static final String ID = "test_id";
    // strategy.setEntityColumnConstant(true);
    // 【实体】是否为构建者模型（默认 false）
    // public User setName(String name) {this.name = name; return this;}
    // strategy.setEntityBuilderModel(true);
    generator.setStrategy(strategy);
  }
}