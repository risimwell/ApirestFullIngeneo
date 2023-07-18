package com.jms.apirestfull.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorJwtIOConfig implements WebMvcConfigurer {

@Autowired
private InterceptorJwtIO interceptorJwtIO; // El interceptor que acabamos de crear

@Override
public void addInterceptors(InterceptorRegistry registry) {
// Registrar el interceptor y especificar los patrones de ruta que se deben incluir o excluir
registry.addInterceptor(interceptorJwtIO)
.addPathPatterns("/[**") // Incluir todas las rutas
.excludePathPatterns("/api/user/**](https://www.bing.com/search?form=SKPBOT&q=%26quot%3B%29%20%2F%2F%20Incluir%20todas%20las%20rutas%0D%0A.excludePathPatterns%28%26quot%3B%2Fapi%2Fuser%2F)"); // Excluir las rutas que empiecen por /api/user/
}

}
