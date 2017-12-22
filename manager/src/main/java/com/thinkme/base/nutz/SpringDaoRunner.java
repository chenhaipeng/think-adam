package com.thinkme.base.nutz;

import org.nutz.dao.ConnCallback;
import org.nutz.dao.impl.sql.run.NutDaoRunner;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

public class SpringDaoRunner extends NutDaoRunner {

    @Override
    public void _run(DataSource dataSource, ConnCallback callback) {

        Connection con = DataSourceUtils.getConnection(dataSource);
        try {
            callback.invoke(con);
        } catch (Exception e) {
            if (e instanceof RuntimeException)
                throw (RuntimeException) e;
            else
                throw new RuntimeException(e);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }
}