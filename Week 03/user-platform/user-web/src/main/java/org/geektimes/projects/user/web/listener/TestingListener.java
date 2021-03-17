package org.geektimes.projects.user.web.listener;

import org.geektimes.context.ComponentContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.management.UserMXBean;
import org.geektimes.projects.user.management.UserManager;
import org.geektimes.projects.user.management.UserManagerMBean;
import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.management.DynamicMBean;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.StandardMBean;
import javax.management.modelmbean.ModelMBeanInfo;
import javax.management.modelmbean.RequiredModelMBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.util.logging.Logger;

/**
 * 测试用途
 */
@Deprecated
public class TestingListener implements ServletContextListener {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();
        ComponentContext context = new ComponentContext();
        context.init(servletContext);

        DBConnectionManager dbConnectionManager = context.getComponent("bean/DBConnectionManager");
        Connection connection = dbConnectionManager.getConnection();
//        testPropertyFromServletContext(sce.getServletContext());
//        testPropertyFromJNDI(context);
//        testUser(dbConnectionManager.getEntityManager());
//        logger.info("所有的 JNDI 组件名称：[");
//        context.getComponentNames().forEach(logger::info);
//        logger.info("]");


        try{
            // 获取平台 MBean Server
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            // 为 UserMXBean 定义 ObjectName
            ObjectName objectName = new ObjectName("org.geektimes.projects.user.management:type=User");
            // 创建 UserMBean 实例

//            UserMXBean mxBean = new UserMXBean();
            StandardMBean standardMBean = new StandardMBean(new UserManager(new User()), UserManagerMBean.class);

            mBeanServer.registerMBean(standardMBean, objectName);

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public static RequiredModelMBean createModelerMBean(UserMXBean mxBean) {
        RequiredModelMBean model = null;
        try {
            model = new RequiredModelMBean();
            model.setManagedResource(mxBean, "ObjectReference");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    private void testPropertyFromServletContext(ServletContext servletContext) {
        String propertyName = "application.name";
        logger.info("ServletContext Property[" + propertyName + "] : "
                + servletContext.getInitParameter(propertyName));
    }

    private void testPropertyFromJNDI(ComponentContext context) {
        String propertyName = "maxValue";
        logger.info("JNDI Property[" + propertyName + "] : "
                + context.lookupComponent(propertyName));
    }

    private void testUser(EntityManager entityManager) {
        User user = new User();
        user.setName("小马哥");
        user.setPassword("******");
        user.setEmail("mercyblitz@gmail.com");
        user.setPhoneNumber("abcdefg");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        System.out.println(entityManager.find(User.class, user.getId()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
