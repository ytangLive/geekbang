package org.geektimes.rest;

import org.geektimes.rest.client.DefaultVariantListBuilder;
import org.geektimes.rest.core.DefaultResponseBuilder;
import org.geektimes.rest.core.DefaultUriBuilder;
import org.geektimes.rest.domain.User;
import org.geektimes.rest.domain.UserBeanHeaderDelegate;

import javax.ws.rs.core.*;
import javax.ws.rs.ext.RuntimeDelegate;

public class DefaultRuntimeDelegate extends RuntimeDelegate {

    @Override
    public UriBuilder createUriBuilder() {
        return new DefaultUriBuilder();
    }

    @Override
    public Response.ResponseBuilder createResponseBuilder() {
        return new DefaultResponseBuilder();
    }

    @Override
    public Variant.VariantListBuilder createVariantListBuilder() {
        return new DefaultVariantListBuilder();
    }

    @Override
    public <T> T createEndpoint(Application application, Class<T> endpointType) throws IllegalArgumentException, UnsupportedOperationException {
        return null;
    }

    @Override
    public <T> HeaderDelegate<T> createHeaderDelegate(Class<T> type) throws IllegalArgumentException {

        return  new HeaderDelegate<T>() {
            @Override
            public T fromString(String value) {
                return null;
            }

            @Override
            public String toString(T value) {
                if(value.getClass() == MediaType.class){
                    return ((MediaType)value).getSubtype();
                }
                return "";
            }
        };
    }

    @Override
    public Link.Builder createLinkBuilder() {
        return null;
    }

}
