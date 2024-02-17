# Use a minimal base image
FROM openjdk:11-slim

# Set working directory inside the container
WORKDIR /app

# Create /home/data directory inside the container
RUN mkdir -p /home/data

# Copy the Java script file and text files into the container
COPY program.java /app
COPY IF.txt /home/data
COPY Limerick-1.txt /home/data

# Compile the Java code
RUN javac program.java

# Command to run the Java program and redirect output to result.txt
CMD ["java", "program"] > /home/output/result.txt && cat /home/output/result.txt
