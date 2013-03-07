##############
# Parameters #
##############
WEBROOT:=/var/www/jformat


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
