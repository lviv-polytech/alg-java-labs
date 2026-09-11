.PHONY: help test run install-mvnw

help:
	@echo "Available commands:"
	@echo "  make test           - Run unit tests"
	@echo "  make run            - Run the application"
	@echo "  make install-mvnw   - Install local mvnw (Maven Wrapper)"

test:
	./mvnw test

run:
	./mvnw compile exec:java

install-mvnw:
	mvn -N wrapper:wrapper -Dmaven.wrapper.version=3.9.5
