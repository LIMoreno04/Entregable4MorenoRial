#!/usr/bin/env bash
set -e
echo "Building..."
mvn -DskipTests=false clean package

echo "Stopping previous app (if any)..."
pkill -f miplaylist || true

echo "Copy artifact..."
mkdir -p /opt/miplaylist
cp target/*.jar /opt/miplaylist/miplaylist.jar

echo "Starting app..."
nohup java -jar /opt/miplaylist/miplaylist.jar > /var/log/miplaylist.log 2>&1 &
echo "Deploy finished."
