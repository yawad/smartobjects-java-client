# mnubo Java SDK integration example

Table of Content
================
 
[1. Introduction](#section1)

[2. Prerequisites](#section2)

[3. Configure the example](#section3)

[4. Run the example](#section4)

---
#<a name="section1"></a>1. Introduction

Give an example about how integrate this Java SDK.

---
#<a name="section2"></a>2. Prerequisites

- to Have Java and maven installed.
- To have mnubo's consumer key and consumer secret.
- To have a Object Model already defined. 

---
#<a name="section3"></a>3. Configure the example

- Go to the file 'SdkClientIntegrationTest' in '...example/src/test/java/com/mnubo/java/sdk/client/integration/' folder
- Remove or comment out the 'Ignore' annotation.
- Assign your personal consumer key to the field 'CONSUMER_KEY' as: CONSUMER_KEY = "consumerKeyProvidedByMnubo";
- Assign your personal consumer secret to the field 'CONSUMER_SECRET' as: CONSUMER_SECRET = "consumerSecretProvidedByMnubo";
- Add at least one Owner's attribute to the fields 'OWNER_ATTRIBUTES' and 'OWNER_ATTRIBUTES_2_UPDATE' this can be the sames or not.
- Add at least one Object's attribute to the fields 'OBJECT_ATTRIBUTES' and 'OBJECT_ATTRIBUTES_2_UPDATE' this can be the sames or not.
- Add at least one Timeserie to the fields 'TIMESERIES_2_POST' and 'EXTRA_TIMESERIES_2_POST' this can be the sames or not.

---
#<a name="section4"></a>4. Run the example

- Go to '...example/' folder
- Invoke 'mvn package'.
