/*
 * ---------------------------------------------------------------------------
 * 
 * COPYRIGHT (c) 2015 Mnubo Inc. All Rights Reserved.
 * 
 * The copyright to the computer program(s) herein is the property of Mnubo Inc. The program(s) may be used and/or
 * copied only with the written permission from Mnubo Inc. or in accordance with the terms and conditions stipulated in
 * the agreement/contract under which the program(s) have been supplied.
 * 
 * Author: marias Date : Jul 22, 2015
 * 
 * ---------------------------------------------------------------------------
 */

package com.mnubo.java.sdk.client.mapper;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;

public abstract class SDKMapperUtils
{
    private static ObjectMapper objectMapper = SDKObjectMapperConfig.getObjectMapper();

    /**
     * @throws IllegalStateException if any exception occurs.
     */
    public static < T > T readValue( String content, Class< T > valueType ) throws IllegalStateException
    {
        try
        {
            return objectMapper.readValue( content , valueType );
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "reading json content" , e.getCause() );
        }
    }

    public static < T > T readValue( String content, TypeReference< T > valueType ) throws IllegalStateException
    {
        try
        {
            return objectMapper.readValue( content , valueType );
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "reading json content" , e.getCause() );
        }
    }

    /**
     * @throws IllegalStateException if any exception occurs.
     */
    public static < T > T readValue( String content, CollectionType valueType ) throws IllegalStateException
    {
        try
        {
            return objectMapper.readValue( content , valueType );
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "reading json content" , e.getCause() );
        }
    }

    /**
     * @throws IllegalStateException if any exception occurs.
     */
    @SuppressWarnings( "unchecked" )
    public static Map< String , String > readValueAsMap( String content ) throws IllegalStateException
    {
        try
        {
            MapType stringStringMapTypew = MapType.construct( HashMap.class ,
                                                              SimpleType.construct( String.class ) ,
                                                              SimpleType.construct( String.class ) );
            return (Map< String , String >) objectMapper.readValue( content ,
                                                                    stringStringMapTypew );
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "reading json content" , e.getCause() );
        }
    }

    @SuppressWarnings( "unchecked" )
    public static Map< String , String > readValueAsMap( JsonParser parser ) throws IllegalStateException
    {
        try
        {
            MapType stringMapTypew = MapType.construct( HashMap.class ,
                                                        SimpleType.construct( String.class ) ,
                                                        SimpleType.construct( String.class ) );
            return (HashMap< String , String >) objectMapper.readValue( parser , stringMapTypew );
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "reading json content" , e.getCause() );
        }

    }

    @SuppressWarnings( "unchecked" )
    public static Map< String , Object > readValueAsMapObject( String content ) throws IllegalStateException
    {
        try
        {
            MapType stringObjectMapTypew = MapType.construct( HashMap.class ,
                                                              SimpleType.construct( String.class ) ,
                                                              SimpleType.construct( Object.class ) );
            return (Map< String , Object >) objectMapper.readValue( content , stringObjectMapTypew );
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "reading json content" , e.getCause() );
        }

    }

    @SuppressWarnings( "unchecked" )
    public static Map< String , Object > readValuesAsMapObject( JsonParser parser ) throws IllegalStateException
    {
        try
        {
            MapType stringObjectMapTypew = MapType.construct( HashMap.class ,
                                                              SimpleType.construct( String.class ) ,
                                                              SimpleType.construct( Object.class ) );
            return (HashMap< String , Object >) objectMapper.readValue( parser , stringObjectMapTypew );
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "reading json content" , e.getCause() );
        }

    }

    /**
     * @throws IllegalStateException if any exception occurs.
     */
    public static < T > T readValue( File jsonFile, Class< T > valueType ) throws IllegalStateException
    {
        try
        {
            return objectMapper.readValue( jsonFile , valueType );
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "reading json content" , e.getCause() );
        }
    }

    /**
     * @throws IllegalStateException if any exception occurs.
     */
    public static < T > T readValue( InputStream is, Class< T > valueType ) throws IllegalStateException
    {
        try
        {
            return objectMapper.readValue( is , valueType );
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "reading json content" , e.getCause() );
        }
    }

    /**
     * @throws IllegalStateException if {@linkplain JsonProcessingException} occurs
     */
    public static String writeValueAsString( Object value ) throws IllegalStateException
    {
        try
        {
            return objectMapper.writeValueAsString( value );
        }
        catch ( JsonProcessingException e )
        {
            throw new IllegalStateException( "writing as json" , e.getCause() );
        }
    }

    /**
     * @throws IllegalStateException if any exception occurs.
     */
    public static < T > ObjectNode toObjectNode( T object ) throws IllegalStateException
    {
        try
        {
            return objectMapper.convertValue( object , ObjectNode.class );

        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "reading json content" , e.getCause() );
        }
    }

}
