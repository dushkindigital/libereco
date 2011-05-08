echo Setup
set LIBERECO_ROOT="D:\projects\java\dushkindigital\libereco\aid"

echo TODO: !!! Add code that copies configuration properties
echo TODO: !!! Organize jars in libs directory

echo Libereco

cd %LIBERECO_ROOT%

echo eBay SDK jars are not available in official Maven repositories.
echo Because of this they need to be installed manually (this is one-time action).
echo There are 4 jars that need to be installed and the following commands should be executed from %LIBERECO_ROOT%\lib\ebay (this is for version 687 of the SDK)
echo mvn install:install-file -DgroupId=com.ebay.ebaysdk -DartifactId=ebaysdkcore -Dversion=687 -Dfile=ebaysdkcore.jar -Dpackaging=jar
echo mvn install:install-file -DgroupId=com.ebay.ebaysdk -DartifactId=ebaycalls -Dversion=687 -Dfile=ebaycalls.jar -Dpackaging=jar
echo mvn install:install-file -DgroupId=com.ebay.ebaysdk -DartifactId=attributes -Dversion=687 -Dfile=attributes.jar -Dpackaging=jar
echo mvn install:install-file -DgroupId=com.ebay.ebaysdk -DartifactId=helper -Dversion=687 -Dfile=helper.jar -Dpackaging=jar 

rem mvn install:install-file -DgroupId=com.ebay.ebaysdk -DartifactId=ebaysdkcore -Dversion=687 -Dfile=ebaysdkcore.jar -Dpackaging=jar
rem mvn install:install-file -DgroupId=com.ebay.ebaysdk -DartifactId=ebaycalls -Dversion=687 -Dfile=ebaycalls.jar -Dpackaging=jar
rem mvn install:install-file -DgroupId=com.ebay.ebaysdk -DartifactId=attributes -Dversion=687 -Dfile=attributes.jar -Dpackaging=jar
rem mvn install:install-file -DgroupId=com.ebay.ebaysdk -DartifactId=helper -Dversion=687 -Dfile=helper.jar -Dpackaging=jar

rem call mvn -Dmaven.test.skip install
rem call mvn -Dmaven.test.skip clean package install
call mvn -Dmaven.test.skip clean package


rem copy %LIBERECO_ROOT%\target\aid-1.0-SNAPSHOT.jar %LIBERECO_ROOT%\dist\.
copy %LIBERECO_ROOT%\target\libereco-1.0-SNAPSHOT.jar %LIBERECO_ROOT%\dist\.
echo Copied target jar to dist directory

