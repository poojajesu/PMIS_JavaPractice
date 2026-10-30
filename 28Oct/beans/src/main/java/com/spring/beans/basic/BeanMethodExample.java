package com.spring.beans.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanMethodExample {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfiguration.class);

        BasicBean bean = context.getBean(BasicBean.class);

        bean.showMessage();
    }
}