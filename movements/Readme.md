# Build

## Maven build: 

```
mvn clean install 
```

### RUN

The default profile will use the *8080* port.

```
 mvn spring-boot:run 
```


## Docker build:

```
docker build -t poc/movements:0.0.1 .

```

# Kubernetes Deploy

Deploy: 
 
```
kubectl apply -f service.yaml
```

# Build and Kubernetes Deploy script

Requires Kubectl configured. 

All-in-one script: 

```
./build-deploy.sh
```

## Clean Kubernetes deployment

```
kubectl delete -f service.yaml
```


# Run 

Using cUrl

* Local JVM (Default Spring Profile):

 
```
$ curl -X POST -H "Content-Type: application/json" -d '{"objectId": 123,"x": 1,"y": 1}' http://localhost:8080/movements
```

Using HTTPie:

```
$ http POST :8080/movements objectId=123 x=1 y=1
```

* Kubernetes:
 
```
$ curl -X POST -H "Content-Type: application/json" -d '{"objectId": 123,"x": 1,"y": 1}' http://${KUBE_HOST}:${TRAEFIK_PORT}/movements
```

Using HTTPie:

```
$ http POST http://${KUBE_HOST}:${TRAEFIK_PORT}/movements objectId=123 x=1 y=1
```
 