FROM java:9
COPY Server.java MultiThreadRespond.java /
RUN javac Server.java
EXPOSE 9876
ENTRYPOINT ["java"]
CMD ["Server"]