FROM gitlab:8223/root/docker_registry/aiindevops.azurecr.io/alpine:3.13
ADD . /todo
WORKDIR /todo
EXPOSE 5000
RUN apk --update add openssl ca-certificates musl
RUN apk --update add --virtual build-dependencies python3-dev build-base wget
RUN apk add --update --no-cache python3 py3-pip
RUN pip3 install -r source/requirements.txt
ENTRYPOINT ["python3", "source/app.py"]
