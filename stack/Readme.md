# Kubernetes deployment

## Traefik 

```
$ kubectl apply -f https://raw.githubusercontent.com/containous/traefik/master/examples/k8s/traefik-rbac.yaml
 
$ kubectl apply -f https://raw.githubusercontent.com/containous/traefik/master/examples/k8s/traefik-deployment.yaml
```


## Zookeeper and Kafka 

* Intall using Helm 

```
$ helm repo add incubator http://storage.googleapis.com/kubernetes-charts-incubator
$ helm install --name my-kafka incubator/kafka
```

If using a dedicated namespace(recommended) then make sure the namespace exists with:

```
$ kubectl create ns kafka
$ helm install --name poc --set global.namespace=kafka incubator/kafka
```

Use a configuration file: 

```
$ helm install --set global.namespace=kafka -f kafka-helm-conf.yaml incubator/kafka
```

* Connecting to Kafka from inside Kubernetes

You can connect to Kafka by running a simple pod in the K8s cluster like this with a configuration like this:

  apiVersion: v1
  kind: Pod
  metadata:
    name: testclient
    namespace: default
  spec:
    containers:
    - name: kafka
      image: confluentinc/cp-kafka:4.0.1-1
      command:
        - sh
        - -c
        - "exec tail -f /dev/null"

Once you have the testclient pod above running, you can list all kafka
topics with:

  kubectl -n default exec testclient -- /usr/bin/kafka-topics --zookeeper k-zookeeper:2181 --list

To create a new topic:

  kubectl -n default exec testclient -- /usr/bin/kafka-topics --zookeeper k-zookeeper:2181 --topic test1 --create --partitions 1 --replication-factor 1

To listen for messages on a topic:

  kubectl -n default exec -ti testclient -- /usr/bin/kafka-console-consumer --bootstrap-server k-kafka:9092 --topic test1 --from-beginning

To stop the listener session above press: Ctrl+C

To start an interactive message producer session:
  kubectl -n default exec -ti testclient -- /usr/bin/kafka-console-producer --broker-list k-kafka-headless:9092 --topic test1

To create a message in the above session, simply type the message and press "enter"
To end the producer session try: Ctrl+C

# Mongo 

```
$ kubectl apply -f mongo-service.yaml
 
```