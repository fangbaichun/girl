package com.fbc.frame.gateway.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Configuration
public class RouterConfig {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        //动态获取路由配置
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from sys_route where state = '1'");
        if (!CollectionUtils.isEmpty(list)) {
            for (Map<String, Object> map : list) {
                String id = (String) map.get("id");
                String path = (String) map.get("path");
                String uri = (String) map.get("uri");
                routes.route(id,p -> p.path(path).uri(uri));
            }
        }
        return routes.build();
    }
}
