# Dockerfile for ${APP_NAME}
# 1. Copy ${DEPLOY_ZIP} to current directory
# 2. Build with: docker build -t server-registry-eureka .
# 3. Run with: docker run -p 8080:8080 -e DS_URL="jdbc:mysql://fill-in-the-correct-server:3306/ApolloConfigDB?characterEncoding=utf8" -e DS_USERNAME=FillInCorrectUser -e DS_PASSWORD=FillInCorrectPassword -d -v /tmp/logs:/opt/logs --name ${APP_NAME} ${APP_NAME}

FROM openjdk:8-jre-alpine
MAINTAINER fang <fang_baichun@163.com>

ENV APP_NAME server-registry-eureka
ENV VERSION 1.0.0
ENV SERVER_PORT 8761
ENV DEPLOY_ZIP ${APP_NAME}-${VERSION}-github.zip

RUN echo "http://mirrors.aliyun.com/alpine/v3.8/main" > /etc/apk/repositories \
    && echo "http://mirrors.aliyun.com/alpine/v3.8/community" >> /etc/apk/repositories \
    && apk update upgrade \
    && apk add --no-cache procps unzip curl bash tzdata \
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone

ADD ${DEPLOY_ZIP} /eureka/${DEPLOY_ZIP}

RUN unzip /eureka/${DEPLOY_ZIP} -d /eureka \
    && rm -rf /eureka/${DEPLOY_ZIP} \
    && sed -i '$d' /eureka/scripts/startup.sh \
    && chmod +x /eureka/scripts/startup.sh \
    && echo "tail -f /dev/null" >> /eureka/scripts/startup.sh

EXPOSE $SERVER_PORT

CMD ["/eureka/scripts/startup.sh"]
