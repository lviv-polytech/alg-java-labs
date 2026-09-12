.PHONY: help test test-lb1 test-lb2 test-all run run-lb1 run-lb2

LAB ?= lb1

help:
	@echo "Available commands:"
	@echo "  make test           - Run tests for all labs"
	@echo "  make test-lb1       - Run tests for lab 1"
	@echo "  make test-lb2       - Run tests for lab 2"
	@echo "  make run LAB=lb1    - Run a specific lab program"
	@echo "  make run LAB=lb2    - Run a specific lab program"

test:
	./mvnw test

test-lb1:
	./mvnw -q -pl lb1 test

test-lb2:
	./mvnw -q -pl lb2 test

test-all: test

run:
	@case "$(LAB)" in \
		lb1) ./mvnw -q -f lb1/pom.xml org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.alg_lb1.Main ;; \
		lb2) ./mvnw -q -f lb2/pom.xml org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.alg_lb2.Main ;; \
		*) echo "Unsupported LAB=$(LAB). Use lb1 or lb2."; exit 1 ;; \
	esac

run-lb1:
	$(MAKE) run LAB=lb1

run-lb2:
	$(MAKE) run LAB=lb2
