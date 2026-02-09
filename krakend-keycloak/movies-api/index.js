import express from "express";

const app = express();

import { createRemoteJWKSet, jwtVerify } from "jose";

const JWKS = createRemoteJWKSet(
    new URL(
      "http://localhost:8080/realms/marcelocorp/protocol/openid-connect/certs"
    )
  );

const port = 3000;

const authInterceptor = async function (req, res, next) {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1];

    if (token == null) {
      return res.status(401).send('Authentication token required.'); 
    }
  
    const { payload } = await jwtVerify(token, JWKS, {
        issuer: "http://localhost:8080/realms/marcelocorp"
      });
  
      req.user = payload;
    next()
}

app.use(authInterceptor)
 
app.get('/movie/v1/movies/', (req, res) =>  {
    const movies = [
        { title: 'End Game'}
    ];
    res.send(movies);
})
 
app.listen(port, () => console.log(`Listen port = ${port}`));