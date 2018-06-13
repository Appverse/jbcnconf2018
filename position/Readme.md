
# Mongo DB

## StatefulSet

kubectl apply -f https://raw.githubusercontent.com/thesandlord/mongo-k8s-sidecar/master/example/StatefulSet/mongo-statefulset.yaml

kubectl delete -f https://raw.githubusercontent.com/thesandlord/mongo-k8s-sidecar/master/example/StatefulSet/mongo-statefulset.yaml

## Using Helm
 
helm install --name my-release stable/mongodb


helm install --name positionsdb stable/mongodb-replicaset