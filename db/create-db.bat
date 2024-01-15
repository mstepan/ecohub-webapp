

docker stop ecohub-db
docker rm ecohub-db

docker network rm ecohub-db-network

docker network create -d bridge ecohub-db-network

docker run ^
  -v "C:\Users\Shade\Desktop\workspace\ecohub-webapp\db\init.sql:/docker-entrypoint-initdb.d/init.sql" ^
  -e POSTGRES_USER=postgres ^
  -e POSTGRES_PASSWORD=postgres ^
  --network ecohub-db-network ^
  -p 5432:5432 ^
  -d --name ecohub-db ^
  --cpus=1 ^
  --memory=1g --memory-swap=1g --memory-reservation=512m ^
  postgres:14.4
