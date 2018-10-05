# geodeclient
Simple client to connect to Geode running in a Docker container 

# Start Geode in a Docker container:
docker run -p 8080:8080 -p 10334:10334 -p 40404:40404 -p 1099:1099 -p 7070:7070 -it apachegeode/geode

gfsh > start locator ```Note the hostname it starts on, e.g. d26f1ea42d39[10334]```

gfsh > start server

# Setup Geode region
gfsh > create region --name=hello-world-region --type=REPLICATE

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
gfsh > query --query='select * from /hello-world-region'
