# mnubo Java SDK
This Java SDK client provides you a wrapper to connect your Java application to our API.

## Geting started
---
Include the mnubo client in your Java application using:

### Maven
Add the following dependencies into your pom.xml:
```
<dependency>
	<groupId>com.mnubo</groupId>
    <artifactId>java-sdk-client</artifactId>
    <scope>compile</scope>
    <version>1.1.2</version>
</dependency>
```
### Artifacts
**Not yet available, it will be soon**.

### Download source code
Download the source code and include it in your Java Application project. All codes are available in **mnubo-java-sdk-client**.

## Configuration
---
A number of parameters must be configured before using the mnubo client. See section usage for more information.

- **Mandatory parameters:**
    - **hostname**.- mnubo's server name, for example: ```rest.sandbox.mnubo.com```.
    - **consumer-key**.- Your unique client identity which is provided by mnubo.
    - **consumer-secret**.- Your secret key which is used in conjunction with the consumer key to access the mnubo server. This key is provided by mnubo.
- **optional parameters:**
    - **platform-port**.- mnubo's server port, by default it is ```443```.
    - **autentication-port**.- mnubo's authentication port, by default it is ```443```.
    - **http-protocol**.- Use "http" for unsecure connection and "https" for secure connections. By default it is https.
    - **disable-redirect-handling**.- Disables automatic redirect handling. The default is ```false```.
    - **disable-automatic-retries**.- Disables automatic request recovery and re-execution. The default is ```false```.
    - **max-connections-per-route**.- Maximum connection per route value. The default is ```200```.
    - **default-timeout**.- The number of seconds a session can be idle before it is abandoned. The default is ```30```.
    - **connect-timeout**.- This is timeout in seconds until a connection is established. By default it is the same value the ```default-timeout```.
    - **connection-request-timeout**.- This is timeout in seconds when requesting a connection from the connection manager. By default it is the same values as the ```default-timeout```.
    - **socket-timeout**.- After a connection has been established this is the maximum period inactivity between two consecutive data packets). By default it take the value of ```default-timeout```.
    - **max-total-connection** This is the maximum number of connections allowed across all routes. The default is ```200```.

## Usage
---
Build a mnuboSDKClient instance. This provides all the interfaces required to use this library.

### Getting a "MnuboSDKClient" (client) instance
To get a client instance use **"MnuboSDKFactory" Class**. Note that:
1. You need only one client instance. Internally it provides you multithread support (thread safe) and a pool of connections.
2. All initializations and configuration are done by factory class.

There are two ways to get a client instance.:
- **Basic**, Only 3 mandatory parameters are required – Host, consumer key and consumer secret. Please see the example below:

```
//Configure constants
private final String HOST = "rest.sandbox.mnubo.com";
private final String CONSUMER_KEY = "your consumer key!!!";
private final String CONSUMER_SECRET = "your consumer SECRET!!!";

//getting mnubo client using simple way, taken default values.
MnuboSDKClient mnuboClient = MnuboSDKFactory.getClient( HOST , CONSUMER_KEY , CONSUMER_SECRET );
```

- **Advanced**, this allows configure default parameters using a properties file. Please see the example below.

```
//property file name
private static String CONFIG_FILE_NAME = "config.properties";

//Getting mnubo client using
MnuboSDKClient mnuboClient = MnuboSDKFactory.getAdvanceClient( new File( CONFIG_FILE_NAME ) );
```

property example file:

```
#Property file example
client.config.hostname=rest.sandbox.mnubo.com
client.config.platform-port=8081
client.config.autentication-port=8089
client.config.http-protocol=http
client.security.consumer-key=**???**
client.security.consumer-secret=**!!!**
client.http.client.disable-redirect-handling=true
client.http.client.disable-automatic-retries=true
client.http.client.max-connections-per-route=500
client.http.client.default-timeout=15
#default value for connect-timeout
#client.http.client.connect-timeout=30
client.http.client.connection-request-timeout=10
#default value for socket-timeout
#client.http.client.socket-timeout=30
client.http.client.max-total-connection=500
```

#### creating Owners
To create an owner you need to:
1. Request an OwnersSDK interface from the mnubo client instance.
2. Build an owner.

Here is an example:
```
//get mnubo client using basic way.
MnuboSDKClient mnuboClient = MnuboSDKFactory.getClient( HOST , CONSUMER_KEY , CONSUMER_SECRET );

//get Owners client interface
OwnersSDK mnuboOwnersClient = mnuboClient.getOwnerClient();

//build the owner
Owner Owner2Create = new Owner();
Owner2Create.setUsername( "john.smith@mycompany.com" );
Owner2Create.setPassword( "dud7#%^ddd_J" );
Owner2Create.setRegistrationDate( DateTime.parse( "2015-01-01T00:00:00+04:00" ) );
Owner2Create.setAttributesValue( "gender", "male" );
Owner2Create.setAttributesValue( "height", "1.80" );

//create the owner
mnuboOwnersClient.create( Owner2Create );
```

Note that the same Owner created above can be deserialized using the a flat Json file as following, "myOwnerFile.json" file:
```
{
  "username":"john.smith@mycompany.com",
  "x_password":"dud7#%^ddd_J",
  "x_registration_date":"2015-01-01T00:00:00+04:00",
  "gender":"male",
  "height":"1.80"
}
```

And create it using "SDKMapperUtils" singleton deserializer as:

```
//get mnubo client using basic way.
MnuboSDKClient mnuboClient = MnuboSDKFactory.getClient( HOST , CONSUMER_KEY , CONSUMER_SECRET );

//get Owners client interface
OwnersSDK mnuboOwnersClient = mnuboClient.getOwnerClient();

//read Owner from flat json file
String owner2BePosted = ReadingFile( "myOwnerFile.json" );

//deserialise the json file
Owner owner2Create = SDKMapperUtils.readValue( owner2BePosted , Owner.class );

//create the owner
mnuboOwnersClient.create( owner2Create );
```

#### creating SmartObjects
To create a SmartObject:
1. Request an OwnersSDK interface from the mnubo client instance.
2. Build an object.

```
//get mnubo client using basic way.
MnuboSDKClient mnuboClient = MnuboSDKFactory.getClient( HOST , CONSUMER_KEY , CONSUMER_SECRET );

//get Object client interface
ObjectsSDK mnuboObjectClient = mnuboClient.getObjectClient();

//read Object from flat json file
String object2BePosted = ReadingFile( "myObjectFile.json" );

//deserialise the json file
SmartObject object2Create = SDKMapperUtils.readValue( object2BePosted , SmartObject.class );

//create the owner
mnuboObjectClient.create( object2Create );
```

Note that this case the flat Json file, "myObjectFile.json", look like:
```
{
    "x_device_id" : "connect_alpha.6hv135nw00393.1234567",
    "x_object_type" : "gateway",
    "x_registration_date":"2015-01-27T08:01:01.000Z",
    "partnerid" : "connect_alpha",
    "business_line" : "connect",
    "siteid" : "6hv135nw00393",
    "site_description" : "My connected House",
    "x_owner":
    {
           "username" : "john.smith@mycompany.com"
    }
}
```

#### Send Events
To send events:
1. Request an OwnersSDK interface from the mnubo client instance.
2. Build an event.

##### Send Events to single object
```
//private String objectID = "mythermostat0301424";

//get mnubo client using basic way.
MnuboSDKClient mnuboClient = MnuboSDKFactory.getClient( HOST , CONSUMER_KEY , CONSUMER_SECRET );

//get Event client interface
EventsSDK mnuboEventClient = mnuboClient.getEventClient();

//read event from flat json file
String event2BeSent = ReadingFile( "myEvents.json" );

//deserialise the json file
EventValues event2Send = SDKMapperUtils.readValue( event2BeSent , Events.class );

//send the event
mnuboEventClient.send( objectID, event2Send );
```

Note that in this case the flat Json file, "myEventsByObjectFile.json", look like:
```
[
    {
        "x_event_type" : "thermostat_temperature",
        "x_timestamp" : "2015-02-22T08:19:04+00:00",
        "event_id": "830789",
        "thermostat_temperature": 20,
        "errorcode": "",
        "varname": "temperature",
    },
    {
        "x_event_type" : "thermostat_temperature",
        "x_timestamp" : "2015-02-22T08:20:06+00:00",
        "event_id": "830789",
        "thermostat_temperature": 22,
        "cause_type": "normal",
        "varname": "temperature",
    }
]
```
##### Send Events to multiples objects
```
//get mnubo client using basic way.
MnuboSDKClient mnuboClient = MnuboSDKFactory.getClient( HOST , CONSUMER_KEY , CONSUMER_SECRET );

//get Event client interface
EventsSDK mnuboEventClient = mnuboClient.getEventClient();

//read event from flat json file
String event2Besent = ReadingFile( "myEvents.json" );

//deserialise the json file
EventValues event2Send = SDKMapperUtils.readValue( event2Besent , EventValues.class );

//send the event
mnuboEventClient.send( event2Send );
```

Note that in this case the flat Json file, "myEvents.json", look like:
```
[
    {
        "x_object":
        {
            "x_device_id" :"connect_alpha.6hv135nw00393.81"
        },
        "x_event_type": "thermostat_temperature",
        "x_timestamp": "2015-02-22T08:19:04+00:00",
        "event_id": "830789",
        "thermostat_temperature": 20,
        "cause_type": null,
        "errorcode": "",
        "varname": "temperature",
    },
    {
        "x_object":
        {
            "x_device_id" :"connect_alpha.6hv135nw00393.82"
        },
        "x_event_type": "light_dimmer.internal",
        "x_timestamp": "2014-10-30T21:46:10+00:00",
        "event_id": "920534",
        "light_dimmer": 0,
        "cause_type": "internal",
        "errorcode": null,
        "varname": "level",
        "varfunction": "light-dimmer",
    },
    {
        "x_object":
        {
            "x_device_id" :"connect_alpha.6hv135nw00393.83"
        },
        "x_event_type": "mask_masked.internal",
        "x_timestamp": "2014-10-30T21:46:10+00:00",
        "event_id": "2350139",
        "mask_masked": "Not Masked",
        "cause_type": "internal",
        "errorcode": null,
        "varname": "mask-state",
    }
]
```