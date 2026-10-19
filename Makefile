.PHONY: help test test-lb1 test-lb2 test-lb3 test-lb4 test-lb5 test-lb6 test-lb7 test-all run run-lb1 run-lb2 run-lb3 run-lb4 run-lb5 run-lb6 run-lb7

LAB ?= lb1

help:
	@echo "Available commands:"
	@echo "  make test           - Run tests for all labs"
	@echo "  make test-lb[1-7]   - Run tests for a specific lab (e.g., make test-lb4)"
	@echo "  make run LAB=lbX    - Run a specific lab program (e.g., make run LAB=lb4)"
	@echo "  make run-lb[1-7]    - Shorthand to run a specific lab"

test:
	./mvnw test

test-lb1:
	./mvnw -q -pl lb1 test

test-lb2:
	./mvnw -q -pl lb2 test

test-lb3:
	./mvnw -q -pl lb3 -am test

test-lb4:
	./mvnw -q -pl lb4 -am test

test-lb5:
	./mvnw -q -pl lb5 -am test

test-lb6:
	./mvnw -q -pl lb6 -am test

test-lb7:
	./mvnw -q -pl lb7 -am test

test-all: test

run:
	@case "$(LAB)" in \
	   lb1) ./mvnw -q -pl lb1 -am compile && ./mvnw -q -pl lb1 org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.alg_lb1.Main ;; \
	   lb2) ./mvnw -q -pl lb2 -am compile && ./mvnw -q -pl lb2 org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.alg_lb2.Main ;; \
	   lb3) ./mvnw -q -pl lb3 -am compile && ./mvnw -q -pl lb3 org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.alg_lb3.Main ;; \
	   lb4) ./mvnw -q -pl lb4 -am compile && ./mvnw -q -pl lb4 org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.lb4.ui.Main ;; \
	   lb5) ./mvnw -q -pl lb5 -am compile && ./mvnw -q -pl lb5 org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.lb5.ui.Main ;; \
	   lb6) ./mvnw -q -pl lb6 -am compile && ./mvnw -q -pl lb6 org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.lb6.ui.Main ;; \
	   lb7) ./mvnw -q -pl lb7 -am compile && ./mvnw -q -pl lb7 org.codehaus.mojo:exec-maven-plugin:3.6.3:java -Dexec.mainClass=com.lpnu.lb7.ui.Main ;; \
	   *) echo "Unsupported LAB=$(LAB). Use lb1 through lb7."; exit 1 ;; \
	esac

run-lb1:
	$(MAKE) run LAB=lb1

run-lb2:
	$(MAKE) run LAB=lb2

run-lb3:
	$(MAKE) run LAB=lb3

run-lb4:
	$(MAKE) run LAB=lb4

run-lb5:
	$(MAKE) run LAB=lb5

run-lb6:
	$(MAKE) run LAB=lb6

run-lb7:
	$(MAKE) run LAB=lb7
