# Api-sum
Ejercicio de API
# Mockserver usado
https://beeceptor.com/console/tenpo-percentage
## Endpoints
### Signup user
```
curl -X POST \
  http://localhost:8086/signup/users \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"email": "pepe@foo.com",
	"password": "123"
}'
```

### Retrieve Percentage
```
curl -X POST \
  http://localhost:8086/percentage \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"first_number": 10,
	"second_number": 0
}'
```
### Extern Service Percentage
```
curl -X GET \
  https://tenpo-percentage.free.beeceptor.com/tenpo/percentage \
```
## Levantar DB y servicios
```
docker run --name basic-postgres --rm -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e PGDATA=/var/lib/postgresql/data/pgdata -v /tmp:/var/lib/postgresql/data -p 5432:5432 -it postgres:14.1-alpine
```
