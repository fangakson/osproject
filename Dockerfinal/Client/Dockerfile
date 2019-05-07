FROM java:9
COPY Client.java /
RUN javac Client.java
EXPOSE 9876
ENTRYPOINT ["java"]
CMD ["Client"]