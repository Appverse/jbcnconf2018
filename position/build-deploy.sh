#!/bin/bash
parent_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )

cd "$parent_path"

echo "Building ... "
mvn clean install -Dmaven.test.skip=true
echo "Building docker ... "
docker build -t poc/positions:0.0.1 .
echo "Deploy ... " 
kubectl apply -f service.yaml