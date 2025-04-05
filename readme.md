# mykube
`mykube` is a RESTful Spring Boot application for URL shortening which runs inside Kubernetes

## Prerequisites
- Java 21
- Maven
- Docker

## Build the image
Make sure you're in the root of the project (where `pom.xml` is located), then run:

```bash
docker build -t mykube .
```

## Tag the image
```bash
docker tag mykube rezacomplete/mykube:latest
```

## Log in to Docker Hub
```bash
docker login
```

## Push the image
```bash
docker push rezacomplete/mykube:latest
```

## Deploy the app into Kubernetes cluster
```bash
kubectl apply -f setup.yaml
```

## Add domain to the hosts file
```bash
echo "127.0.0.1 mykube.local" | sudo tee -a /etc/hosts
```

## Send the post command
```bash
curl -X POST http://mykube.local/shorten -H "Content-Type: text/plain" -d "https://example.com/some/long/url"
```

## Undeploy the app
```bash
kubectl delete -f setup.yaml
```
