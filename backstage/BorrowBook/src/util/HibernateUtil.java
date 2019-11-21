package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
      private static Configuration cfg=null;
      private static SessionFactory factory=null;
      private static Session session =null;
      
      
      static{
    	  cfg=new Configuration().configure();
    	  factory =cfg.buildSessionFactory();
    	   }
      
      
      public static Session getSession()
      {
    	  if(factory!=null)
    		 return session=factory.openSession();
    	  factory =cfg.buildSessionFactory();
    	  return session=factory.openSession();
    	
      }
      public static void closeSession(Session session)
      {
    	  if(session!=null&&session.isOpen())
    		  session.close();
      }
}
