#!/bin/bash

echo "select * from shoe_collection_app.app_users;" | docker exec -i project1db psql -U postgres --html | tee result.html
