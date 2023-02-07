
.PHONY : all
all: controller sensor programs

.PHONY : controller
controller:
	@echo "Building Controller(s)..."; \
	cd common; \
	javac -g -classpath . *.java; \
	cd ../controller; \
	javac -g -classpath .:.. *.java; \
	cd ..; \

.PHONY : sensor
sensor:
	@echo "Building Sensor(s)..."; \
	cd common; \
	javac -g -classpath . *.java; \
	cd ../sensor; \
	javac -g -classpath .:.. *.java; \


.PHONY : programs
programs:
	@echo "Building Programs..."; \
	cd common; \
	javac -g -classpath . *.java; \
	cd ../run; \
	javac -g -classpath .:.. *.java; \


.PHONY : clean
clean:
		rm sensor/*.class controller/*.class common/*.class run/*.class
