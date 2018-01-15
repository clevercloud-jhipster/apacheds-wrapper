package com.clevercloud.apacheds;


import org.apache.directory.server.UberjarMain;
import org.apache.directory.server.core.api.DirectoryService;
import org.apache.mina.transport.socket.SocketAcceptor;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener
public class ServletInitListener implements ServletContextListener {

    private UberjarMain instance;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String instanceName = System.getenv("INSTANCE_NAME");
        try {
            instance = new UberjarMain();
            instance.start(instanceName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            instance.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}