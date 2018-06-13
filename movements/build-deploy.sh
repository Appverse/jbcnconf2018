#!/bin/bash
parent_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )

cd "$parent_path"

echo "Building ... "
mvn clean install 
echo "Building docker image ... "
docker build -t poc/movements:0.0.1 .

echo "Remove from kubernetes...it will fail if it not exists..."
kubectl delete -f service.yaml

echo "Kubernetes Deploy ... " 
kubectl apply -f service.yaml