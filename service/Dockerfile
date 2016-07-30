FROM java:8

ENV VERSION=2.3

RUN apt-get update
RUN wget -q https://services.gradle.org/distributions/gradle-${VERSION}-bin.zip \
    -O /tmp/gradle-${VERSION}-bin.zip
RUN apt-get install stow
RUN cd /usr/local/stow/ && unzip /tmp/gradle-${VERSION}-bin.zip
RUN cd /usr/local/stow && stow gradle-${VERSION}

VOLUME /tmp
COPY /src /src
COPY build.gradle build.gradle

RUN bash -c 'gradle build'
RUN bash -c 'cp /build/libs/gameme-0.0.1.jar /'

RUN bash -c 'gradle clean'
RUN bash -c 'rm -rf src/'
RUN bash -c 'rm build.gradle'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","gameme-0.0.1.jar"]