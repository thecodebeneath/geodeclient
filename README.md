# geodeclient
Simple client running on the Docker host that will connect to Geode running in a Docker container 

# Build the Geode image
Use the Dockerfile and gfsh script to create an image that will start a locator and server on container start
```bash
cd geode
docker build -t apachegeode/geode:with-startup-script .
```

# Start Geode in a Docker container:
The Docker container will start the locator and server and then the gfsh shell
```bash
winpty docker run -it -p 10334:10334 -p 40404:40404 -p 1099:1099 -p 7070:7070 apachegeode/geode:with-startup-script
gfsh > connect
```

Important: Note the hostname connected to, e.g. [host=*d26f1ea42d39*, port=10334]

# Windows network hack
To reach the Geode controller from outside of the containter, edit your "hosts" file to give 127.0.0.1 an alias matching the location host address
Example:
```
127.0.0.1       localhost   d26f1ea42d39
```

# Run the simple client
mvn clean install
java -jar target\geodeclient-1.0-SNAPSHOT.jar

# Query the Geode region for data
```bash
gfsh > query --query='select * from /hello-world-region'

This will return a result similiar with a Message: Could not create an instance of a class com.codebeneath.geodeclient.model.ApplicationMessage
```

Because PDX is enabled, we can read the individual fields with a query (and avoid full deserialization of the whole object)
```bash
gfsh > query --query='select message from /hello-world-region'

Result
------
World
Hello

```
