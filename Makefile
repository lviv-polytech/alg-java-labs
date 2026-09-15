.PHONY: help test test-lb1 test-lb2 test-lb3 test-all run run-lb1 run-lb2 run-lb3

LAB ?= lb1

help:
	@echo "Available commands:"
	@echo "  make test           - Run tests for all labs"
	@echo "  make test-lb1       - Run tests for lab 1"
	@echo "  make test-lb2       - Run tests for lab 2"
	@echo "  make test-lb3       - Run tests for lab 3"
	@echo "  make run LAB=lb1    - Run a specific lab program"
	@echo "  make run LAB=lb2    - Run a specific lab program"
	@echo "  make run LAB=lb3    - Run a specific lab program"

test:
	./mvnw test

test-lb1:
	./mvnw -q -pl lb1 test

test-lb2:
	./mvnw -q -pl lb2 test

test-lb3:
	./mvnw -q -pl lb3 -am test

test-all: test

run:
	@case "$(LAB)" in \
	   lb1) ./mvnw -q -pl lb1 -am compile && ./mvnw -q -pl lb1 org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.alg_lb1.Main ;; \
	   lb2) ./mvnw -q -pl lb2 -am compile && ./mvnw -q -pl lb2 org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.alg_lb2.Main ;; \
	   lb3) ./mvnw -q -pl lb3 -am compile && ./mvnw -q -pl lb3 org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.alg_lb3.Main ;; \
	   *) echo "Unsupported LAB=$(LAB). Use lb1, lb2 or lb3."; exit 1 ;; \
	esac

run-lb1:
	$(MAKE) run LAB=lb1

run-lb2:
	$(MAKE) run LAB=lb2

run-lb3:
	$(MAKE) run LAB=lb3
