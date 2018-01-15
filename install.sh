#!/bin/bash
cp keystore.jks /app/data/keystore.jks
if [ ! -d /app/data/default/conf ]; then
	wget http://www-us.apache.org/dist//directory/apacheds/dist/2.0.0-M24/apacheds-2.0.0-M24.zip
	unzip apacheds-2.0.0-M24.zip
	cp -r apacheds-2.0.0-M24/instances/default /app/data/
	sed -i 's/10636/4040/g' /app/data/default/conf/config.ldif
	sed -i -e 's/ads-searchBaseDN: ou=users,ou=system/&\nads-certificatePassword:: aXRzYXNlY3JldA==\nads-keystoreFile: \/app\/data\/keystore.jks/' /app/data/default/conf/config.ldif
fi


