package com.example.demo.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.netty.RequestFuture;
import com.example.demo.netty.Response;

/**
 * @author 61635
 * @Classname Mediator
 * @Description TODO
 * @Date 2022/1/16 13:56
 * @Created by 61635
 */
public class Mediator {
    public static Map<String,MethodBean> methodBeans;
    static{
        methodBeans = new HashMap<>();
    }

    public static Response process(RequestFuture requestFuture){
        Response response = new Response();
        String path = requestFuture.getPath();
        MethodBean methodeBean = methodBeans.get(path);
        if(methodeBean !=null){
            Object bean = methodeBean.getBean();
            Method method = methodeBean.getMethod();
            Object body = requestFuture.getRequest();
            Class[] paramTypes = method.getParameterTypes();
            Class paramType = paramTypes[0];
            Object param = null;
            if(paramType.isAssignableFrom(List.class)){
                param = JSONArray.parseArray(JSONArray.toJSONString(body),paramType);
            }else if(paramType.getName().equals(String.class.getName())){
                param = body;
            }else{
                param = JSONObject.parseObject(JSONObject.toJSONString(body),paramType);
            }
            try {
                Object result = method.invoke(bean,param);
                response.setResult(result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        response.setId(requestFuture.getId());
        return response;
    }

    public static class MethodBean{
        private Object bean;
        private Method method;
        public Object getBean(){
            return bean;
        }
        public void setBean(Object bean){
            this.bean = bean;
        }

        public Method getMethod(){
            return method;
        }

        public void setMethod(Method method){
            this.method = method;
        }
    }
}
