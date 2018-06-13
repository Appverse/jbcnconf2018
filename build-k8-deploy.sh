 #!/bin/bash

source $(dirname $0)/movements/build-deploy.sh
cd ..
source $(dirname $0)/position/build-deploy.sh
cd ..
source $(dirname $0)/position-builder/build-deploy.sh
cd ..
source $(dirname $0)/web-tracker/build-deploy.sh