# Dockerfile to run a geodeclient against geode running in a container
# Does not work because I don't know how to reference the hostname that the 'locator' starts on
# > docker run -e  "GEODE_HOST=localhost" geodeclient
FROM java:8
WORKDIR /
ADD target/geodeclient-1.0-SNAPSHOT.jar geodeclient-1.0-SNAPSHOT.jar
CMD java -jar geodeclient-1.0-SNAPSHOT.jar