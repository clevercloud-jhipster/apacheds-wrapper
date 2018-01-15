# ApacheDS Clever Cloud Wrapper

This project aims at running Apache Directory Server on Clever Cloud.

Every project running on CleverCloud must respond with a 2xx code to an HTTP request. And ApacheDS needs a none HTTP port for the LDAP protocol. As such we are wrapping it in a Payara instance. A Servlet is used to answer to CleverCloud HTTP ping request and a Servlet Listener is used to bootstrap ApacheDS. 

ApacheDS is using the file system as backend. Because of that we need to use an FS Bucket. 

## Configuration

 - This application must be deployed as a WAR: `clever create --type war apache-ds-wrapper`
  - Create a [FS Bucket](https://www.clever-cloud.com/doc/addons/fs_buckets/): `clever addon create fs-bucket --plan s apachedsFS`
  - Link the FS Bucket add-on to the application: `clever service link-addon apachedsFS`
 - Define where to mount our FS Bucket:``clever env set CC_FS_BUCKET /data:`clever env | awk  -F = '/BUCKET_HOST/ { print $2}'` ``


Here's an example of environment variable used for configuration:
```
ADS_INSTANCES=/app/data
CC_FS_BUCKET=/data:yourBucketHostaddress
CC_PRE_RUN_HOOK=./install.sh
ENABLE_METRICS=true
INSTANCE_NAME=/app/data/default
JAVA_VERSION=8
PORT=8080
```

The `install.sh` script is responsible for the initialization of the ApacheDS configuration. Right now we download the latest version, unzip it, copy the default configuration in the FS Bucket and change the LDAP HTTPS port to 4040. Port 4040 is only made available through a request to Clever Cloud's support.

You need to import your own keystore. It's added directly to the default configuration through the `instal.sh` script. Don't forget to change the password.


