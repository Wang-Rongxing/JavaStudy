package com.hnsoftedu.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 配置类
 * 主要用于配置MyBatis-Plus的各种插件，例如分页插件
 *
 * @Configuration 表示这是一个Spring配置类，会被Spring容器加载
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 创建并配置MyBatis-Plus的拦截器Bean
     * 该方法主要注册MyBatis-Plus的分页插件
     *
     * @return 返回配置好的MybatisPlusInterceptor拦截器实例
     *
     * @Bean 表示该方法返回的对象将被注册为Spring容器中的一个Bean
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 创建MyBatis-Plus的主拦截器实例
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页内部拦截器，并指定数据库类型为MySQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 如果配置多个插件, 切记分页最后添加
        // 如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
        // 但单数据源环境下，建议明确指定数据库类型
        return interceptor;
    }
}
