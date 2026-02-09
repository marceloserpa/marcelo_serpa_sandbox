TOKEN=$1

curl -X GET \
    "http://localhost:3000/movie/v1/movies" \
    -H "Authorization: Bearer $TOKEN" 