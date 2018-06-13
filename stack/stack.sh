#!/bin/bash

echo "Traefik ..."
## TRAEFIK 
kubectl apply -f https://raw.githubusercontent.com/containous/traefik/master/examples/k8s/traefik-rbac.yaml
 
kubectl apply -f https://raw.githubusercontent.com/containous/traefik/master/examples/k8s/traefik-deployment.yaml

kubectl describe svc -n kube-system traefik-ingress-service

### Kubernetes dashboard with traefik ingress
echo "Kubernetes Dashboard ..."
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/master/src/deploy/alternative/kubernetes-dashboard.yaml

kubectl apply -f kube-dashboard-ingress.yaml

### Zookeeper & Kafka 
kubectl create -f zookeeper.yaml 

kubectl create -f kafka.yaml