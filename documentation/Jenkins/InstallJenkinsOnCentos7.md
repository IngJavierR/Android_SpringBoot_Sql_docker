# Generando el ambiente con Jenkins

En este documento de describe el proceso para instalar en jenkins en ambiente para ejecutar y probar la aplicación.

## Requisitos

* [Instalar Java 8](http://openjdk.java.net/install/)
* [Instalar Jenkins](https://wiki.jenkins.io/display/JENKINS/Installing+Jenkins+on+Red+Hat+distributions)
* [Instalar Android SDK](http://moonlightbox.logdown.com/posts/2016/02/01/linux-install-android-sdk-in-centos7-updated-2017-07-24)
* [Instalar Liquibase](http://download.liquibase.org/download/)
* [Instalar Docker CE Centos](https://docs.docker.com/install/linux/docker-ce/centos/)
* [Instalar NodeJs v8](https://www.hugeserver.com/kb/install-nodejs8-centos7-debian8-ubuntu16/)

## Procedimiento

1. Instalar Java 8  
`$ sudo yum install java-1.8.0-openjdk`

2. Instalar java 8 development  
`$ sudo yum install java-1.8.0-openjdk-devel`

3. Instalar Jenkins  
`$ sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat-stable/jenkins.repo`
`$ sudo rpm --import https://jenkins-ci.org/redhat/jenkins-ci.org.key`
`$ sudo yum install jenkins`

4. Jenkins como servicio  
`$ sudo service jenkins start`
`$ sudo chkconfig jenkins on`  
Opciones para manipular Jenkins: sudo service jenkins start/stop/restart

5. Abrir puertos jenkins  
`$ sudo firewall-cmd --permanent --new-service=jenkins`  
`$ sudo firewall-cmd --permanent --service=jenkins --set-short="Jenkins Service Ports"`  
`$ sudo firewall-cmd --permanent --service=jenkins --set-description="Jenkins service firewalld port exceptions"`  
`$ sudo firewall-cmd --permanent --service=jenkins --add-port=8080/tcp`  
`$ sudo firewall-cmd --permanent --add-service=jenkins`  
`$ sudo firewall-cmd --zone=public --add-service=http --permanent`  
`$ sudo firewall-cmd --reload`  

6. Instalar Android SDK  
`$ sudo mkdir -p /opt/sdk-tools-linux-3859397`  
`$ cd /opt/sdk-tools-linux-3859397`  
`$ sudo wget https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip`  
`$ sudo unzip sdk-tools-linux-3859397.zip`  
`$ cd /opt`  
`$ sudo chown -R root:root sdk-tools-linux-3859397`  
`$ sudo ln -s sdk-tools-linux-3859397 android-sdk`  

7. Set Android Enviroment variables  
`$ sudo vim /etc/profile.d/android-sdk-env.sh`  
`export ANDROID_HOME="/opt/android-sdk"  export PATH="$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$PATH"`  
`$ sudo -s source /etc/profile.d/android-sdk-env.sh`  

8. Update Android SDK  
`$ cd /opt/android-sdk/tools`  
`$ sudo ./android update sdk --no-ui`  
`$ /opt/android-sdk/tools/bin/sdkmanager --list`  
`$ /opt/android-sdk/tools/bin/sdkmanager "tools" "build-tools;26.0.0" "extras;android;m2repository" "extras;google;google_play_services" "platforms;android-26" "platform-tools"`  

9. Insalar liquibase  
`cd /usr/local/lib`  
`wget https://github.com/liquibase/liquibase/releases/download/liquibase-parent-3.5.5/liquibase-3.5.5-bin.tar.gz`  
`sudo mkdir liquibase`  
`sudo tar -xzf liquibase-3.5.5-bin.tar.gz -C liquibase`

9. Set Liquibase Enviroment variables y agregar al PATH  
`$ sudo vim /etc/profile.d/liquibase-env.sh`  
`export LIQUIBASE_HOME="/usr/local/lib/liquibase"`  
`$ sudo -s source /etc/profile.d/liquibase-env.sh`  
`$ vi ~/.bash_profile`  
`export PATH=$PATH:/usr/local/lib/liquibase`  
`$ source ~/.bash_profile`  

10. Instalar Docker CE  
`$ sudo yum install -y yum-utils device-mapper-persistent-data lvm2`  
`$ sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo`  
`$ sudo yum install docker-ce`  

11. Iniciar y verificar instalación docker  
`$ sudo systemctl start docker`  
`$ sudo docker run hello-world`  

12. Correr docker sin sudo  
`sudo groupadd docker`  
`sudo usermod -aG docker $USER`  
NOTA: Hacer logout y despues login, para que los permisos sean evaluados nuevamente.

13. Instalar docker-compose  
`$ sudo yum install epel-release`  
`$ sudo yum install -y python-pip`  
`$ sudo pip install docker-compose`  
`$ sudo yum upgrade python*`  

14. Instalar NodeJs v8  
`sudo curl -sL https://rpm.nodesource.com/setup_8.x | bash -`  
`rpm -i --nosignature --force '/tmp/tmp.uq4T4izXFa'`  
`sudo yum install nodejs`  

15. Verificar instalacion Nodejs  
`node --version` --> v8.\*.*  
`npm --version` --> 5.\*.*  

## Configurar Jenkins

Pasos para la configuración de Jenkins en el servidor de despliegue:

1. Ingresar a la Url de jenkins http://{ip servidor}:8080

2. Aparecera la siguiente pantalla si es la primera vez que se inicia el sistema.

3. Copiar el contenido del archivo que nos indica la pantalla y colocarlo en el campo Administrator password  
`cat /var/lib/jenkins/secrets/initialAdminPassword`

4. Instalar los plugins sugeridos

5. Llenar los campos de un usuario administrador

6. Comenzar a utilizar Jenkins

7. Ingresar Administrar Jenkins / Configurar el sistema

8. Agregar las siguientes variables de entorno

9. Instalar el plugin de Bitbucket




## Server

IP: 192.168.0.88
USR: admin  
Pwd: //R0g4  
