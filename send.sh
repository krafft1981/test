#!/bin/bash

check() {
	if [ ! $? -eq 0 ]
	then
		exit
	fi
}

while true
do
	book=$(curl -X POST "http://192.168.15.103:8080/book?name=a&publishingYear=0&annotation=b&authors=0" 2> /dev/null)
	check
	author=$(curl -X POST "http://192.168.15.103:8080/author?fio=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)&birthYear=$(cat /dev/urandom | tr -dc '0-9' | fold -w 4 | head -n 1)&books=$book" 2> /dev/null)
	check
	curl -X PUT "http://192.168.15.103:8080/book?id=$book&name=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)&publishingYear=$(cat /dev/urandom | tr -dc '0-9' | fold -w 4 | head -n 1)&annotation=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)&authors=$author" 2> /dev/null
	check
	customer=$(curl -X POST "http://192.168.15.103:8080/customer?name=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)&phone=+7917$(cat /dev/urandom | tr -dc '0-9' | fold -w 7 | head -n 1)" 2> /dev/null)
	check
	order=$(curl -X POST "http://192.168.15.103:8080/order?name=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)&customer=$customer&books=$book" 2> /dev/null)
	check

	job=$(((RANDOM % 2) + 1))
	if [ $job -eq 2 ]
	then
		curl -X POST "http://192.168.15.103:8080/order/finished?id=$order" 2> /dev/null
		check
	fi


	echo "author=[$author] book[$book] customer=[$customer] order=[$order] job[$job]"
done
