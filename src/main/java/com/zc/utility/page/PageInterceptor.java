/**
 * @title ExamplePlugin.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年6月6日 下午4:31:15
 * Copyright 2016 知藏. All right reserved.
 * mybatis分页拦截器 默认使用postgresql分页驱动
 */
package com.zc.utility.page;


import com.zc.utility.ParamHelper;
import com.zc.utility.ReflectHelper;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.zc.utility.ParamHelper.defaultVal;


@Component
@Intercepts({@Signature(type = StatementHandler.class, args = {
        Connection.class, Integer.class}, method = "prepare")})
public class PageInterceptor implements Interceptor {

    private PageDriver initPageDriver;
    private PageDriver pageDriver;

    private Page page;

    private String funcRegex;

    private boolean useParam;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        RoutingStatementHandler statementHandler1 = (RoutingStatementHandler) invocation
                .getTarget();
        BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
                .getValueByFieldName(statementHandler1, "delegate");

        MappedStatement mappedStatement = (MappedStatement) ReflectHelper
                .getValueByFieldName(delegate, "mappedStatement");

        String funcName = mappedStatement.getId();

        if (!funcName.matches(funcRegex) && !useParam) {
            return invocation.proceed();
        }

        BoundSql boundSql = delegate.getBoundSql();

        Object parameterObject = boundSql.getParameterObject();

        if (parameterObject == null) {
            return invocation.proceed();
        }

        this.pageDriver = this.initPageDriver;
        this.page = null;


        if (parameterObject instanceof Page) {
            this.page = (Page) parameterObject;
        } else if (parameterObject instanceof Map) {
            for (Object obj : ((Map) parameterObject).values()) {
                if (obj instanceof Page) {
                    this.page = (Page) obj;
                    break;
                }
            }
        }
        if (page == null) {
            return invocation.proceed();
        }

        if (page.isDisable()) {
            return invocation.proceed();
        }

        if (page.getPageDriver() != null) {
            this.pageDriver = page.getPageDriver();
        } else {
            page.setPageDriver(this.pageDriver);
        }

        Connection connection = (Connection) invocation.getArgs()[0];

        this.setTotalCount(mappedStatement, connection, parameterObject);

        // 获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
        String sql = boundSql.getSql();

        String pageSql = this.pageDriver.getPageSql(page, sql);

        ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        try {

            this.funcRegex = defaultVal(properties.getProperty("funcRegex"),
                    "useParam");// .*ByPage$
            this.useParam = this.funcRegex.equals("useParam");
            String driver = properties.getProperty("pageDriver");
            if (ParamHelper.isValidStr(driver)) {
                Class<PageDriver> c = (Class<PageDriver>) Class.forName(driver);
                this.initPageDriver = c.newInstance();
            } else {
                this.initPageDriver = new MySqlPageDriver();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置获取总记录数
     *
     * @param mappedStatement
     * @param connection
     * @param parameterObject
     * @创建人 zhuzhzh @创建时间 2016年6月12日 下午2:56:10
     */
    private void setTotalCount(MappedStatement mappedStatement,
                               Connection connection, Object parameterObject) {

        SqlSource sqlSource = mappedStatement.getSqlSource();
        // BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);

        String sql = boundSql.getSql();

        String countSql = pageDriver.getCountSql(sql);

        // 通过BoundSql获取对应的参数映射
        List<ParameterMapping> parameterMappings = boundSql
                .getParameterMappings();
        BoundSql countBoundSql = new BoundSql(
                mappedStatement.getConfiguration(), countSql, parameterMappings,
                parameterObject);

        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                countBoundSql.setAdditionalParameter(prop,
                        boundSql.getAdditionalParameter(prop));
            }
        }

        ParameterHandler parameterHandler = mappedStatement.getConfiguration()
                .newParameterHandler(mappedStatement, parameterObject,
                        countBoundSql);
        // ParameterHandler parameterHandler = new DefaultParameterHandler(
        // mappedStatement, parameterObject, countBoundSql);

        // 通过connection建立一个countSql对应的PreparedStatement对象。
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalCount = rs.getInt(1);
                page.setTotalCount(totalCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception e2) {
            }
        }
    }

    /**
     * @return the initPageDriver
     */
    public PageDriver getInitPageDriver() {
        return initPageDriver;
    }

    /**
     * @param initPageDriver the initPageDriver to set
     */
    public void setInitPageDriver(PageDriver initPageDriver) {
        this.initPageDriver = initPageDriver;
    }

}