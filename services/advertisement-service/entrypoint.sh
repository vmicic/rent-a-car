#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar advertisement-service-0.0.1-SNAPSHOT.jar