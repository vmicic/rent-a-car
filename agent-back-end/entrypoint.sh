#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar agent-back-end-0.0.1-SNAPSHOT.jar