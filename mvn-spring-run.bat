mvn -Djavax.net.ssl.trustStore=certs/cacerts -Djavax.net.ssl.trustStorePassword=changeit -Dmaven.wagon.http.ssl.ignore.validity.dates=true -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true -Dext.properties.file="file:./application.properties"  clean spring-boot:run -s configuration/settings.xml -DLOGGING_LEVEL=INFO