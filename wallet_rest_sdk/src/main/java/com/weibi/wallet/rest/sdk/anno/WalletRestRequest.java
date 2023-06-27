package com.weibi.wallet.rest.sdk.anno;

import com.weibi.wallet.rest.sdk.constants.WebProxyConstant;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WalletRestRequest {
    RequestMethod method() default RequestMethod.GET;

    String path() default "";

    Class typeInResponse() default  Object.class;
}
