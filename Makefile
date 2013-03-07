##############
# Parameters #
##############
WEBROOT:=/var/www/jformat


###########
# Targets #
###########
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
