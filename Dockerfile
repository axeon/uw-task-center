FROM bellsoft/liberica-openjre-debian:21-cds AS builder
WORKDIR /builder
COPY target/*.jar application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

FROM bellsoft/liberica-openjre-debian:21-cds
WORKDIR /application
COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./
# Execute the CDS training run
RUN java -XX:ArchiveClassesAtExit=application.jsa -Dspring.context.exit=onRefresh -jar application.jar

ENV JAVA_OPTS="" SPRING_OPTS=""
ENTRYPOINT ["java","-XX:SharedArchiveFile=application.jsa", "-jar","$JAVA_OPTS","application.jar","${SPRING_OPTS}"]