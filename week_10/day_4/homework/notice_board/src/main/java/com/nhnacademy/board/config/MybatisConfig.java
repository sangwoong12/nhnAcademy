package com.nhnacademy.board.config;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@MapperScan("com.nhnacademy.board.mapper.**")
@EnableTransactionManagement //TransactionalAspect 와 같은 역활을 한다. @Transactional 이 붙어 있는 아이들만 적용
public class MybatisConfig {
    private final DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/maps/*.xml"));
        //camelcase 설정
        //https://mybatis.org/mybatis-3/configuration.html
        //TODO 이걸 적용하여도 왜 xml 에서 as 로 똑같게 만들어야지만 작동하는지 알아내기.
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory;
    }
}
