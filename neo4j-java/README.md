# PoC Java + Neo4j

to get all nodes:

```
MATCH (a:UserPoc) RETURN a
```

To delete relationships and nodes: 

```
MATCH (a:UserPoc) DETACH DELETE a
```


