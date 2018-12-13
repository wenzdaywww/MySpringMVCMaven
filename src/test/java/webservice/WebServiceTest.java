package webservice;

import org.junit.Test;

import com.www.app.webservice.axis2client.AxisServiceStub;

public class WebServiceTest {
	
	/**
	 * 测试通过Axis2生成的客户端代码的Axis2WebService
	 */
	@Test
	public void testAxis2WebServicesProduceByAxis2(){
		try {
			//AxisServiceStub必须使用带参数的，否则访问不到地址
			AxisServiceStub stub=new AxisServiceStub("http://localhost:8080/maven/services/AxisService?wsdl");
			/** getValue方法*/
			AxisServiceStub.GetValue getValue=new AxisServiceStub.GetValue();
			getValue.setName("测试axis2的webservice的接口调用,Stub类");
			System.out.println(stub.getValue(getValue).get_return());
			/** getUser方法*/
			AxisServiceStub.GetUser getUser=new AxisServiceStub.GetUser();
			getUser.setName("www");
			AxisServiceStub.Sysuser sysuser=stub.getUser(getUser).get_return();
			System.out.println("UserId="+sysuser.getUserId()+" UserName="+sysuser.getUserName()+" Passwd="+sysuser.getPasswd());
			/** login方法*/
			AxisServiceStub.Login login=new AxisServiceStub.Login();
			login.setXmlStr("登入");
			System.out.println(stub.login(login).get_return());
			/** logout方法*/
			AxisServiceStub.Logout logout=new AxisServiceStub.Logout();
			logout.setXmlStr("退出");
			System.out.println(stub.logout(logout).get_return());
			/** get方法*/
			AxisServiceStub.Get get=new AxisServiceStub.Get();
			get.setXmlStr("get方法");
			System.out.println(stub.get(get).get_return());
			/** set方法*/
			AxisServiceStub.Set set=new AxisServiceStub.Set();
			set.setXmlStr("设置");
			System.out.println(stub.set(set).get_return());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
