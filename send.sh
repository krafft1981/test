#!/bin/bash

while true
do
	book=$(curl -X POST "http://192.168.15.103:8080/book?name=a&publishingYear=0&annotation=b&authors=0")
	author=$(curl -X POST "http://192.168.15.103:8080/author?fio=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)&birthYear=$(cat /dev/urandom | tr -dc '0-9' | fold -w 4 | head -n 1)&books=$book")
	curl -X PUT "http://192.168.15.103:8080/book?id=$book&name=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)&publishingYear=$(cat /dev/urandom | tr -dc '0-9' | fold -w 4 | head -n 1)&annotation=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)&authors=$author"
done
