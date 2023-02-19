# backend-enterprise

1. Pull project from git 

2. Pull image with docker
   
   Open terminal and enter :   docker pull cashion1406/postgre_docker:latest


![image](https://user-images.githubusercontent.com/52843640/219953076-e94aa264-8b56-41a7-8001-a48ee64a80c4.png)


3. Initiate docker
    
   Using command: docker run -p5431:5432  --name docker-postgres -e POSTGRES_PASSWORD=postgres -d cashion1406/postgre_docker 
   
   
