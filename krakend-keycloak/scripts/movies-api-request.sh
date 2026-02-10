TOKEN=$1

curl -X GET \
    "http://localhost:9000/api/movie/v1/movies" \
    -H "Authorization: Bearer $TOKEN" 