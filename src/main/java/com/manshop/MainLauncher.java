package com.manshop;

import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean(create = "init", depose = "depose")
public class MainLauncher {
    @Inject
    protected PropertiesProxy conf;

    @At("/")
    @Ok("->:/index.html")
    public void index() {
    }

    @Inject
    private Dao dao;

    public void init() {
        Daos.createTablesInPackage(dao, "com.manshop.bean", false);
        Daos.migration(dao, "com.manshop.bean", true, false, false);
    }

    public void depose() {
    }

    public static void main(String[] args) throws Exception {
        new NbApp(MainLauncher.class).run();
    }
}
