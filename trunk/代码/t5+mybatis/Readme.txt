1��tbl_user.sql�����ݿ�ű�
2���ǵ���tomcat jndi
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
contex.xmlΪtomcat�����ļ�