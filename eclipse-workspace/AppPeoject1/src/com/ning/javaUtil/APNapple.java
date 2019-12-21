package com.ning.javaUtil;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

public class APNapple {
	
	public void setDemandid(String demandid,HttpServletResponse response,String text) {
		       try {
		    	   
		              PrintWriter out = response.getWriter();

		              // token为不包含空格和<>的字母数字组合(字母不区分大小写)
		              String deviceToken = demandid;    
		              PushNotificationManager pushManager = PushNotificationManager.getInstance();
		              pushManager.addDevice("iphone", deviceToken);
		             
		              

		             // 苹果推送服务器 
		             /*
		             开发状态服务器地址 gateway.sandbox.push.apple.com 2195
		             发布状态服务器地址 gateway.push.apple.com 2195
		             
		             需要注意：
		             Xcode打出的Debug安装包只能选择开发服务器,证书可以选择开发推送证书或者发布推送证书；
		             Xcode打出的AdHoc或Release安装包只能选择发布务器和发布推送证书；
		             */
		              String host= "gateway.push.apple.com";
		              // 端口号
		              int port = 2195; 
		              
		              // 在mac系统下导出的p12证书(开发证书对应开发环境，发布证书对应所有环境)
		              String certificatePath = "resources/comspdbmposnewbank.p12"; 
		              
		              
		               File file = new File(certificatePath); 

		             
		             
		               file=new File(this.getClass().getResource("/../../").getPath());

		               certificatePath=file.getPath()+"/path/comspdbmposnewbank.p12";
                       System.out.println(certificatePath); 
		              // p12证书密码
		              String certificatePassword= "123456";
		              // 初始化tcp连接                
		              pushManager.initializeConnection(host, port, certificatePath, certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
				       out.println("123312"+demandid);
     
		              // Send Push
		              Device client = pushManager.getDevice("iphone");
		              // 要推送的消息
		              PayLoad payLoad = new PayLoad();
		              payLoad.addAlert(text);
		              payLoad.addBadge(1);
		              payLoad.addSound("default");
		              // 开始推送
		              pushManager.sendNotification(client, payLoad);
		              pushManager.stopConnection();
		              pushManager.removeDevice("iphone");
		              System.out.println("push succeed!");
		             }
		             catch (Exception e) {  
		            	 System.out.println("push succeed!1");
		                 e.printStackTrace();
		             }
		       //		                 else {
//		                 System.out.println("push succeed!");
//		             }

	}

}


