 #!/bin/bash

kubectl apply -f ./movements/service.yaml
kubectl apply -f ./position/service.yaml
kubectl apply -f ./position-builder/service.yaml
kubectl apply -f ./web-tracker/service.yaml