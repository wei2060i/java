package com.mybatis_study.test;

import com.mybatis_study.bean.Department;
import com.mybatis_study.bean.Employee;
import com.mybatis_study.dao.DepartmentMapper;
import com.mybatis_study.dao.EmployeeMapper;
import com.mybatis_study.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sky
 * @date 2020/12/2 15:55
 */
public class MainTest {

    // 1、获取sqlSessionFactory对象

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static void main(String[] args) throws IOException {
        CatchTest();
    }
    /**
     * 两级缓存：
     * 一级缓存:(本地缓存):sqlSession级别的缓存。一级缓存是一直开启的;sqlSession级别的map
     * 		与数据库同一次会话期间查询到的数据会放在本地缓存中。
     * 		以后如果需要获取相同的数据,直接从缓存中拿,没必要再去查询数据库;
     *
     * 		一级缓存失效情况（没有使用到当前一级缓存的情况,效果就是,还需要再向数据库发出查询):
     * 		1、sqlSession不同。
     * 		2、sqlSession相同，查询条件不同.(当前一级缓存中还没有这个数据)
     * 		3、sqlSession相同，两次查询之间执行了增删改操作(这次增删改可能对当前数据有影响)
     * 		4、sqlSession相同，手动清除了一级缓存(缓存清空)openSession.clearCache();
     *
     * 二级缓存(全局缓存):基于namespace级别的缓存:一个namespace对应一个二级缓存;
     * 		工作机制:
     * 		1、一个会话,查询一条数据,这个数据就会被放在当前会话的一级缓存中;
     * 		2、如果会话关闭;一级缓存中的数据会被保存到二级缓存中;新的会话查询信息,就可以参照二级缓存中的内容;
     * 		3、sqlSession===EmployeeMapper==>Employee
     * 						DepartmentMapper===>Department
     * 			不同namespace查出的数据会放在自己对应的缓存中(map)
     * 			效果:数据会从二级缓存中获取
     * 				查出的数据都会被默认先放在一级缓存中。
     * 				只有会话提交或者关闭以后,一级缓存中的数据才会转移到二级缓存中。
     * 		使用:
     * 			1)、开启全局二级缓存配置: <setting name="cacheEnabled" value="true"/>
     * 			2)、去mapper.xml中配置使用二级缓存：
     * 				<cache></cache>
     * 			3)、我们的POJO需要实现序列化接口
     *
     * 和缓存有关的设置/属性：
     * 			1）、全局配置 cacheEnabled=true:false 关闭缓存(二级缓存关闭)(一级缓存一直可用的)
     * 			2）、每个select标签都有useCache="true":
     * 					false:不使用缓存(一级缓存依然使用,二级缓存不使用)
     * 			3）、【每个增删改标签的:flushCache="true":(一级二级都会清除)】
     * 					增删改执行完成后就会清楚缓存:
     * 					测试：flushCache="true";一级缓存就清空了;二级也会被清除;
     * 					查询标签:flushCache="false":
     * 						如果flushCache=true;每次查询之后都会清空缓存;缓存是没有被使用的;
     * 			4）、sqlSession.clearCache();只是清楚当前session的一级缓存;
     * 			5）、localCacheScope:本地缓存作用域:(一级缓存SESSION);当前会话的所有数据保存在会话缓存中;
     * 								STATEMENT:可以禁用一级缓存;
     *           https://mybatis.org/mybatis-3/zh/configuration.html#typeAliases
     *第三方缓存整合:
     *		1）、导入第三方缓存包即可;
     *		2）、导入与第三方缓存整合的适配包;官方有;
     *		3）、mapper.xml中使用自定义缓存
     *		<cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
     *
     *责任链模式(Chain of Responsibility Pattern)
     * 避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，
     * 并且沿着这条链传递请求，直到有对象处理它为止。职责链模式是一种对象行为型模式	。
     */
    private static void CatchTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        //可以执行批量操作的sqlSession
        //SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employeeById = mapper.getEmployeeById(1);
            System.out.println(employeeById);
            mapper.getEmployeeById(1);
        } finally {
            openSession.close();
        }
    }

    private static void DynamicSqlTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Integer> ids = new ArrayList<>();
            ids.add(1);ids.add(2);
            List<Employee> empByConditionForeach = mapper.getEmpByConditionForeach(ids);
            empByConditionForeach.forEach(System.out::println);
            //手动提交
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    private static void departmentTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department departmentById = mapper.getDepartmentById(1);
            System.out.println(departmentById);
            //手动提交
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    /**
     * 测试增删改
     * 1、mybatis允许增删改直接定义以下类型返回值
     * Integer、Long、Boolean、void
     * 2、我们需要手动提交数据
     * sqlSessionFactory.openSession();===》手动提交
     * sqlSessionFactory.openSession(true);===》自动提交
     *
     * @throws IOException
     */
    private static void curdTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            //boolean bool = mapper.updateEmp(new Employee(1, "小舞", "123@qq.com", 0,1));
           //Integer bool = mapper.addEmp(new Employee(null, "小舞", "123@qq.com", null, 1));
            //boolean bool = mapper.deleteEmpById(0);
            Employee bool = mapper.getEmpById(Collections.singletonList(1));
            //Map<String, Employee> bool = mapper.getEmpByLastNameLikeReturnMap("小舞");
            System.out.println(bool);
            //手动提交
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    private static void selectOneTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
//            Employee employee = openSession.selectOne(
//                    "com.mybatis_study.dao.EmployeeMapper.getEmployeeById", 1);
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employeeById = mapper.getEmployeeById(1);
            System.out.println(employeeById);
        } finally {
            openSession.close();
        }
    }
}
