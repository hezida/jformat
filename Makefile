##############
# Parameters #
##############
WEBROOT:=/var/www/jformat
PEM:=~/downloads/hezidaniel.pem
REMOTE_MACHINE:=root@ec2-46-137-133-209.eu-west-1.compute.amazonaws.com


###########
# Targets #
###########
.PHONY: all
all:
	$(info what do you want me to do?)

.PHONY: install
install:
	ant
	ant javadoc
	sudo rm -rf $(WEBROOT)
	sudo mkdir -p $(WEBROOT)
	sudo cp -r bin/javadoc $(WEBROOT)
	sudo cp bin/dist/jformat.jar $(WEBROOT)
	sudo cp -r web index.html $(WEBROOT)
	sudo chmod -R og+rx $(WEBROOT)

.PHONY: remote_install
remote_install:
	ant
	ant javadoc
	ssh -i $(PEM) $(REMOTE_MACHINE) "rm -rf /var/www/*"
	scp -r -i $(PEM) bin/dist/jformat.jar web index.html bin/javadoc $(REMOTE_MACHINE):/var/www
	ssh -i $(PEM) $(REMOTE_MACHINE) "chmod -R go+rx /var/www/*"
