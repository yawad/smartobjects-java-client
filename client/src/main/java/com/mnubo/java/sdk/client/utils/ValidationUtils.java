package com.mnubo.java.sdk.client.utils;

import static com.mnubo.java.sdk.client.Constants.HTTP_PROTOCOL;
import static com.mnubo.java.sdk.client.Constants.MAX_PORT_VALUE;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ValidationUtils
{
    public static int parseAsInteger( String propertyValue, String property )
    {
        parseAsString( propertyValue , property );
        if ( propertyValue.matches( "\\d+" ) )
        {
            try
            {
                int value = Integer.parseInt( propertyValue );
                return value;
            }
            catch ( Exception ex )
            {}
        }
        throw new IllegalStateException(
                                         String.format( "%s property has to be an positive integer." , property ) );
    }

    public static boolean parseAsBoolean( String propertyValue, String property )
    {
        parseAsString( propertyValue , property );

        if(propertyValue.equalsIgnoreCase("true") || propertyValue.equalsIgnoreCase("false") )
        {
            return Boolean.parseBoolean(propertyValue);
        }
        else
        {
            throw new IllegalStateException( String.format( "%s property has to be a boolean valid." , property ) );
        }
    }

    public static String parseAsString( String propertyValue, String property )
    {
        if ( isBlank( propertyValue ) )
        {
            throw new IllegalStateException( String.format( "%s property has to be a valid String." , property ) );
        }
        return propertyValue;
    }

    public static int parseAsPort( String propertyValue, String property )
    {
        int value = parseAsInteger( propertyValue , property );
        if ( value == 0 || value > MAX_PORT_VALUE )
        {
            throw new IllegalStateException( String.format( "%s property has to be a valid port." , property ) );
        }
        return value;
    }

    public static String parseAsHttpProtocol( String httpProtocol )
    {
        parseAsString( httpProtocol , HTTP_PROTOCOL );
        if ( !httpProtocol.equalsIgnoreCase( "http" ) && !httpProtocol.equalsIgnoreCase( "https" ) )
        {
            throw new IllegalStateException( String.format( "%s has to be equal to \"http\" or \"https\"" ,
                                                            HTTP_PROTOCOL ) );
        }
        return httpProtocol;
    }

    public static boolean validNotNull( Object object, String objectName )
    {
        if ( object == null )
        {
            throw new IllegalStateException( String.format( "%s cannot be null." , objectName ) );
        }
        return true;
    }

    public static boolean validIsFile( File fileName )
    {
        validNotNull( fileName , "configuration file" );
        if ( !fileName.exists() || fileName.isDirectory() )
        {
            throw new IllegalStateException( String.format( "This file, %s does not exist or is a folder" ,
                                                            fileName.getAbsolutePath() ) );
        }
        return true;
    }

    public static boolean notBlank( String object, String message )
    {
        if ( isBlank( object ) )
        {
            throw new IllegalStateException( message );
        }
        return true;
    }

    public static boolean notNullNorEmpty(List list, String message )
    {
        if ( list == null || list.isEmpty() )
        {
            throw new IllegalStateException( message );
        }
        return true;
    }

    public static boolean isBlank( String object )
    {
        return StringUtils.isBlank( object );
    }
}
