package com.weibi.wallet.rest.sdk.anno;

import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WalletRestRequest {
    RequestMethod method() default RequestMethod.GET;

    String path() default "";

    
    Class typeInResponse() default  Object.class;
}
