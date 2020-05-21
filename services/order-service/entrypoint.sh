#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar order-service-0.0.1-SNAPSHOT.jar