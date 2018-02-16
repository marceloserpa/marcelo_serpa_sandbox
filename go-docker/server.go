package main

import (
    "fmt"
    "net/http"
    "os"
)

func handler(w http.ResponseWriter, r *http.Request) {    
    fmt.Fprintf(w, "Hi there, I love %s!", r.URL.Path[1:])
}

func main() {
    fmt.Println("PROFILE:", os.Getenv("PROFILE"))
    http.HandleFunc("/", handler)
    http.ListenAndServe(":8080", nil)
}
