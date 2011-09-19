1、tbl_user.sql是数据库脚本
2、记得配tomcat jndi
<Resource name="jdbc/baties" 
		auth="Container" 
		type="javax.sql.DataSource"
        maxActive="100" 
		maxIdle="30" 
		maxWait="10000"
        username="root" 
		password="123456" 
		driverClassName="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/yiidemo"/>
contex.xml为tomcat配置文件