#!/bin/bash

# Output 0 means "no, this thing is not on"
# Output 1 means "this thing is on"

# make sure container name was provided
if [ -z $1 ]; then
	echo "Container name must be provided!"
	echo "Expected usage: $0 container-name"
	exit 1
fi


# Breakdown
# List all the running container suing 'docker ps'
# Search the list of running container for the name provided using 'grep'
# Count the number of lines output by 'grep' using 'wc'
docker ps | grep $1 | wc -l
