package com.nhnacademy.student.init;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ControllerFactory {
    private final Map<String, Object> beanMap = new HashMap<>();
    public void init(Set<Class<?>> c){
        for(Class<?> clazz : c){
            log.info("controller-class: {}", clazz.getName());
            Annotation[] annotations = clazz.getAnnotations();
            if(annotations.length>0){
                String annotation = annotations[0].toString();
//                log.info("annotion : {}",annotation);

                if(isController(annotation)){
                    String method = getMethod(annotation);
                    String path = getPath(annotation);
                    String key = getKey(method,path);
                    log.info("method : [{}] path: [{}]",method,path);
                    log.info("key : {}",key);
                    try {
                        //default 생성자 , 즉 해당 클래스를 생성;
                        Object command = clazz.getDeclaredConstructor().newInstance();
                        beanMap.put(key,command);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    public Object getBean(String method, String path){
        String key = getKey(method, path);
        log.info("input key : {}",key);
        if(!beanMap.containsKey(key)){
            throw new RuntimeException("error");
        }
        return beanMap.get(key);
    }
    private String getKey(String method, String path) {
        return method + "-" + path;
    }

    private String getPath(String annotation) {
        int start = annotation.indexOf("value=") + 7;
        int end = annotation.length()-2;
        return annotation.substring(start,end);
    }

    private String getMethod(String annotation) {
        int start = annotation.indexOf("method=") + 7;
        int end = annotation.indexOf(",");
        return annotation.substring(start,end);
    }

    private boolean isController(String annotation) {
        return annotation.contains("RequestMapping");
    }
}
