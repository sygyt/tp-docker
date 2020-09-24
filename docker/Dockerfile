FROM openjdk:11

ENV url 127.0.0.1:5432/tp_devops
ENV username tp_devops
ENV password tp_devops

ADD tp-docker-1.0.0.jar .

CMD java -Ddatabase.url=${url} -Ddatabase.username=${username} -Ddatabase.password=${password} -jar tp-docker-1.0.0.jar
