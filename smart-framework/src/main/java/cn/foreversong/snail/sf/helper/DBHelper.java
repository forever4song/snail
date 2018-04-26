package cn.foreversong.snail.sf.helper;

import cn.foreversong.snail.sf.util.CollectionUtil;
import cn.foreversong.snail.sf.util.PropsUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/26
 * Time: 9:50
 * Description: 数据库助手类
 */
public final class DBHelper {
    private static final Logger log = LoggerFactory.getLogger(DBHelper.class);
    private static final ThreadLocal<Connection> CONNECTION_HOLDER; // 连接池
    private static final QueryRunner QUERY_RUNNER;  // SQL语句执行器
    private static final BasicDataSource DATA_SOURCE;   // 数据源

    static{
        CONNECTION_HOLDER = new ThreadLocal<Connection>();
        QUERY_RUNNER = new QueryRunner();

        Properties conf = PropsUtil.loadProps("config.properties");
        String driver = conf.getProperty("jdbc.driver");
        String url = conf.getProperty("jdbc.url");
        String username = conf.getProperty("jdbc.username");
        String password = conf.getProperty("jdbc.password");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
    }
    /**
     * 查询实体列表
     * @param entityClass 实体类类对象
     * @param sql         查询语句
     * @param conn        数据库连接对象
     * @param params      查询参数
     * @param <T>   实体类类型
     * @return 实体列表
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Connection conn,Object... params){
        List<T> entityList;
        try {
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass),params);
        } catch (SQLException e) {
            log.error("query entity list failure",e);
            throw new RuntimeException(e);
        }
        return entityList;
    }

    /**
     * 查询实体
     * @param entityClass   实体类类型
     * @param sql 查询语句
     * @param conn  数据库连接对象
     * @param params    查询参数
     * @param <T>   实体类类型
     * @return  实体类
     */
    public static <T> T queryEntity(Class<T> entityClass,String sql, Connection conn, Object... params){
        T entity;
        try {
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass),params);
        } catch (SQLException e) {
            log.error("query entity failure", e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     * 执行查询
     * @param sql  查询语句
     * @param params    查询参数
     * @return 返回查询结果
     */
    public static List<Map<String,Object>> executeQuery(String sql,Object... params){
        List<Map<String,Object>> result;
        try {
            Connection conn = getConnection();
            result = QUERY_RUNNER.query(conn,sql,new MapListHandler(),params);
        } catch (Exception e) {
            log.error("execute query failire",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 执行更新语句(包括update、insert、delete)
     * @param sql 更新语句
     * @param params    更新参数
     * @return 返回影响行数
     */
    public static int executeUpdate(String sql,Object... params){
        int rows;
        try{
            Connection conn = getConnection();
            rows = QUERY_RUNNER.execute(conn,sql,params);
        } catch (SQLException e) {
            log.error("execute update failire",e);
            throw new RuntimeException(e);
        }
        return rows;
    }

    /**
     * 插入实体
     * @param entityClass 实体类 类类型
     * @param fieldMap  字段Map
     * @param <T>   实体类类型
     * @return  标志
     */
    public static <T> boolean insertEntity(Class<T> entityClass,Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            log.error("can't insert entity: fieldMap is empty");
            throw new RuntimeException();
        }
        String sql = "INSERT INTO "+getTableName(entityClass);
        StringBuffer columns = new StringBuffer("(");
        StringBuffer values = new StringBuffer("(");
        fieldMap.keySet().forEach((k)->{
            columns.append(k).append(",");
            values.append("?,");
        });
        columns.replace(columns.indexOf(","), columns.length(), ")");
        values.replace(values.indexOf(","), values.length(), ")");
        sql += columns + "VALUES" + values;
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;
    }

    /**
     * 更新实体
     *
     * @param entityClass 实体类 类类型
     * @param id 主键ID
     * @param fieldMap  字段Map
     * @param <T> 实体类型
     * @return 标志
     */
    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            log.error("cna't insert entity: fieldMap is empty");
            throw new RuntimeException();
        }
        String sql = "UPDATE " + getTableName(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()
                ) {
            columns.append(fieldName).append("=?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(",")) + " WHERE id=?";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();
        return executeUpdate(sql, params) == 1;
    }

    /**
     * 删除实体
     * @param entityClass 实体类 类类型
     * @param id 主键
     * @param <T> 实体类类型
     * @return 标志
     */
    public static <T> boolean deleteEntity(Class<?> entityClass,Long id){
        String sql = "DELETE FROM "+ getTableName(entityClass) + "WHERE id=?";
        return executeUpdate(sql,id) == 1;
    }

    /**
     *返回实体名称
     * @param entityClass   实体类
     * @return  实体类名称
     */
    public static  String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName();
    }

    /**
     * 执行Sql脚本
     * @param filePath  文件路径
     */
    public static void executeSqlFile(String filePath){
        String file = "sql/customer_init.sql";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try{
            String sql ;
            while ((sql = reader.readLine()) != null){
                executeUpdate(sql);
            }
        } catch (Exception e) {
            log.error("execute sql file failure", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取数据库连接
     * @return 数据库连接
     */
    public static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if(conn == null){
            try{
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                log.error("get connectin failure",e);
                throw new RuntimeException();
            }finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

    /**
     * 开启事务
     */
    public static void beginTransraction(){
        Connection conn = getConnection();
        if(conn!=null){
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                log.error("begin transraction failure",e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransraction(){
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                log.error("comit transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }
    /**
     * 回滚事务
     */
    public static void rollbackTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e) {
                log.error("rollback transcation failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }




}