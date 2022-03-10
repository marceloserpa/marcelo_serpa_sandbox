


sudo chown -R 1000:root esdata  


GET _search
{
"query": {
"match_all": {}
}
}


DELETE posts_not_sorted


DELETE posts_sorted


GET posts_not_sorted/_search
{
"query": {
"match_all": {}
}
}



GET /posts_not_sorted/_search
{
"size": 50,
"sort": [
{ "created_at": "desc" }
]
}


GET /posts_sorted/_search
{
"size": 50,
"sort": [
{ "created_at": "desc" }
],
"track_total_hits": false
}



GET posts_not_sorted/_settings


GET posts_sorted/_settings

GET _cat/indices/posts_not_sorted

GET _cat/indices/posts_sorted,posts_not_sorted
