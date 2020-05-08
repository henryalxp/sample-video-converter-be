mvn clean package -Prod
docker build -t video-converter .
docker stop video-converter
docker rm video-converter
docker run -d -p 8080:8080 -t --name video-converter video-converter
