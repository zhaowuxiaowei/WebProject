package com.ning.javaServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;
import com.ning.javaUtil.JSONBean;
import com.ning.javaUtil.JsonName;
import com.ning.javaUtil.APNapple;
import com.ning.javaUtil.DopostHandle;
import java.util.Date;



/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HelloServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

		   Statement stmt = null;
			Connection con = null;
			 
	        String driver = "com.mysql.jdbc.Driver";
	        String username = "0knwkw3nxk";
	        String password = "xlm3i1wjlhmjmww0ly2w1kx1yky40z0liy2k24hx";
	        //System.getenv("MYSQL_HOST_S"); 为从库，只读
	        String dbUrl = String.format("jdbc:mysql://%s:%s/%s","w.rdc.sae.sina.com.cn","3307","app_appone");
	        try {
	            Class.forName(driver).newInstance();
	            con = DriverManager.getConnection(dbUrl, username, password);
	            // 执行 SQL 查询
	            stmt = con.createStatement();
	            String sql;   	            
	            
	            Date date = new Date();
	            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
	       
	            System.out.println("当前时间为: " + ft.format(date));

		        sql = String.format("SELECT * FROM personit");
		        ResultSet rs = stmt.executeQuery(sql);
	          // 展开结果集数据库
	          while(rs.next()){
	              // 通过字段检索
	          	String tokenid  = rs.getString("tokenid");
	            if(tokenid != null && tokenid.length() != 0) { 
	            	
	            	APNapple apnapple=new APNapple();
	            	File file=new File(this.getClass().getResource("/../../").getPath());

	                 String certificatePath=file.getPath()+"/path/comspdbmposnewbank.p12";
	                 System.out.println(certificatePath);
	    	        out.println(certificatePath);
	            	apnapple.setDemandid(tokenid,response,"请准时填写日报");
	            	
	            }
	            else {
	            	
	            }

	          
	          }
	         }catch (Exception e) {
  	            System.err.println("Caught " + e);
  	        }
	        
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String transID= request.getParameter("transID");
        transID=new String(transID.getBytes("ISO-8859-1"),"UTF-8");

  

      

        Statement stmt = null;
		Connection con = null;
		 
        String driver = "com.mysql.jdbc.Driver";
        String username = "0knwkw3nxk";
        String password = "xlm3i1wjlhmjmww0ly2w1kx1yky40z0liy2k24hx";
        //System.getenv("MYSQL_HOST_S"); 为从库，只读
        String dbUrl = String.format("jdbc:mysql://%s:%s/%s","w.rdc.sae.sina.com.cn","3307","app_appone");
        
        
        String requestsuccess="requestsuccess";
        String requestfaile="requestfaile";


        String standardtransID="queryid";
        String standardstoreID="storeid";
        String standardloginID="login";
        String standardupdateID="update";
        String standardpersonidID="personid";
        String standarddeleteidID="delete";
        String standardnewstoreID="newstoreid";

        String standardAPNPushID="apnpushid";
        String standardversionID="versionid";
        String standardpuandversionid="puandversionid";

        
        
        if (transID.equals(standardtransID)) {
            String developName= request.getParameter("developName");
            developName=new String(developName.getBytes("ISO-8859-1"),"UTF-8");
	        try {
	            Class.forName(driver).newInstance();
	            con = DriverManager.getConnection(dbUrl, username, password);
	            // 执行 SQL 查询
	            stmt = con.createStatement();
	            String sql;
	            if(developName.equals("all")){
			        sql = "SELECT * FROM dailystatistics WHERE 1 ORDER BY developName";
	            }
	            else {
			        sql = String.format("SELECT * FROM dailystatistics WHERE developName = '%s'",developName);
	            }
		        
	            ResultSet rs = stmt.executeQuery(sql);
	            rs.last();
	            int rowCount=rs.getRow();
	            rs.beforeFirst();
	            
	            JSONBean[] myList = new JSONBean[rowCount];
	            int index=0;
	            // 展开结果集数据库
	            while(rs.next()){
	                // 通过字段检索
	                JSONBean jb=new JSONBean();
	                jb.setDemandid(rs.getString("demandid"));
	                jb.setVersion(rs.getString("version"));

	                
	                jb.setSubmittime(rs.getString("submittime"));
	                jb.setDemanddemand(rs.getString("demanddemand"));
	                jb.setTechnologyhead(rs.getString("technologyhead"));
	                jb.setDepartmentshead(rs.getString("departmentshead"));
	                jb.setOpenness(rs.getString("openness"));
	                jb.setGrayscaleverified(rs.getString("grayscaleverified"));
	                jb.setCurrentstage(rs.getString("currentstage"));
	                jb.setSpeedofprogress(rs.getString("speedofprogress"));
	                jb.setDevelopers(rs.getString("developers"));
	               
	                jb.setTester(rs.getString("tester"));
	                jb.setBusinessacceptance(rs.getString("businessacceptance"));
	                jb.setEstimatedworkload(rs.getString("estimatedworkload"));
	               
	                jb.setRequirementanalysis_a(rs.getString("requirementanalysis_a"));
	                jb.setRequirementanalysis_b(rs.getString("requirementanalysis_b"));
	                jb.setRequirementanalysis_c(rs.getString("requirementanalysis_c"));
	                jb.setRequirementanalysis_d(rs.getString("requirementanalysis_d"));
	                jb.setRequirementanalysis_e(rs.getString("requirementanalysis_e"));
	                jb.setRequirementanalysis_f(rs.getString("requirementanalysis_f"));
	                jb.setInterfacedesign_a(rs.getString("interfacedesign_a"));
	                jb.setInterfacedesign_b(rs.getString("interfacedesign_b"));
	                jb.setInterfacedesign_c(rs.getString("interfacedesign_c"));
	                jb.setInterfacedesign_d(rs.getString("interfacedesign_d"));
	                jb.setCodingdevelopment_a(rs.getString("codingdevelopment_a"));
	                jb.setCodingdevelopment_b(rs.getString("codingdevelopment_b"));
	                jb.setCodingdevelopment_c(rs.getString("codingdevelopment_c"));
	                jb.setInterfacedebugging_a(rs.getString("interfacedebugging_a"));
	                jb.setInterfacedebugging_b(rs.getString("interfacedebugging_b"));
	                jb.setInterfacedebugging_c(rs.getString("interfacedebugging_c"));
	                jb.setIntegrationtesting_a(rs.getString("integrationtesting_a"));
	                jb.setIntegrationtesting_b(rs.getString("integrationtesting_b"));
	                jb.setIntegrationtesting_c(rs.getString("integrationtesting_c"));
	                jb.setIntegrationtesting_d(rs.getString("integrationtesting_d"));
	                jb.setIntegrationtesting_e(rs.getString("integrationtesting_e"));
	                jb.setRegressiontesting_a(rs.getString("regressiontesting_a"));
	                jb.setRegressiontesting_b(rs.getString("regressiontesting_b"));
	                jb.setRegressiontesting_c(rs.getString("regressiontesting_c"));
	                jb.setRegressiontesting_d(rs.getString("regressiontesting_d"));
	                jb.setBusinessacceptance_a(rs.getString("businessacceptance_a"));
	                jb.setBusinessacceptance_b(rs.getString("businessacceptance_b"));
	                jb.setBusinessacceptance_c(rs.getString("businessacceptance_c"));
	                jb.setBusinessacceptance_d(rs.getString("businessacceptance_d"));
	                
	    
	                jb.setRiskpointsorremarks(rs.getString("riskpointsorremarks"));
	                jb.setRisklevelandImpact(rs.getString("risklevelandImpact"));
	                jb.setRiskliableperson(rs.getString("riskliableperson"));
	                jb.setAddingfunctionpoints(rs.getString("addingfunctionpoints"));
	                jb.setInvolvingsystems(rs.getString("involvingsystems"));
	                jb.setGrayproduction(rs.getString("grayproduction"));
	                jb.setRequirementstartuptime(rs.getString("requirementstartuptime"));
	                jb.setDemandendtime(rs.getString("demandendtime"));
	                jb.setRemarks(rs.getString("remarks"));
	                jb.setDevelopName(rs.getString("DevelopName"));

	                
	                
	                
	                
	              

	                

	                myList[index]=jb;
	                index++;
	                // 输出数据
	               
	            }
	            String jsonString =new Gson().toJson(myList);
		        out.println(jsonString);
		        
	        } catch (Exception e) {
	            System.err.println("Caught " + e);
		        out.println(requestfaile);
	        }
	      
        	 
        }
        else if (transID.equals(standardstoreID)) {
            String developName= request.getParameter("developName");
            developName=new String(developName.getBytes("ISO-8859-1"),"UTF-8");
    	        
        	String demandid= request.getParameter("demandid");
        	demandid=new String(demandid.getBytes("ISO-8859-1"),"UTF-8");

        	String version= request.getParameter("version");
        	version=new String(version.getBytes("ISO-8859-1"),"UTF-8");

        	String submittime= request.getParameter("submittime");
        	submittime=new String(submittime.getBytes("ISO-8859-1"),"UTF-8");

        	String demanddemand= request.getParameter("demanddemand");
        	demanddemand=new String(demanddemand.getBytes("ISO-8859-1"),"UTF-8");

        	String technologyhead= request.getParameter("technologyhead");
        	technologyhead=new String(technologyhead.getBytes("ISO-8859-1"),"UTF-8");


        	String openness= request.getParameter("openness");
        	openness=new String(openness.getBytes("ISO-8859-1"),"UTF-8");


        	String grayscaleverified= request.getParameter("grayscaleverified");
        	grayscaleverified=new String(grayscaleverified.getBytes("ISO-8859-1"),"UTF-8");


        	String currentstage= request.getParameter("currentstage");
        	currentstage=new String(currentstage.getBytes("ISO-8859-1"),"UTF-8");


        	String speedofprogress= request.getParameter("speedofprogress");
        	speedofprogress=new String(speedofprogress.getBytes("ISO-8859-1"),"UTF-8");


        	String tester= request.getParameter("tester");
        	tester=new String(tester.getBytes("ISO-8859-1"),"UTF-8");


        	String businessacceptance= request.getParameter("businessacceptance");
        	businessacceptance=new String(businessacceptance.getBytes("ISO-8859-1"),"UTF-8");


        	String riskpointsorremarks= request.getParameter("riskpointsorremarks");
        	riskpointsorremarks=new String(riskpointsorremarks.getBytes("ISO-8859-1"),"UTF-8");


        	String risklevelandImpact= request.getParameter("risklevelandImpact");
        	risklevelandImpact=new String(risklevelandImpact.getBytes("ISO-8859-1"),"UTF-8");


        	String riskliableperson= request.getParameter("riskliableperson");
        	riskliableperson=new String(riskliableperson.getBytes("ISO-8859-1"),"UTF-8");


        	String addingfunctionpoints= request.getParameter("addingfunctionpoints");
        	addingfunctionpoints=new String(addingfunctionpoints.getBytes("ISO-8859-1"),"UTF-8");


        	String involvingsystems= request.getParameter("involvingsystems");
        	involvingsystems=new String(involvingsystems.getBytes("ISO-8859-1"),"UTF-8");


        	String grayproduction= request.getParameter("grayproduction");
        	grayproduction=new String(grayproduction.getBytes("ISO-8859-1"),"UTF-8");


        	String requirementstartuptime= request.getParameter("requirementstartuptime");
        	requirementstartuptime=new String(requirementstartuptime.getBytes("ISO-8859-1"),"UTF-8");


        	String demandendtime= request.getParameter("demandendtime");
        	demandendtime=new String(demandendtime.getBytes("ISO-8859-1"),"UTF-8");



        	String departmentshead= request.getParameter("departmentshead");
        	departmentshead=new String(departmentshead.getBytes("ISO-8859-1"),"UTF-8");


        	String developers= request.getParameter("developers");
        	developers=new String(developers.getBytes("ISO-8859-1"),"UTF-8");


        	String estimatedworkload= request.getParameter("estimatedworkload");
        	estimatedworkload=new String(estimatedworkload.getBytes("ISO-8859-1"),"UTF-8");


        	String remarks= request.getParameter("remarks");
        	remarks=new String(remarks.getBytes("ISO-8859-1"),"UTF-8");

        	String   requirementanalysis_a=request.getParameter("requirementanalysis_a");
        	requirementanalysis_a=new String(requirementanalysis_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   requirementanalysis_b=request.getParameter("requirementanalysis_b");
        	requirementanalysis_b=new String(requirementanalysis_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   requirementanalysis_c=request.getParameter("requirementanalysis_c");
        	requirementanalysis_c=new String(requirementanalysis_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   requirementanalysis_d=request.getParameter("requirementanalysis_d");
        	requirementanalysis_d=new String(requirementanalysis_d.getBytes("ISO-8859-1"),"UTF-8");


        	String   requirementanalysis_e=request.getParameter("requirementanalysis_e");
        	requirementanalysis_e=new String(requirementanalysis_e.getBytes("ISO-8859-1"),"UTF-8");


        	String   requirementanalysis_f=request.getParameter("requirementanalysis_f");
        	requirementanalysis_f=new String(requirementanalysis_f.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedesign_a=request.getParameter("interfacedesign_a");
        	interfacedesign_a=new String(interfacedesign_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedesign_b=request.getParameter("interfacedesign_b");
        	interfacedesign_b=new String(interfacedesign_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedesign_c=request.getParameter("interfacedesign_c");
        	interfacedesign_c=new String(interfacedesign_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedesign_d=request.getParameter("interfacedesign_d");
        	interfacedesign_d=new String(interfacedesign_d.getBytes("ISO-8859-1"),"UTF-8");


        	String   codingdevelopment_a=request.getParameter("codingdevelopment_a");
        	codingdevelopment_a=new String(codingdevelopment_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   codingdevelopment_b=request.getParameter("codingdevelopment_b");
        	codingdevelopment_b=new String(codingdevelopment_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   codingdevelopment_c=request.getParameter("codingdevelopment_c");
        	codingdevelopment_c=new String(codingdevelopment_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedebugging_a=request.getParameter("interfacedebugging_a");
        	interfacedebugging_a=new String(interfacedebugging_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedebugging_b=request.getParameter("interfacedebugging_b");
        	interfacedebugging_b=new String(interfacedebugging_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedebugging_c=request.getParameter("interfacedebugging_c");
        	interfacedebugging_c=new String(interfacedebugging_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   integrationtesting_a=request.getParameter("integrationtesting_a");
        	integrationtesting_a=new String(integrationtesting_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   integrationtesting_b=request.getParameter("integrationtesting_b");
        	integrationtesting_b=new String(integrationtesting_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   integrationtesting_c=request.getParameter("integrationtesting_c");
        	integrationtesting_c=new String(integrationtesting_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   integrationtesting_d=request.getParameter("integrationtesting_d");
        	integrationtesting_d=new String(integrationtesting_d.getBytes("ISO-8859-1"),"UTF-8");


        	String   integrationtesting_e=request.getParameter("integrationtesting_e");
        	integrationtesting_e=new String(integrationtesting_e.getBytes("ISO-8859-1"),"UTF-8");


        	String   regressiontesting_a=request.getParameter("regressiontesting_a");
        	regressiontesting_a=new String(regressiontesting_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   regressiontesting_b=request.getParameter("regressiontesting_b");
        	regressiontesting_b=new String(regressiontesting_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   regressiontesting_c=request.getParameter("regressiontesting_c");
        	regressiontesting_c=new String(regressiontesting_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   regressiontesting_d=request.getParameter("regressiontesting_d");
        	regressiontesting_d=new String(regressiontesting_d.getBytes("ISO-8859-1"),"UTF-8");


        	String   businessacceptance_a=request.getParameter("businessacceptance_a");
        	businessacceptance_a=new String(businessacceptance_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   businessacceptance_b=request.getParameter("businessacceptance_b");
        	businessacceptance_b=new String(businessacceptance_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   businessacceptance_c=request.getParameter("businessacceptance_c");
        	businessacceptance_c=new String(businessacceptance_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   businessacceptance_d=request.getParameter("businessacceptance_d");
        	businessacceptance_d=new String(businessacceptance_d.getBytes("ISO-8859-1"),"UTF-8");
  
    	        
    	        try {
    	        	
    	            Class.forName(driver).newInstance();
    	            con = DriverManager.getConnection(dbUrl, username, password);
    	            // 执行 SQL 查询
    	            stmt = con.createStatement();
    	            String sql;
    		        sql = String.format("SELECT * FROM dailystatistics WHERE demandid  = '%s'",demandid);
    		        
    	            ResultSet rs = stmt.executeQuery(sql);
    	            rs.last();
    	            int rowCount=rs.getRow();
    	            rs.beforeFirst();
    	            
    	            if(rowCount>0) {
    	            	String sqldevelopName="";
    		            while(rs.next()){
    		            	 sqldevelopName  = rs.getString("developName");
    		    
    		            }
    		            
    	            	if(sqldevelopName.equals(developName)) {
    	            	  String mysqlInset = String.format("UPDATE dailystatistics SET version= '%s',submittime= '%s',demanddemand= '%s',technologyhead= '%s',departmentshead= '%s',openness= '%s',grayscaleverified= '%s',currentstage= '%s',speedofprogress= '%s',developers= '%s',tester= '%s',businessacceptance= '%s', estimatedworkload= '%s',riskpointsorremarks= '%s',risklevelandImpact= '%s',riskliableperson= '%s',addingfunctionpoints= '%s',involvingsystems= '%s',grayproduction= '%s', requirementstartuptime= '%s',demandendtime= '%s',remarks= '%s',developName= '%s' ,requirementanalysis_a = '%s', requirementanalysis_b = '%s', requirementanalysis_c = '%s', requirementanalysis_d = '%s', requirementanalysis_e = '%s', requirementanalysis_f = '%s', interfacedesign_a = '%s', interfacedesign_b = '%s', interfacedesign_c = '%s', interfacedesign_d = '%s', codingdevelopment_a = '%s', codingdevelopment_b = '%s', codingdevelopment_c = '%s', interfacedebugging_a = '%s', interfacedebugging_b = '%s', interfacedebugging_c = '%s', integrationtesting_a = '%s', integrationtesting_b = '%s', integrationtesting_c = '%s', integrationtesting_d = '%s', integrationtesting_e = '%s', regressiontesting_a = '%s', regressiontesting_b = '%s', regressiontesting_c = '%s', regressiontesting_d = '%s', businessacceptance_a = '%s', businessacceptance_b = '%s', businessacceptance_c = '%s', businessacceptance_d = '%s' WHERE demandid = '%s'",version,submittime,demanddemand,technologyhead,departmentshead,openness,grayscaleverified,currentstage,speedofprogress,developers,tester,businessacceptance, estimatedworkload,riskpointsorremarks,risklevelandImpact,riskliableperson,addingfunctionpoints,involvingsystems,grayproduction, requirementstartuptime,demandendtime,remarks,developName,requirementanalysis_a,requirementanalysis_b,requirementanalysis_c,requirementanalysis_d,requirementanalysis_e,requirementanalysis_f,interfacedesign_a,interfacedesign_b,interfacedesign_c,interfacedesign_d,codingdevelopment_a,codingdevelopment_b,codingdevelopment_c,interfacedebugging_a,interfacedebugging_b,interfacedebugging_c,integrationtesting_a,integrationtesting_b,integrationtesting_c,integrationtesting_d,integrationtesting_e,regressiontesting_a,regressiontesting_b,regressiontesting_c,regressiontesting_d,businessacceptance_a,businessacceptance_b,businessacceptance_c,businessacceptance_d,demandid);
    	            	
    	            	  int  sisuccess = stmt.executeUpdate(mysqlInset);
    	            	  if(sisuccess>0) {
    	    		        out.println(requestsuccess);
    	    	            System.err.println("SQL修改：修改成功" + developName);
    	            	  }
    	            	  else {
      	            		String errString="SQL修改：修改失败" + developName+sqldevelopName;
    	    		        out.println(requestfaile+errString);
    	    	            System.err.println("SQL修改：修改失败" + developName);

    	            	  }
    	            	}
    	            	else {
    	            		String errString="SQL修改：修改名称不相同，需求单号相同" + developName+sqldevelopName;
    	    	            System.err.println(errString);
    	    		        out.println(requestfaile+errString);
    	            	}
    	            }
    	            else {
    	            	String mysqlInset = String.format("INSERT INTO dailystatistics (demandid, version, submittime, demanddemand, technologyhead, departmentshead, openness, grayscaleverified, currentstage, speedofprogress, developers, tester,businessacceptance, estimatedworkload, riskpointsorremarks, risklevelandImpact, riskliableperson,addingfunctionpoints, involvingsystems, grayproduction, requirementstartuptime, demandendtime, remarks, developName,requirementanalysis_a,requirementanalysis_b,requirementanalysis_c,requirementanalysis_d,requirementanalysis_e,requirementanalysis_f,interfacedesign_a,interfacedesign_b,interfacedesign_c,interfacedesign_d,codingdevelopment_a,codingdevelopment_b,codingdevelopment_c,interfacedebugging_a,interfacedebugging_b,interfacedebugging_c,integrationtesting_a,integrationtesting_b,integrationtesting_c,integrationtesting_d,integrationtesting_e,regressiontesting_a,regressiontesting_b,regressiontesting_c,regressiontesting_d,businessacceptance_a,businessacceptance_b,businessacceptance_c,businessacceptance_d) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",demandid,version,submittime,demanddemand,technologyhead,departmentshead,openness,grayscaleverified,currentstage,speedofprogress,developers,tester,businessacceptance,estimatedworkload,riskpointsorremarks,risklevelandImpact,riskliableperson,addingfunctionpoints,involvingsystems,grayproduction, requirementstartuptime,demandendtime,remarks,developName,requirementanalysis_a,requirementanalysis_b,requirementanalysis_c,requirementanalysis_d,requirementanalysis_e,requirementanalysis_f,interfacedesign_a,interfacedesign_b,interfacedesign_c,interfacedesign_d,codingdevelopment_a,codingdevelopment_b,codingdevelopment_c,interfacedebugging_a,interfacedebugging_b,interfacedebugging_c,integrationtesting_a,integrationtesting_b,integrationtesting_c,integrationtesting_d,integrationtesting_e,regressiontesting_a,regressiontesting_b,regressiontesting_c,regressiontesting_d,businessacceptance_a,businessacceptance_b,businessacceptance_c,businessacceptance_d);    	            	    	            	   	            	
    	     
    	            	int  sisuccess = stmt.executeUpdate(mysqlInset);
    	            	if(sisuccess>0) {
    	    		        out.println(requestsuccess);
    	            	}
    	            	else {
    	    		        out.println(requestfaile);
    	            	}
    	            }
   	        	 Date date = new Date();
  	             SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");  
  	             String timeDate=ft.format(date);
  	             String insertsql = String.format("UPDATE  personit SET  timenum =  '%s' WHERE  name =  '%s'",timeDate,developName);
	            	 int  insertsisuccess = stmt.executeUpdate(insertsql);
    		        
    	        } catch (Exception e) {
    	            System.err.println("Caught " + e);
    	        }
        	
        }
        /* 登录*/
        else if (transID.equals(standardloginID)) {
	        try {
	          	String name= request.getParameter("name");
	           	name=new String(name.getBytes("ISO-8859-1"),"UTF-8");
	        	String apppassword= request.getParameter("password");
	        	apppassword=new String(apppassword.getBytes("ISO-8859-1"),"UTF-8");
	            Class.forName(driver).newInstance();
	            con = DriverManager.getConnection(dbUrl, username, password);
	            // 执行 SQL 查询
	            stmt = con.createStatement();
	            String sql;
		        sql = String.format("SELECT * FROM personit WHERE name = '%s'",name);
	            ResultSet rs = stmt.executeQuery(sql);
	            String servernamestring="";
	            while(rs.next()){
	            	servernamestring=rs.getString("password");
	            }
	            if(servernamestring.equals(apppassword)) {
			        out.println(requestsuccess);
	            }
	            else {
			        out.println(requestfaile);
	            }
	        } catch (Exception e) {
	            System.err.println("Caught " + e);
		        out.println(requestfaile);
	        }
	
        }
        /* 修改密码*/
        else if (transID.equals(standardupdateID)) {
	        try {
	          	String name= request.getParameter("name");
	           	name=new String(name.getBytes("ISO-8859-1"),"UTF-8");
	           	String appnewpassword= request.getParameter("newpassword");
	           	appnewpassword=new String(appnewpassword.getBytes("ISO-8859-1"),"UTF-8");

	        	String apppassword= request.getParameter("password");
	        	apppassword=new String(apppassword.getBytes("ISO-8859-1"),"UTF-8");
	            Class.forName(driver).newInstance();
	            con = DriverManager.getConnection(dbUrl, username, password);
	            // 执行 SQL 查询
	            stmt = con.createStatement();
	            String sql;
		        sql = String.format("SELECT * FROM personit WHERE name = '%s'",name);
	            ResultSet rs = stmt.executeQuery(sql);
	            String servernamestring="";
	            while(rs.next()){
	            	servernamestring=rs.getString("password");
	            }
	            if(servernamestring.equals(apppassword)) {
	            	String updatesql = String.format("UPDATE personit SET password= '%s' WHERE name = '%s'",appnewpassword,name);
	            	int  sisuccess = stmt.executeUpdate(updatesql);
	            	if(sisuccess>0) {
	    		        out.println(requestsuccess);
	            	}
	            	else {
	    		        out.println(requestfaile);
	            	}

	            }
	            else {
			        out.println(requestfaile);
	            }
		        
	        } catch (Exception e) {
	            System.err.println("Caught " + e);
		        out.println(requestfaile);
	        }
	
        }
        else if (transID.equals(standardpersonidID)) {
	        try {
	            Class.forName(driver).newInstance();
	            con = DriverManager.getConnection(dbUrl, username, password);
	            // 执行 SQL 查询
	            stmt = con.createStatement();
	            String sql;
		        sql = "SELECT * FROM personit WHERE 1";
	            ResultSet rs = stmt.executeQuery(sql);
	            rs.last();
	            int rowCount=rs.getRow();
	            rs.beforeFirst();
	            
	            JsonName[] myList = new JsonName[rowCount];
	            int index=0;
	            // 展开结果集数据库
	            while(rs.next()){
	                // 通过字段检索
	                JsonName jb=new JsonName();
	                jb.setName(rs.getString("name"));
	                jb.setHanname(rs.getString("hanname"));
	                jb.setTokenid(rs.getString("tokenid"));
	                jb.setTimenum(rs.getString("timenum"));
	                jb.setUsertype(rs.getString("usertype"));

	                myList[index]=jb;
	                index++;
	                // 输出数据
	               
	            }
	            String jsonString =new Gson().toJson(myList);
		        out.println(jsonString);
		        
	        } catch (Exception e) {
	            System.err.println("Caught " + e);
		        out.println(requestfaile);
	        }
        	
        }
        else if (transID.equals(standarddeleteidID)) {
        	String demandid= request.getParameter("demandid");
        	demandid=new String(demandid.getBytes("ISO-8859-1"),"UTF-8");
    	        
    	        try {
    	            Class.forName(driver).newInstance();
    	            con = DriverManager.getConnection(dbUrl, username, password);
    	            // 执行 SQL 查询
    	            stmt = con.createStatement();
    	            String sql;
	
    		        sql = String.format("DELETE FROM dailystatistics  WHERE  demandid  = '%s'",demandid);
	            	  int  sisuccess = stmt.executeUpdate(sql);
	            	  if(sisuccess>0) {
  	    		        out.println(requestsuccess);
  	    	            System.err.println("SQL修改：修改成功" + demandid);
  	            	  }
  	            	  else {
    	            		String errString="SQL修改：修改失败" + demandid+demandid;
  	    		        out.println(requestfaile+errString);
  	    	            System.err.println("SQL修改：修改失败" + demandid);

  	            	  }
    
    	            
    		        
    	        } catch (Exception e) {
    	            System.err.println("Caught " + e);
    	        }
        	
        }
        else if (transID.equals(standardnewstoreID)) {   
        	

            String developName= request.getParameter("developName");
            developName=new String(developName.getBytes("ISO-8859-1"),"UTF-8");
    	        
        	String demandid= request.getParameter("demandid");
        	demandid=new String(demandid.getBytes("ISO-8859-1"),"UTF-8");

        	String version= request.getParameter("version");
        	version=new String(version.getBytes("ISO-8859-1"),"UTF-8");

        	String submittime= request.getParameter("submittime");
        	submittime=new String(submittime.getBytes("ISO-8859-1"),"UTF-8");

        	String demanddemand= request.getParameter("demanddemand");
        	demanddemand=new String(demanddemand.getBytes("ISO-8859-1"),"UTF-8");

        	String technologyhead= request.getParameter("technologyhead");
        	technologyhead=new String(technologyhead.getBytes("ISO-8859-1"),"UTF-8");


        	String openness= request.getParameter("openness");
        	openness=new String(openness.getBytes("ISO-8859-1"),"UTF-8");


        	String grayscaleverified= request.getParameter("grayscaleverified");
        	grayscaleverified=new String(grayscaleverified.getBytes("ISO-8859-1"),"UTF-8");


        	String currentstage= request.getParameter("currentstage");
        	currentstage=new String(currentstage.getBytes("ISO-8859-1"),"UTF-8");


        	String speedofprogress= request.getParameter("speedofprogress");
        	speedofprogress=new String(speedofprogress.getBytes("ISO-8859-1"),"UTF-8");


        	String tester= request.getParameter("tester");
        	tester=new String(tester.getBytes("ISO-8859-1"),"UTF-8");


        	String businessacceptance= request.getParameter("businessacceptance");
        	businessacceptance=new String(businessacceptance.getBytes("ISO-8859-1"),"UTF-8");


        	String riskpointsorremarks= request.getParameter("riskpointsorremarks");
        	riskpointsorremarks=new String(riskpointsorremarks.getBytes("ISO-8859-1"),"UTF-8");


        	String risklevelandImpact= request.getParameter("risklevelandImpact");
        	risklevelandImpact=new String(risklevelandImpact.getBytes("ISO-8859-1"),"UTF-8");


        	String riskliableperson= request.getParameter("riskliableperson");
        	riskliableperson=new String(riskliableperson.getBytes("ISO-8859-1"),"UTF-8");


        	String addingfunctionpoints= request.getParameter("addingfunctionpoints");
        	addingfunctionpoints=new String(addingfunctionpoints.getBytes("ISO-8859-1"),"UTF-8");


        	String involvingsystems= request.getParameter("involvingsystems");
        	involvingsystems=new String(involvingsystems.getBytes("ISO-8859-1"),"UTF-8");


        	String grayproduction= request.getParameter("grayproduction");
        	grayproduction=new String(grayproduction.getBytes("ISO-8859-1"),"UTF-8");


        	String requirementstartuptime= request.getParameter("requirementstartuptime");
        	requirementstartuptime=new String(requirementstartuptime.getBytes("ISO-8859-1"),"UTF-8");


        	String demandendtime= request.getParameter("demandendtime");
        	demandendtime=new String(demandendtime.getBytes("ISO-8859-1"),"UTF-8");



        	String departmentshead= request.getParameter("departmentshead");
        	departmentshead=new String(departmentshead.getBytes("ISO-8859-1"),"UTF-8");


        	String developers= request.getParameter("developers");
        	developers=new String(developers.getBytes("ISO-8859-1"),"UTF-8");


        	String estimatedworkload= request.getParameter("estimatedworkload");
        	estimatedworkload=new String(estimatedworkload.getBytes("ISO-8859-1"),"UTF-8");


        	String remarks= request.getParameter("remarks");
        	remarks=new String(remarks.getBytes("ISO-8859-1"),"UTF-8");

        	String   requirementanalysis_a=request.getParameter("requirementanalysis_a");
        	requirementanalysis_a=new String(requirementanalysis_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   requirementanalysis_b=request.getParameter("requirementanalysis_b");
        	requirementanalysis_b=new String(requirementanalysis_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   requirementanalysis_c=request.getParameter("requirementanalysis_c");
        	requirementanalysis_c=new String(requirementanalysis_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   requirementanalysis_d=request.getParameter("requirementanalysis_d");
        	requirementanalysis_d=new String(requirementanalysis_d.getBytes("ISO-8859-1"),"UTF-8");


        	String   requirementanalysis_e=request.getParameter("requirementanalysis_e");
        	requirementanalysis_e=new String(requirementanalysis_e.getBytes("ISO-8859-1"),"UTF-8");


        	String   requirementanalysis_f=request.getParameter("requirementanalysis_f");
        	requirementanalysis_f=new String(requirementanalysis_f.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedesign_a=request.getParameter("interfacedesign_a");
        	interfacedesign_a=new String(interfacedesign_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedesign_b=request.getParameter("interfacedesign_b");
        	interfacedesign_b=new String(interfacedesign_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedesign_c=request.getParameter("interfacedesign_c");
        	interfacedesign_c=new String(interfacedesign_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedesign_d=request.getParameter("interfacedesign_d");
        	interfacedesign_d=new String(interfacedesign_d.getBytes("ISO-8859-1"),"UTF-8");


        	String   codingdevelopment_a=request.getParameter("codingdevelopment_a");
        	codingdevelopment_a=new String(codingdevelopment_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   codingdevelopment_b=request.getParameter("codingdevelopment_b");
        	codingdevelopment_b=new String(codingdevelopment_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   codingdevelopment_c=request.getParameter("codingdevelopment_c");
        	codingdevelopment_c=new String(codingdevelopment_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedebugging_a=request.getParameter("interfacedebugging_a");
        	interfacedebugging_a=new String(interfacedebugging_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedebugging_b=request.getParameter("interfacedebugging_b");
        	interfacedebugging_b=new String(interfacedebugging_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   interfacedebugging_c=request.getParameter("interfacedebugging_c");
        	interfacedebugging_c=new String(interfacedebugging_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   integrationtesting_a=request.getParameter("integrationtesting_a");
        	integrationtesting_a=new String(integrationtesting_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   integrationtesting_b=request.getParameter("integrationtesting_b");
        	integrationtesting_b=new String(integrationtesting_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   integrationtesting_c=request.getParameter("integrationtesting_c");
        	integrationtesting_c=new String(integrationtesting_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   integrationtesting_d=request.getParameter("integrationtesting_d");
        	integrationtesting_d=new String(integrationtesting_d.getBytes("ISO-8859-1"),"UTF-8");


        	String   integrationtesting_e=request.getParameter("integrationtesting_e");
        	integrationtesting_e=new String(integrationtesting_e.getBytes("ISO-8859-1"),"UTF-8");


        	String   regressiontesting_a=request.getParameter("regressiontesting_a");
        	regressiontesting_a=new String(regressiontesting_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   regressiontesting_b=request.getParameter("regressiontesting_b");
        	regressiontesting_b=new String(regressiontesting_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   regressiontesting_c=request.getParameter("regressiontesting_c");
        	regressiontesting_c=new String(regressiontesting_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   regressiontesting_d=request.getParameter("regressiontesting_d");
        	regressiontesting_d=new String(regressiontesting_d.getBytes("ISO-8859-1"),"UTF-8");


        	String   businessacceptance_a=request.getParameter("businessacceptance_a");
        	businessacceptance_a=new String(businessacceptance_a.getBytes("ISO-8859-1"),"UTF-8");


        	String   businessacceptance_b=request.getParameter("businessacceptance_b");
        	businessacceptance_b=new String(businessacceptance_b.getBytes("ISO-8859-1"),"UTF-8");


        	String   businessacceptance_c=request.getParameter("businessacceptance_c");
        	businessacceptance_c=new String(businessacceptance_c.getBytes("ISO-8859-1"),"UTF-8");


        	String   businessacceptance_d=request.getParameter("businessacceptance_d");
        	businessacceptance_d=new String(businessacceptance_d.getBytes("ISO-8859-1"),"UTF-8");
        	
        	String   usertype=request.getParameter("usertype");
        	usertype=new String(usertype.getBytes("ISO-8859-1"),"UTF-8");
    	        
    	        try {
    	            Class.forName(driver).newInstance();
    	            con = DriverManager.getConnection(dbUrl, username, password);
    	            // 执行 SQL 查询
    	            stmt = con.createStatement();
    	            String sql;
    		        sql = String.format("SELECT * FROM dailystatistics WHERE demandid  = '%s'",demandid);
    		        
    	            ResultSet rs = stmt.executeQuery(sql);
    	            rs.last();
    	            int rowCount=rs.getRow();
    	            rs.beforeFirst();
    	            
    	            if(rowCount>0) {
    	            	String sqldevelopName="";
    		            while(rs.next()){
    		            	 sqldevelopName  = rs.getString("developName");
    		    
    		            }
    		            
    	            	if(sqldevelopName.equals(developName)||usertype.equals("1")) {
    	            	  String mysqlInset = String.format("UPDATE dailystatistics SET version= '%s',submittime= '%s',demanddemand= '%s',technologyhead= '%s',departmentshead= '%s',openness= '%s',grayscaleverified= '%s',currentstage= '%s',speedofprogress= '%s',developers= '%s',tester= '%s',businessacceptance= '%s', estimatedworkload= '%s',riskpointsorremarks= '%s',risklevelandImpact= '%s',riskliableperson= '%s',addingfunctionpoints= '%s',involvingsystems= '%s',grayproduction= '%s', requirementstartuptime= '%s',demandendtime= '%s',remarks= '%s',developName= '%s' ,requirementanalysis_a = '%s', requirementanalysis_b = '%s', requirementanalysis_c = '%s', requirementanalysis_d = '%s', requirementanalysis_e = '%s', requirementanalysis_f = '%s', interfacedesign_a = '%s', interfacedesign_b = '%s', interfacedesign_c = '%s', interfacedesign_d = '%s', codingdevelopment_a = '%s', codingdevelopment_b = '%s', codingdevelopment_c = '%s', interfacedebugging_a = '%s', interfacedebugging_b = '%s', interfacedebugging_c = '%s', integrationtesting_a = '%s', integrationtesting_b = '%s', integrationtesting_c = '%s', integrationtesting_d = '%s', integrationtesting_e = '%s', regressiontesting_a = '%s', regressiontesting_b = '%s', regressiontesting_c = '%s', regressiontesting_d = '%s', businessacceptance_a = '%s', businessacceptance_b = '%s', businessacceptance_c = '%s', businessacceptance_d = '%s' WHERE demandid = '%s'",version,submittime,demanddemand,technologyhead,departmentshead,openness,grayscaleverified,currentstage,speedofprogress,developers,tester,businessacceptance, estimatedworkload,riskpointsorremarks,risklevelandImpact,riskliableperson,addingfunctionpoints,involvingsystems,grayproduction, requirementstartuptime,demandendtime,remarks,developName,requirementanalysis_a,requirementanalysis_b,requirementanalysis_c,requirementanalysis_d,requirementanalysis_e,requirementanalysis_f,interfacedesign_a,interfacedesign_b,interfacedesign_c,interfacedesign_d,codingdevelopment_a,codingdevelopment_b,codingdevelopment_c,interfacedebugging_a,interfacedebugging_b,interfacedebugging_c,integrationtesting_a,integrationtesting_b,integrationtesting_c,integrationtesting_d,integrationtesting_e,regressiontesting_a,regressiontesting_b,regressiontesting_c,regressiontesting_d,businessacceptance_a,businessacceptance_b,businessacceptance_c,businessacceptance_d,demandid);
    	            	
    	            	  int  sisuccess = stmt.executeUpdate(mysqlInset);
    	            	  if(sisuccess>0) {
    	    		        out.println(requestsuccess);
    	    	            System.err.println("SQL修改：修改成功" + developName);
    	            	  }
    	            	  else {
      	            		String errString="SQL修改：修改失败" + developName+sqldevelopName;
    	    		        out.println(requestfaile+errString);
    	    	            System.err.println("SQL修改：修改失败" + developName);

    	            	  }
    	            	}
    	            	else {
    	            		String errString="SQL修改：修改名称不相同，需求单号相同" + developName+sqldevelopName;
    	    	            System.err.println(errString);
    	    		        out.println(requestfaile+errString);
    	            	}
    	            }
    	            else {
    	            	String mysqlInset = String.format("INSERT INTO dailystatistics (demandid, version, submittime, demanddemand, technologyhead, departmentshead, openness, grayscaleverified, currentstage, speedofprogress, developers, tester,businessacceptance, estimatedworkload, riskpointsorremarks, risklevelandImpact, riskliableperson,addingfunctionpoints, involvingsystems, grayproduction, requirementstartuptime, demandendtime, remarks, developName,requirementanalysis_a,requirementanalysis_b,requirementanalysis_c,requirementanalysis_d,requirementanalysis_e,requirementanalysis_f,interfacedesign_a,interfacedesign_b,interfacedesign_c,interfacedesign_d,codingdevelopment_a,codingdevelopment_b,codingdevelopment_c,interfacedebugging_a,interfacedebugging_b,interfacedebugging_c,integrationtesting_a,integrationtesting_b,integrationtesting_c,integrationtesting_d,integrationtesting_e,regressiontesting_a,regressiontesting_b,regressiontesting_c,regressiontesting_d,businessacceptance_a,businessacceptance_b,businessacceptance_c,businessacceptance_d) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",demandid,version,submittime,demanddemand,technologyhead,departmentshead,openness,grayscaleverified,currentstage,speedofprogress,developers,tester,businessacceptance,estimatedworkload,riskpointsorremarks,risklevelandImpact,riskliableperson,addingfunctionpoints,involvingsystems,grayproduction, requirementstartuptime,demandendtime,remarks,developName,requirementanalysis_a,requirementanalysis_b,requirementanalysis_c,requirementanalysis_d,requirementanalysis_e,requirementanalysis_f,interfacedesign_a,interfacedesign_b,interfacedesign_c,interfacedesign_d,codingdevelopment_a,codingdevelopment_b,codingdevelopment_c,interfacedebugging_a,interfacedebugging_b,interfacedebugging_c,integrationtesting_a,integrationtesting_b,integrationtesting_c,integrationtesting_d,integrationtesting_e,regressiontesting_a,regressiontesting_b,regressiontesting_c,regressiontesting_d,businessacceptance_a,businessacceptance_b,businessacceptance_c,businessacceptance_d);    	            	    	            	   	            	
    	     
    	            	int  sisuccess = stmt.executeUpdate(mysqlInset);
    	            	if(sisuccess>0) {
    	    		        out.println(requestsuccess);
    	            	}
    	            	else {
    	    		        out.println(requestfaile);
    	            	}
    	            }
    	            
   	        	 Date date = new Date();
  	             SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");  
  	             String timeDate=ft.format(date);
  	             String insertsql = String.format("UPDATE  personit SET  timenum =  '%s' WHERE  name =  '%s'",timeDate,developName);
	            	 int  insertsisuccess = stmt.executeUpdate(insertsql);
	            	 
    	        } catch (Exception e) {
    	            System.err.println("Caught " + e);
    	        }
        	
        
        }
        else if (transID.equals(standardAPNPushID)) { 
        	
            String developName= request.getParameter("developName");
            developName=new String(developName.getBytes("ISO-8859-1"),"UTF-8");
            
            String pushtext= request.getParameter("pushtext");
            pushtext=new String(pushtext.getBytes("ISO-8859-1"),"UTF-8");
            
           try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(dbUrl, username, password);
            // 执行 SQL 查询
            stmt = con.createStatement();
            String sql;   	            
            
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
       
            System.out.println("当前时间为: " + ft.format(date));

	        sql = String.format("SELECT * FROM personit WHERE  name =  '%s'",developName);
	        ResultSet rs = stmt.executeQuery(sql);
          // 展开结果集数据库
          while(rs.next()){
              // 通过字段检索
          	String tokenid  = rs.getString("tokenid");
            if(tokenid != null && tokenid.length() != 0) { 
            	
            	APNapple apnapple=new APNapple();
            	File file=new File(this.getClass().getResource("/../../").getPath());

                 String certificatePath=file.getPath()+"/path/comspdbmposnewbank.p12";
                 System.out.println(certificatePath);
    	        out.println(certificatePath);
            	apnapple.setDemandid(tokenid,response,pushtext);
            	
 		       out.println(requestsuccess+tokenid);
            }
            else {
  		       out.println(requestfaile+tokenid);
            }


          	
          
          }
	        
        } catch (Exception e) {
		       out.println(requestfaile+ e);

        }
       }
        
        else if (transID.equals(standardversionID)) {         	
            String developName= request.getParameter("developName");
            developName=new String(developName.getBytes("ISO-8859-1"),"UTF-8");
            
            String tokenid= request.getParameter("tokenid");
            tokenid=new String(tokenid.getBytes("ISO-8859-1"),"UTF-8");
    	        try {
    	            Class.forName(driver).newInstance();
    	            con = DriverManager.getConnection(dbUrl, username, password);
    	            // 执行 SQL 查询
    	            stmt = con.createStatement();
    	            String sql;   	            
    	            
    	            Date date = new Date();
    	            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
    	       
    	            System.out.println("当前时间为: " + ft.format(date));
    	            
    		        sql = String.format("UPDATE  personit SET  tokenid =  '%s' WHERE  name =  '%s'",tokenid,developName);
	            	  int  sisuccess = stmt.executeUpdate(sql);
	            	  if(sisuccess>0) {
  	    		        out.println(requestsuccess);
  	    	            System.err.println("SQL修1：修改成功" + developName);
  	            	  }
  	            	  else {
    	            		String errString="SQL修改2：修改失败" + developName+developName;
  	    		        out.println(requestfaile+errString);
  	    	            System.err.println("SQL修改2：修改失败" + developName);

  	            	  }
    		        
    	        } catch (Exception e) {
	    		       out.println(requestfaile+ e);

    	        }
        	
        }
        
        else if (transID.equals(standardpuandversionid)) {         	
            String version= request.getParameter("version");
            version=new String(version.getBytes("ISO-8859-1"),"UTF-8");
            if(version.equals("1.0")) {
		        out.println("1|||||https://www.pgyer.com/PSSc");
            }
            else if(version.equals("2.0")){
		        out.println("0|||||https://www.pgyer.com/PSSc");
            }
            {
		        out.println("");
            }
	
        }
        
        
        
		
	}

}
