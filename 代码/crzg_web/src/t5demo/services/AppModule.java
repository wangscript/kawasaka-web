package t5demo.services;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Configuration module
 */
public class AppModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(UserDAO.class, UserDAOImpl.class);
	}
	
	public static SqlSessionFactory buildSqlSessionFactory() {
        try {
            String resource = "Configuration.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            logger.warn("failed to build SqlSessionFactory: ", e);
            return null;
        }
    }
	
	private static Logger logger = LoggerFactory.getLogger(AppModule.class);

}
